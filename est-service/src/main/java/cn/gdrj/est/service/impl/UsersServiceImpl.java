package cn.gdrj.est.service.impl;

import cn.gdrj.est.common.exception.BusinessException;
import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.constants.ResultEnum;
import cn.gdrj.est.dao.UsersDao;
import cn.gdrj.est.dto.TokenDTO;
import cn.gdrj.est.dto.UsersInfoDTO;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.SmsLoginParam;
import cn.gdrj.est.service.SmsVerifyCodeService;
import cn.gdrj.est.service.TokenService;
import cn.gdrj.est.service.UsersService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;


@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private SmsVerifyCodeService verifyCodeService;
    @Autowired
    private TokenService tokenService;

    @Override
    public Users findById(Long id) {
        return usersDao.selectByPrimaryKey(id);
    }

    @Override
    public Users findByOpenId(String openid) {
        return usersDao.findByOpenId(openid);
    }

    @Override
    public Users registe(Users users) {
        usersDao.insertSelective(users);
        return users;
    }

    @Override
    public TokenDTO smsLogin(SmsLoginParam loginParam) {
        // 根据手机号获取缓存的验证码
        String phone = loginParam.getPhone();
        String verifyCode = verifyCodeService.getVerifyCode(phone);
        // 对比验证码是否正确
        // 如果不正确，抛出业务异常： 验证码不正确
        if (!StrUtil.equals(verifyCode,loginParam.getSms())) {
            throw new BusinessException(ResultEnum.FAIL_VERIFY);
        }
        // 如果验证码正确，就清除验证码
        verifyCodeService.removeVerifyCode(phone);

        // 根据手机号查询用户信息
        Users users = usersDao.findByAccount(phone);
        // 如果用户信息不存在，就进行自动注册
        if(ObjectUtils.isEmpty(users)){
            users = registerByPhone(phone);
        }
        // 自动登录
        TokenDTO tokenDTO = tokenService.generateToken(users);
        tokenService.saveToken(tokenDTO.getToken(),users);
        // 返回结果
        return tokenDTO;
    }

    @Override
    public UsersInfoDTO getUserInfoByToken(String token) {
        Users users = tokenService.getUserInfoByToken(token);

        UsersInfoDTO infoDTO = new UsersInfoDTO();
        // infoDTO.setId(users.getId());
        // infoDTO.setAccount(users.getAccount());
        // infoDTO.setUserName(users.getUserName());
        // infoDTO.setSex(users.getSex());
        // infoDTO.setFaceUrl(users.getFaceUrl());
        BeanUtils.copyProperties(users,infoDTO);

        return infoDTO;
    }

    @Override
    public void logout(String token) {
        tokenService.removeToken(token);
    }

    private Users registerByPhone(String phone) {
        Users users = new Users();
        users.setAccount(phone);
        users.setUserName(phone);
        users.setIsAuthentication(Constants.EST_NOT);
        users.setCreatedTime(new Date());
        users.setIsDelete(Constants.EST_NOT);

        usersDao.insert(users);
        return users;
    }
}
