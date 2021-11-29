package cn.gdrj.est.service.impl;

import cn.gdrj.est.dao.EvaluateDetailDao;
import cn.gdrj.est.dto.EvaluateDetailDTO;
import cn.gdrj.est.entity.EvaluateDetail;
import cn.gdrj.est.service.EvaluateDetailService;
import cn.gdrj.est.util.EstBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/16
 */
@Service
public class EvaluateDetailServiceImpl implements EvaluateDetailService {
    @Autowired
    private EvaluateDetailDao evaluateDetailDao;
    /**
     * 保存评估的详情信息
     *
     * @param evaluateDetails
     * @return
     */
    @Override
    public int addBatchEvaluateDetail(List<EvaluateDetail> evaluateDetails) {
        return evaluateDetailDao.batchInsert(evaluateDetails);
    }

    @Override
    public List<EvaluateDetailDTO> listByEvaluateId(Long evaluateId) {
        List<EvaluateDetail> evaluateDetails =
                evaluateDetailDao.listByEvaluateId(evaluateId);
        return EstBeanUtil.convertTo(evaluateDetails,EvaluateDetailDTO.class);
    }
}
