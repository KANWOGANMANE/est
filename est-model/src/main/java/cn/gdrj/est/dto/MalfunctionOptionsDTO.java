package cn.gdrj.est.dto;

import lombok.Data;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/15
 */
@Data
public class MalfunctionOptionsDTO {
    private Long id;

    /**
     * 商品型号id
     */
    private Long modelId;

    /**
     * 故障id
     */
    private Long malfId;

    /**
     * 名称
     */
    private String optionName;

    /**
     * 是否有提示0:否,1:是)
     */
    private Integer isHint;

    /**
     * 提示信息
     */
    private String hintInfo;

    /**
     * 提示图片
     */
    private String hintImg;

    /**
     * 处理方式（1:维修,2:更换配件）
     */
    private Integer processType;

    /**
     * 本项问题价格占比（最大100）
     */
    private Integer ratio;

}
