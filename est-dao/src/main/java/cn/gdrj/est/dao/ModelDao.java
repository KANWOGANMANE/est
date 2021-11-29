package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Model;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelDao {

    /**
     * 根据条件查询记录数据
     * @param model
     * @return
     */
    int countBy(Model model);

    /**
     * 根据类别和品牌分页查询没有被删除和上架的商品信息
     * @param model 条件对象
     * @param offset 开始位置
     * @param pageSize 每页条数
     * @return
     */
    List<Model> listByPage(@Param("model") Model model,
                           @Param("offset") Integer offset,
                           @Param("pageSize") Integer pageSize);

    int deleteByPrimaryKey(Long id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);
}
