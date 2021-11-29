package cn.gdrj.est.service;

import cn.gdrj.est.dto.TokenDTO;
import cn.gdrj.est.dto.UsersInfoDTO;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.SmsLoginParam;

/**
 * 用户的业务接口
 */
public interface UsersService {

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    Users findById(Long id);

    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    Users findByOpenId(String openid);

    /**
     * 注册用户
     * @param users
     * @return
     */
    Users registe(Users users);

    /**
     * 短信验证码登录
     * @param loginParam
     * @return
     */
    TokenDTO smsLogin(SmsLoginParam loginParam);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    UsersInfoDTO getUserInfoByToken(String token);

    /**
     * 退出登录
     * @param token
     */
    void logout(String token);
}
