package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Malfunction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MalfunctionDao {
    /**
     * 根据商品型号查询故障问题
     * @param modelId
     * @param isDelete
     * @return
     */
    @Select("<script>select * from malfunction" +
            " where " +
            " modelId =#{modelId}" +
            " <if test='isDelete!=null'> and isDelete=#{isDelete}</if>" +
            " </script>")
    List<Malfunction> listByModelId(@Param("modelId") Long modelId,
                                    @Param("isDelete") Integer isDelete);

    int deleteByPrimaryKey(Long id);

    int insert(Malfunction record);

    int insertSelective(Malfunction record);

    Malfunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Malfunction record);

    int updateByPrimaryKey(Malfunction record);
}
