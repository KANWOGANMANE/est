package cn.gdrj.est.service;

import cn.gdrj.est.dto.EvaluateDTO;
import cn.gdrj.est.dto.EvaluateDetailInfoDTO;
import cn.gdrj.est.entity.Evaluate;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.AssessParam;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/16
 */
public interface EvaluateService {
    /**
     * 保存评估信息
     * @param evaluate
     * @return
     */
    int addEvaluate(Evaluate evaluate);

    /**
     * 估计
     * @param assessParam
     * @param users
     * @return
     */
    EvaluateDTO assess(AssessParam assessParam, Users users);

    /**
     * 根据ID查询评估详情信息
     * @param evaluateId
     * @return
     */
    EvaluateDetailInfoDTO getAssessInfo(Long evaluateId);
}
