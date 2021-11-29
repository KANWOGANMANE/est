package cn.gdrj.est.dto;

import lombok.Data;

import java.util.Date;

/**
 * 登录用户信息
 *
 * @author jun
 * @date 2021/11/9
 */
@Data
public class UsersInfoDTO {
    private Long id;

    /**
     * 账号（手机号）
     */
    private String account;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 性别（0:女,1:男,2:保密）
     */
    private Integer sex;

    /**
     * 头像
     */
    private String faceUrl;

    private String token;
}
