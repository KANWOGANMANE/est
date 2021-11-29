package cn.gdrj.est.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * evaluate
 * @author
 */
@Data
public class EvaluateDTO implements Serializable {
    private Long id;

    /**
     * 型号id
     */
    private Long modelId;

    /**
     * 订金金额
     */
    private BigDecimal subscription;

    /**
     * 维修估价
     */
    private BigDecimal price;

}
