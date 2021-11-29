package cn.gdrj.est.service.impl;

import cn.gdrj.est.common.exception.BusinessException;
import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.constants.MaintainStatusEnum;
import cn.gdrj.est.constants.ResultEnum;
import cn.gdrj.est.dto.MaintainOrderDTO;
import cn.gdrj.est.entity.PayOrder;
import cn.gdrj.est.service.AliPayService;
import cn.gdrj.est.service.MaintainOrderService;
import cn.gdrj.est.service.PayOrderService;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import cn.gdrj.est.util.AliPayProperties;
import java.util.Date;
import java.util.Map;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
@Service
public class AliPayServiceImpl implements AliPayService {
    @Autowired
    private AliPayProperties aliPayProperties;
    @Autowired
    private MaintainOrderService maintainOrderService;
    @Autowired
    private PayOrderService payOrderService;
    @Override
    public String paySubscription(String orderNo) throws AlipayApiException {
       MaintainOrderDTO maintainOrderDTO = maintainOrderService.getMaintainOrderBy(orderNo);
       if(ObjectUtils.isEmpty(maintainOrderDTO)){
           throw new BusinessException(ResultEnum.FAIL_PARAM);
       }
       if(!MaintainStatusEnum.APPOINT.getCode().equals(maintainOrderDTO.getStatus())){
           throw new BusinessException(ResultEnum.FAIL_PARAM);
       }
       // 创建支付订单
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(maintainOrderDTO.getOrderNo());
        String payNo= maintainOrderDTO.getOrderNo()+ RandomUtil.randomNumbers(5);
        payOrder.setPayNo(payNo);
        // payOrder.setAliTradeNo();
        payOrder.setUserId(maintainOrderDTO.getUserId());
        payOrder.setPrice(maintainOrderDTO.getSubscription());
        // 1:定金,2:尾款
        payOrder.setPayType(1);
        payOrder.setStatus(Constants.Order.PayStaus.UN_PAY);
        payOrder.setPayChannel(Constants.Order.Channel.ALIPAY);
        payOrder.setCreatedUserId(maintainOrderDTO.getUserId());
        payOrder.setCreatedTime(new Date());
        payOrder.setIsDelete(Constants.EST_NOT);
        payOrderService.addPayOrder(payOrder);

        //获得初始化的AlipayClient
        AlipayClient alipayClient =
                new DefaultAlipayClient(aliPayProperties.getGatewayUrl(),
                        aliPayProperties.getAppId(),
                        aliPayProperties.getMerchantPrivateKey(),
                        "json",
                        aliPayProperties.getCharset(),
                        aliPayProperties.getAlipayPublicKey(),
                        aliPayProperties.getSignType());
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliPayProperties.getReturnUrl());
        alipayRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());

        JSONObject jsonObject = new JSONObject();
        // 支付订单号要唯一，用于和支付宝对账
        jsonObject.set("out_trade_no",payNo);
        jsonObject.set("total_amount",payOrder.getPrice());
        jsonObject.set("subject","商品维修定金");
        jsonObject.set("body","");
        jsonObject.set("product_code","FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizContent(jsonObject.toString());
        String body = alipayClient.pageExecute(alipayRequest).getBody();
        return body;
    }

    @Override
    public String notifyCallback(Map<String, String> params) {
        // 获取交易状态值
        String tradeStatus = params.get("trade_status");
        if("TRADE_SUCCESS".equals(tradeStatus)){
            // 把支付订单表的交易状态修改为已支付，填写支付宝的交易订单号
            String tradeNo = params.get("trade_no");
            String payOrderNo = params.get("out_trade_no");
            // 支付订单编号
            PayOrder payOrder = payOrderService.getById(payOrderNo);
            if(ObjectUtils.isEmpty(payOrder)){
                return "success";
            }
            // 如果发现订单不是未支付的状态时，就不用做任何操作
            if(!ObjectUtils.nullSafeEquals(payOrder.getStatus(),Constants.Order.PayStaus.UN_PAY)){
                return "success";
            }
            // 维修订单编号
            String orderNo = payOrder.getOrderNo();
            // 支付订单id
            Long payOrderId = payOrder.getId();
            payOrderService.updateOrderStatusToSuccessAndTradeNoById(payOrderId,tradeNo);
            // 修改维修订单的状态为 支付定金
            maintainOrderService.updateStatusToPaySubscription(orderNo);
        }else if("TRADE_FINISHED".equals(tradeStatus)){
            // 交易过了退款期限，交易结束之后要做的操作
            // ...
        }
        return "success";
    }
}
