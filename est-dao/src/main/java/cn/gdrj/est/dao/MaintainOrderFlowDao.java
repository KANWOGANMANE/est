package cn.gdrj.est.dao;

import cn.gdrj.est.entity.MaintainOrderFlow;

public interface MaintainOrderFlowDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainOrderFlow record);

    int insertSelective(MaintainOrderFlow record);

    MaintainOrderFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainOrderFlow record);

    int updateByPrimaryKey(MaintainOrderFlow record);
}