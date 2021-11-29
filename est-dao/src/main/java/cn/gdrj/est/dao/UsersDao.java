package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Users;
import org.apache.ibatis.annotations.Select;

public interface UsersDao {
    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    @Select("select * from users where openid=#{openid}")
    Users findByOpenId(String openid);

    @Select("select * from users where account=#{account}")
    Users findByAccount(String phone);
}
