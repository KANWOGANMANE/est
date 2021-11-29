package cn.gdrj.est.service;

import cn.gdrj.est.entity.PayOrder;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
public interface PayOrderService {
    int addPayOrder(PayOrder payOrder);

    int updateOrderStatusToSuccessAndTradeNoById(Long payOrderId, String tradeNo);

    PayOrder getById(String payOrderNo);
}
