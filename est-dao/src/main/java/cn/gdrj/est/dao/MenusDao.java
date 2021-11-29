package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Menus;

public interface MenusDao {
    int deleteByPrimaryKey(Long id);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
}