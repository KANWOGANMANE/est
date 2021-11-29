package cn.gdrj.est.service;

import com.alipay.api.AlipayApiException;

import java.util.Map;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
public interface AliPayService {
    String paySubscription(String orderNo) throws AlipayApiException;

    String notifyCallback(Map<String, String> params);
}
