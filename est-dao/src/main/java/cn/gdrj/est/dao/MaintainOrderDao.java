package cn.gdrj.est.dao;

import cn.gdrj.est.entity.MaintainOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

public interface MaintainOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaintainOrder record);

    int insertSelective(MaintainOrder record);

    MaintainOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaintainOrder record);

    int updateByPrimaryKey(MaintainOrder record);

    @Update("update maintain_order set orderNO=#{orderNo} where id=#{id}")
    int updateOrderNoById(@Param("id") Long id, @Param("orderNo") String orderNo);

    @Select("select * from maintain_order where orderNo=#{orderNo}")
    MaintainOrder getMaintainOrderBy(@Param("orderNo") String orderNo);

    @Update("update maintain_order set status = #{status}  where id = #{id}")
    int updateStatusById(@Param("id") Long id, @Param("status") Integer status);
}
