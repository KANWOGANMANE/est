package cn.gdrj.est.service;

import java.util.List;

/**
 * 品牌和类别关系业务接口
 *
 * @author jun
 * @date 2021/11/10
 */
public interface BrandClassifyRelationService {

    /**
     * 根据类别查询没有被删除的品牌id信息
     * @param classifyId
     * @return
     */
    List<Long> listBrandIdByClassifyId(Long classifyId);
}
