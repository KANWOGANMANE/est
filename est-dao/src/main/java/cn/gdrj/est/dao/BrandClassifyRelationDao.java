package cn.gdrj.est.dao;

import cn.gdrj.est.entity.BrandClassifyRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandClassifyRelationDao {

    /**
     * 根据类别查询是否被删除的品牌id信息
     */
    List<Long> listBrandIdByClassifyIdAndIsDelete(@Param("classifyId") Long classifyId,
                                                  @Param("isDelete") Integer isDelete);

    int deleteByPrimaryKey(Long id);

    int insert(BrandClassifyRelation record);

    int insertSelective(BrandClassifyRelation record);

    BrandClassifyRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BrandClassifyRelation record);

    int updateByPrimaryKey(BrandClassifyRelation record);
}
