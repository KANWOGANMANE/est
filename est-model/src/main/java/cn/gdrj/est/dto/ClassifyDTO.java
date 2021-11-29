package cn.gdrj.est.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * classify
 * @author
 */
@Data
public class ClassifyDTO implements Serializable {
    private Long id;

    /**
     * 类型名称
     */
    private String classifyName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 大图标
     */
    private String bigIcon;

    /**
     * 类别下的品牌信息
     */
    private List<BrandDTO> brandList;
}
