package cn.gdrj.est.dao;

import cn.gdrj.est.entity.Malfunction;
import cn.gdrj.est.entity.MalfunctionOptions;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MalfunctionOptionsDao {
    /**
     * 故障问题id查询选项信息
     * @param malfId
     * @param isDelete
     * @return
     */
    @Select("<script>" +
            " select * from malfunction_options" +
            " where " +
            " malfId = #{malfId}" +
            " <if test='isDelete!=null'> and isDelete=#{isDelete}</if>" +
            " </script>")
    List<MalfunctionOptions> listByMalfId(@Param("malfId") Long malfId,
                                    @Param("isDelete") Integer isDelete);

    List<MalfunctionOptions> listByIds(List<Long> ids);

    int deleteByPrimaryKey(Long id);

    int insert(MalfunctionOptions record);

    int insertSelective(MalfunctionOptions record);

    MalfunctionOptions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MalfunctionOptions record);

    int updateByPrimaryKey(MalfunctionOptions record);
}
