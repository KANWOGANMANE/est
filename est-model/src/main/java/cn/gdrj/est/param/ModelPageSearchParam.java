package cn.gdrj.est.param;

import lombok.Data;

/**
 * 商品分页查询条件对象
 *
 * @author jun
 * @date 2021/11/10
 */
@Data
public class ModelPageSearchParam {
    private Long classifyId;
    private Long brandId;
    private String modelName;
    private Integer pageNo = 1 ;
    private Integer pageSize = 20;
}
