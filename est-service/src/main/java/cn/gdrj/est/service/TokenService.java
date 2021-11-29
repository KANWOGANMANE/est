package cn.gdrj.est.service;

import cn.gdrj.est.dto.TokenDTO;
import cn.gdrj.est.entity.Users;

public interface TokenService {
    /**
     *   根据登录的用户信息自动生成一个token:
     *   (使用UUID的方式自动生成一个)
     */
    TokenDTO generateToken(Users users);

    /**
     * 保存token和相关的用户信息到redis中
     */
    void saveToken(String token,Users users);

    /**
     * 根据token获取用户的信息
     */
    Users getUserInfoByToken(String token);

    /**
     * 清除token相关的会话信息
     */
    void removeToken(String token);

}
