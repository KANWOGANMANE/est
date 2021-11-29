package cn.gdrj.est.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/15
 */
@Data
public class MalfunctionDTO {
    private Long id;

    /**
     * 商品型号id
     */
    private Long modelId;

    /**
     * 故障名称
     */
    private String title;

    /**
     * 是否有提示（0:否,1:是）
     */
    private Integer isHint;

    /**
     * 提示信息标题
     */
    private String hintTitle;

    /**
     * 提示信息
     */
    private String hintInfo;

    /**
     * 提示图片
     */
    private String hintImg;

    /**
     * 维修最高价
     */
    private BigDecimal maintainTopPrice;

    private List<?> optionsList;

}
