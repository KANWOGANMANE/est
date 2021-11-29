package cn.gdrj.est.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * brand
 * @author
 */
@Data
public class BrandDTO implements Serializable {
    private Long id;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌下的商品信息
     */
    private List<ModelDTO> modelList;
}
