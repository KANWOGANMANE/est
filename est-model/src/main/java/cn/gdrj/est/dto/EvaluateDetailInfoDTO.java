package cn.gdrj.est.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 整次评估的所有信息
 *
 * @author jun
 * @date 2021/11/16
 */
@Data
public class EvaluateDetailInfoDTO {
    private Long id;

    /**
     * 型号id
     */
    private ModelDTO model;

    /**
     * 订金金额
     */
    private BigDecimal subscription;

    /**
     * 维修估价
     */
    private BigDecimal price;

    private List<EvaluateDetailDTO> detailList;
}
