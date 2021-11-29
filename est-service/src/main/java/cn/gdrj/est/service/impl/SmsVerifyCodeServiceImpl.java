package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.service.SmsVerifyCodeService;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 验证码业务组件
 *
 * @author jun
 * @date 2021/11/8
 */
@Service
public class SmsVerifyCodeServiceImpl implements SmsVerifyCodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendVerifyCode(String phone, Integer codeType) throws Exception {
        // 根据不同的验证码类型来发送不同的验证码
        if(Constants.Sms.TYPE_REGIST_OR_LOGIN.equals(codeType)){
            // 登录注册的验证码
            sendLoginVerifyCode(phone);
        }else if(Constants.Sms.TYPE_UP_PASS.equals(codeType)){
            // 修改密码的短信验证码
            sendUpdatePasswordVerifyCode(phone);
        }else if(Constants.Sms.TYPE_SUBMIT_CHECK.equals(codeType)){
            // 提交订单时的验证码
            sendSubmitOrderVerifyCode(phone);
        }
    }

    @Override
    public String getVerifyCode(String phone) {
        String smsRedisKey = getSmsRedisKey(phone);
        String code = stringRedisTemplate.opsForValue().get(smsRedisKey);
        return code;
    }

    @Override
    public void removeVerifyCode(String phone) {
        String smsRedisKey = getSmsRedisKey(phone);
        stringRedisTemplate.delete(smsRedisKey);
    }

    private void sendSubmitOrderVerifyCode(String phone) {

    }

    private void sendUpdatePasswordVerifyCode(String phone) {

    }

    private void sendLoginVerifyCode(String phone) throws Exception {
        // 先从redis中获取验证码，如果能获取到，直接返回（验证码在有效期内）
        String smsRedisKey = getSmsRedisKey(phone);
        String code = stringRedisTemplate.opsForValue()
                .get(smsRedisKey);
        if(!Objects.isNull(code)){
            return;
        }
        // 生成一个6位随机数字验证码
        code = RandomUtil.randomNumbers(6);
        // 发送给指定的手机号
        Map<String,Object> templateParam = new HashMap<>();
        templateParam.put("code",code);
        String signName = "金码二手兔";
        String templateCode = "SMS_1925414361";
        sendSms(phone,signName,templateCode,templateParam);
        // 保存到redis
        stringRedisTemplate.opsForValue()
                .set(smsRedisKey,code,5, TimeUnit.MINUTES);
    }

    private String getSmsRedisKey(String phone) {
        return Constants.Redis.PREFIX_SMS + phone;
    }


    private SendSmsResponse sendSms(String phone, String signName, String templateCode, Map<String, Object> templateParam) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId("LTAI5tRMVamGTM4EsvdXdSat")
                // 您的AccessKey Secret
                .setAccessKeySecret("OaPKUeJcaN2ETa2FqSRxseP5Qylt7n");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam(JSONUtil.toJsonStr(templateParam));

        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        return sendSmsResponse;
    }

}
