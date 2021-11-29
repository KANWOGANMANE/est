package cn.gdrj.est.controller;

import cn.gdrj.est.common.BaseController;
import cn.gdrj.est.common.util.ResultUtil;
import cn.gdrj.est.config.WechatLoginProperties;
import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dto.Result;
import cn.gdrj.est.dto.TokenDTO;
import cn.gdrj.est.dto.UsersInfoDTO;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.SmsLoginParam;
import cn.gdrj.est.service.TokenService;
import cn.gdrj.est.service.UsersService;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户API")
@RestController
public class UserController extends BaseController {

    //@Value("${oauth2.wechat.appId}")
    //private String appId;
    @Autowired
    private WechatLoginProperties wechatLoginProperties;

    @Autowired
    private UsersService usersService;
    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "跳转到微信登录", notes = "请求这个接口之后，会重定向到微信扫描登录授权的页面")
    @GetMapping("/api/user/login/wechat")
    public void toWechatLogin() throws IOException {
        StringBuilder builder = new StringBuilder(wechatLoginProperties.getLoginUrl());
        builder.append("appid=").append(wechatLoginProperties.getAppId());
        builder.append("&redirect_uri=").append(wechatLoginProperties.getRedirectUrl());
        builder.append("&response_type=code&scope=snsapi_login&state=111111#wechat_redirect");

        response.sendRedirect(builder.toString());
    }

    @GetMapping("/api/user/login/wechatcallback")
    public void wechatCallback(String code) throws IOException {
        //1. 通过临时票据和appid和secret换取正式的票据
        // 使用restTemplate来发送http请求，获取返回的结果
        String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx9168f76f000a0d4c&secret=8ba69d5639242c3bd3a69dffe84336c1&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String tokenInfoJsonString = restTemplate.getForObject(getTokenUrl, String.class);
        JSONObject tokenInfoJsonObj = new JSONObject(tokenInfoJsonString);

        //2. 获取返回的access_token和openid
        String access_token = tokenInfoJsonObj.getStr("access_token");
        String openid = tokenInfoJsonObj.getStr("openid");

        //3. 通过openid查询用户是否已经存在于系统中
        Users users = usersService.findByOpenId(openid);
        //4. 如果不存在，就进行自动注册
        if (users == null) {
            // 获取用户的信息
            String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token={1}&openid={2}";
            String userInfoJsonString = restTemplate.getForObject(getUserInfoUrl, String.class, access_token, openid);
            JSONObject userInfoJsonObj = new JSONObject(userInfoJsonString);

            users = new Users();
            users.setUserName(userInfoJsonObj.getStr("nickname"));
            users.setSex(userInfoJsonObj.getInt("sex"));
            users.setIsAuthentication(Constants.EST_NOT);
            users.setFaceUrl(userInfoJsonObj.getStr("headimgurl"));
            users.setOpenId(openid);

            Date createdTime = new Date();
            users.setCreatedTime(createdTime);
            users.setUpdatedTime(createdTime);
            users.setIsDelete(Constants.EST_NOT);

            usersService.registe(users);

        }
        //5. 根据登录的用户信息，生成会话信息
        TokenDTO tokenDTO = tokenService.generateToken(users);
        tokenService.saveToken(tokenDTO.getToken(), users);
        //6. 返回会话信息
        Map<String, Object> tokenUserInfo = new HashMap<>();
        tokenUserInfo.put("token", tokenDTO.getToken());
        tokenUserInfo.put("userInfo", users.getUserName());
        String tokenString = JSONUtil.toJsonStr(tokenUserInfo);

        // 如果url中有一些特殊字符或中文字符的话，要进行编码处理
        String encodeTokenString = URLEncoder.encode(tokenString, "utf-8");

        // 7. 重定向到前端的相关页面
        //return ResultUtil.success();
        String loginSuccessURL = "http://localhost:8081/redirect?token=" + encodeTokenString;
        response.sendRedirect(loginSuccessURL);
    }


    @ApiOperation(value = "短信验证码登录", notes = "获取验证码之后，通过手机号和验证码进行登录")
    // @ApiImplicitParams(
    //         {
    //                 @ApiImplicitParam(name = "phone", value = "手机号码", paramType = "query", required = true),
    //                 @ApiImplicitParam(name = "sms", value = "短信验证码", paramType = "query", required = true)
    //         }
    // )
    @PostMapping("/api/user/login/sms")
    public Result<TokenDTO> smsLogin(@Valid SmsLoginParam loginParam) {

        TokenDTO tokenDTO = usersService.smsLogin(loginParam);

        return ResultUtil.success(tokenDTO);
    }

    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "会话token", paramType = "header", required = true)}
    )
    @GetMapping("/api/user/info")
    public Result<UsersInfoDTO> getUserInfo() {
        String token = request.getHeader("token");
        UsersInfoDTO usersInfoDTO = usersService.getUserInfoByToken(token);
        usersInfoDTO.setToken(token);
        return ResultUtil.success(usersInfoDTO);
    }

    @ApiOperation(value = "用户注销")
    @PostMapping("/api/user/logout")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "会话token", paramType = "header", required = true)}
    )
    public Result<Void> logout() {

        String token = request.getHeader("token");

        usersService.logout(token);

        return ResultUtil.success();
    }

}
