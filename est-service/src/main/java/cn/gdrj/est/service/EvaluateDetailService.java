package cn.gdrj.est.service;

import cn.gdrj.est.dto.EvaluateDetailDTO;
import cn.gdrj.est.entity.EvaluateDetail;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/16
 */
public interface EvaluateDetailService {

    /**
     * 保存评估的详情信息
     * @param evaluateDetails
     * @return
     */
    int addBatchEvaluateDetail(List<EvaluateDetail> evaluateDetails);

    /**
     * 根据评估id查询关联的评估详情记录
     * @param evaluateId 评估id
     * @return
     */
    List<EvaluateDetailDTO> listByEvaluateId(Long evaluateId);
}
