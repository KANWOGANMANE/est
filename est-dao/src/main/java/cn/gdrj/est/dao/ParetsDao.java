package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Parets;

public interface ParetsDao {
    int deleteByPrimaryKey(Long id);

    int insert(Parets record);

    int insertSelective(Parets record);

    Parets selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Parets record);

    int updateByPrimaryKey(Parets record);
}