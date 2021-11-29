package cn.gdrj.est.dao;

import cn.gdrj.est.entity.PayOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PayOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(PayOrder record);

    int insertSelective(PayOrder record);

    PayOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayOrder record);

    int updateByPrimaryKey(PayOrder record);

    @Update("update pay_order set status= #{status},aliTradeNo=#{tradeNo} where id = #{payOrderId} ")
    int updateStatusAndTradeNo(@Param("payOrderId") Long payOrderId,
                               @Param("status") Integer status,
                               @Param("tradeNo") String tradeNo);

    @Select("select * from pay_order where payNo = #{payOrderNo}")
    PayOrder selectByPayOrderNo(@Param("payOrderNo") String payOrderNo);
}
