package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Classify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassifyDao {
    /**
     * 根据是否删除和是否上架的来查询类别信息
     */
    List<Classify> listClassifyBy(@Param("status") Integer status,
                                  @Param("isDelete") Integer isDelete);

    int deleteByPrimaryKey(Long id);

    int insert(Classify record);

    int insertSelective(Classify record);

    Classify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Classify record);

    int updateByPrimaryKey(Classify record);
}
