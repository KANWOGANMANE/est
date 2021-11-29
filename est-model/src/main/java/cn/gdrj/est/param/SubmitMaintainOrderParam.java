package cn.gdrj.est.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 提交维修订单的参数对象
 *
 * @author jun
 * @date 2021/11/16
 */
@Data
public class SubmitMaintainOrderParam {
    @NotNull(message = "评估id不能为空")
    private Long evaluateId;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "手机号格式不正确")
    private String phone;

    @NotNull(message = "地址不能为空")
    @Size(min = 10,max = 255,message = "地址信息在10到255个字之间")
    private String adress;

    @NotNull(message = "日期不能为空")
    // @Future yyyy-MM-dd HHmmss   2021-11-13 =》 2021-11-13 00:00:00
    // @Future(message = "无效日期，时间必须是一个将来的时间点")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appintDate;

    @NotNull(message = "时间段不能为空")
    // 时间段只能是1,2，3,4中的一个值（通过自定验证的注解来完成）
    private Integer temporalInterval;

}
