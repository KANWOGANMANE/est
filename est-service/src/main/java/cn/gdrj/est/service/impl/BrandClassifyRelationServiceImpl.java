package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.BrandClassifyRelationDao;
import cn.gdrj.est.service.BrandClassifyRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 牌和类别关系业务组件
 *
 * @author jun
 * @date 2021/11/10
 */
@Service
public class BrandClassifyRelationServiceImpl implements BrandClassifyRelationService {
    @Autowired
    private BrandClassifyRelationDao brandClassifyRelationDao;

    /**
     * 根据类别查询没有被删除的品牌id信息
     *
     * @param classifyId
     * @return
     */
    @Override
    public List<Long> listBrandIdByClassifyId(Long classifyId) {
        return brandClassifyRelationDao
                .listBrandIdByClassifyIdAndIsDelete(classifyId, Constants.EST_NOT);
    }
}
