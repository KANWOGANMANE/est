package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dto.TokenDTO;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.service.TokenService;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONConverter;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {
    /**
     * 会话的过期时间
     */
    private static final long SESSION_EXPIRE_MINUTES=60;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public TokenDTO generateToken(Users users) {
        String token = UUID.fastUUID().toString(true);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        return tokenDTO;
    }

    @Override
    public void saveToken(String token, Users users) {
        // key=>  token:fjalkfjalfdaslfkjaf
        String tokenKey= Constants.Redis.PREFIX_TOKEN + token;
        JSONObject jsonObject = new JSONObject(users);
        redisTemplate.opsForValue()
                .set(
                        tokenKey,
                        jsonObject.toString(),
                        SESSION_EXPIRE_MINUTES,
                        TimeUnit.MINUTES
                );
    }

    @Override
    public Users getUserInfoByToken(String token) {
        // fastjson alibaba
        String tokenKey= Constants.Redis.PREFIX_TOKEN + token;
        String userInfoJSONString =
                redisTemplate.opsForValue().get(tokenKey);
        if(Objects.isNull(userInfoJSONString)){
            return null;
        }
        Users users = JSONUtil.toBean(userInfoJSONString, Users.class);
        return users;
    }

    @Override
    public void removeToken(String token) {
        String tokenKey= Constants.Redis.PREFIX_TOKEN + token;
        redisTemplate.delete(tokenKey);
    }
}
