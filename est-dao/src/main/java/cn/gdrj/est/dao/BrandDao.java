package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandDao {

    /**
     * 根据品牌ids信息查询没有被删除和上架的品牌信息
     * @param ids
     * @param status
     * @param isDelete
     * @return
     */
    List<Brand> listByIds(@Param("ids") List<Long> ids,
                          @Param("status") Integer status,
                          @Param("isDelete") Integer isDelete);

    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
}
