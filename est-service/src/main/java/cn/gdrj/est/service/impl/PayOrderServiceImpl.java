package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.PayOrderDao;
import cn.gdrj.est.entity.PayOrder;
import cn.gdrj.est.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {
    @Autowired
    private PayOrderDao payOrderDao;
    @Override
    public int addPayOrder(PayOrder payOrder) {
        return payOrderDao.insertSelective(payOrder);
    }

    @Override
    public int updateOrderStatusToSuccessAndTradeNoById(Long payOrderId, String tradeNo) {
        return payOrderDao.updateStatusAndTradeNo(payOrderId,Constants.Order.PayStaus.SUCCESS,tradeNo);
    }

    @Override
    public PayOrder getById(String payOrderNo) {
        return payOrderDao.selectByPayOrderNo(payOrderNo);
    }
}
