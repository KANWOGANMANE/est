package cn.gdrj.est.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * evaluate_detail
 * @author
 */
@Data
public class EvaluateDetailDTO implements Serializable {
    private Long id;

    /**
     * 评估信息Id
     */
    private Long evaluateId;

    /**
     * 故障id
     */
    private Long malfId;

    /**
     * 故障选项选项id
     */
    private Long optionId;

    /**
     * 故障选项选项名称
     */
    private String optionName;

}
