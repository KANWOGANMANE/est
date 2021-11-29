package cn.gdrj.est;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/8
 */
public class SmsTest {

    public static void main(String[] args) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId("LTAI5tRMVamGTM4EsvdXdSat")
                // 您的AccessKey Secret
                .setAccessKeySecret("OaPKUeJcaN2ETa2FqSRxseP5Qylt7n");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers("13266122792")
                .setSignName("金码二手兔")
                .setTemplateCode("SMS_1925414361")
                .setTemplateParam("{\"code\":\"123456\"}");
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        SendSmsResponseBody body = sendSmsResponse.getBody();
        System.out.println("RequestId =>"+ body.getRequestId());
        System.out.println("BizId =>"+ body.getBizId());
        System.out.println("Code =>"+ body.getCode());
        System.out.println("Message=>"+ body.getMessage());
    }
}
