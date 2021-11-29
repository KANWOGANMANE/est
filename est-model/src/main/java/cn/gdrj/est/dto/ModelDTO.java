package cn.gdrj.est.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * model
 * @author
 */
@Data
public class ModelDTO implements Serializable {
    private Long id;

    /**
     * 型号名称
     */
    private String modelName;


    /**
     * 当前行情价格
     */
    private BigDecimal exchangePrice;

    /**
     * 最高价
     */
    private BigDecimal topPrice;

    /**
     * 封面图片
     */
    private String faceImg;

    /**
     * 内容图片
     */
    private String contentImg;

}
