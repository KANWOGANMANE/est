package cn.gdrj.est.controller;

import cn.gdrj.est.common.BaseController;
import cn.gdrj.est.service.AliPayService;
import cn.gdrj.est.util.AliPayProperties;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
@RestController
@Validated
public class AliPayController extends BaseController {

    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private AliPayProperties aliPayProperties;

    @GetMapping("/api/pay/aliPay")
    public void toAliPay(@NotNull(message = "订单编号不能为空") String orderNo) throws Exception{
        String result = aliPayService.paySubscription(orderNo);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result);
    }

    @GetMapping("/api/pay/aliPay/return")
    public void returnCallback(@RequestParam Map<String,String> params) throws IOException, AlipayApiException {
        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                aliPayProperties.getAlipayPublicKey(),
                aliPayProperties.getCharset(), aliPayProperties.getSignType()); //调用SDK验证签名
        if(signVerified){
            response.sendRedirect("http://localhost:8081/confirmation");
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("非法请求！");
        }

    }

    @PostMapping("/api/pay/aliPay/notify")
    public void notifyCallback(@RequestParam Map<String,String> params)throws IOException, AlipayApiException {
        System.out.println("异步回调的参数：");
        System.out.println(params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                aliPayProperties.getAlipayPublicKey(),
                aliPayProperties.getCharset(), aliPayProperties.getSignType()); //调用SDK验证签名
        System.out.println("签名验证结果：" + signVerified );
        if(signVerified ){
            String result = aliPayService.notifyCallback(params);
            response.getWriter().write(result);
        }else{
            response.getWriter().write("fail");
        }

    }
}
