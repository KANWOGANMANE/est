package cn.gdrj.est.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * maintain_order
 * @author
 */
@Data
public class MaintainOrderDTO implements Serializable {
    private Long id;

    /**
     * 商品序列号
     */
    private String serialNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 型号id
     */
    private Long modelId;

    /**
     * 评估信息id
     */
    private Long evaluateId;

    /**
     * 订金金额
     */
    private BigDecimal subscription;

    /**
     * 实际交易金额
     */
    private BigDecimal price;

    /**
     * 订单状态（1:预约下单,2:支付定金,9:用户取消,10:完成）
     */
    private Integer status;

    /**
     * 是否评论
     */
    private Integer isComment;

    /**
     * 取消订单的原因
     */
    private String cancelReason;


    private static final long serialVersionUID = 1L;

}
