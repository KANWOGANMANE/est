package cn.gdrj.est.controller;

import cn.gdrj.est.common.util.ResultUtil;
import cn.gdrj.est.dto.Result;
import cn.gdrj.est.service.SmsVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码控制器组件
 *
 * @author jun
 * @date 2021/11/8
 */
@RestController
public class SmsVerifyCodeController {

    @Autowired
    private SmsVerifyCodeService smsVerifyCodeService;

    @PostMapping("/api/sms/verifyCode")
    public Result<Void> sendVerifyCode(@RequestParam String phone,
                                       @RequestParam Integer codeType) throws Exception {
        // TODO 验证 phone 格式是否正确
        // TODO codeType的值是否符合要求

        smsVerifyCodeService.sendVerifyCode(phone,codeType);

        return ResultUtil.success();
    }
}
