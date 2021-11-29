package cn.gdrj.est.dao;

import cn.gdrj.est.entity.EvaluateDetail;

import java.util.List;

public interface EvaluateDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateDetail record);

    int insertSelective(EvaluateDetail record);

    EvaluateDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateDetail record);

    int updateByPrimaryKey(EvaluateDetail record);

    /**
     * 批量保存评估详情信息
     * @param evaluateDetails
     * @return
     */
    int batchInsert(List<EvaluateDetail> evaluateDetails);

    /**
     * 根据评估id查询关联的评估详情记录
     * @param evaluateId 评估id
     * @return
     */
    List<EvaluateDetail> listByEvaluateId(Long evaluateId);
}
