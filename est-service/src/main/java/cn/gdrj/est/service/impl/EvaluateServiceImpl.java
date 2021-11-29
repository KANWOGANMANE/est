package cn.gdrj.est.service.impl;

import cn.gdrj.est.common.exception.BusinessException;
import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.constants.ResultEnum;
import cn.gdrj.est.dao.EvaluateDao;
import cn.gdrj.est.dto.*;
import cn.gdrj.est.entity.Evaluate;
import cn.gdrj.est.entity.EvaluateDetail;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.AssessParam;
import cn.gdrj.est.service.*;
import cn.gdrj.est.util.EstBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/16
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {
    @Autowired
    private EvaluateDao evaluateDao;
    @Autowired
    private EvaluateDetailService evaluateDetailService;
    @Autowired
    private MalfunctionOptionsService malfunctionOptionsService;
    @Autowired
    private MalfunctionService malfunctionService;
    @Autowired
    private ModelService modelService;
    /**
     * 保存评估信息
     *
     * @param evaluate
     * @return
     */
    @Override
    public int addEvaluate(Evaluate evaluate) {
        evaluate.setCreatedTime(new Date());
        evaluate.setIsDelete(Constants.EST_NOT);
        return evaluateDao.insert(evaluate);
    }

    /**
     * 估计
     *
     * @param assessParam
     * @param users
     * @return
     */
    @Transactional
    @Override
    public EvaluateDTO assess(AssessParam assessParam, Users users) {
        // 评估价： 每项故障维修最高价 * 每项维修选项的维修占比
        List<Long> optionIds = assessParam.getOptionIds();
        List<MalfunctionOptionsDTO> optionsDTOS =
                malfunctionOptionsService.listByIds(optionIds);
        // 维修总价
        // 	根据维修选项查询故障维修最高价
        BigDecimal total = BigDecimal.ZERO;
        for (MalfunctionOptionsDTO optionsDTO : optionsDTOS) {
            // 维修方式
            Integer processType = optionsDTO.getProcessType();
            // 维修占比
            Integer ratio = optionsDTO.getRatio();
            // 故障选项所属的故障id
            Long malfId = optionsDTO.getMalfId();
            // 查询故障信息
            MalfunctionDTO malfunctionDTO = malfunctionService.getById(malfId);
            // 维修最高价
            BigDecimal topPrice = malfunctionDTO.getMaintainTopPrice();
            // 小计
            BigDecimal smallTotal = topPrice.multiply(new BigDecimal(ratio)).divide(new BigDecimal(100));
            // 累计总价
            total  = total.add(smallTotal);
        }
        // 定金是评估价格的十分之一
        BigDecimal subscription = total.multiply(new BigDecimal("0.1"));
        // 保存评估信息
        Evaluate evaluate = new Evaluate();
        evaluate.setModelId(assessParam.getModelId());
        evaluate.setSubscription(subscription);
        evaluate.setPrice(total);
        evaluate.setRemark("");
        evaluate.setCreatedUserId(users.getId());
        this.addEvaluate(evaluate);
        // 保存评估的详情信息
        // 根据故障选项信息来创建评估详情信息
        Long evaluateId = evaluate.getId();
        List<EvaluateDetail> evaluateDetails = new ArrayList<>();
        for (MalfunctionOptionsDTO optionsDTO : optionsDTOS) {
            EvaluateDetail evaluateDetail = new EvaluateDetail();
            evaluateDetail.setEvaluateId(evaluateId);
            evaluateDetail.setMalfId(optionsDTO.getMalfId());
            evaluateDetail.setOptionId(optionsDTO.getId());
            evaluateDetail.setOptionName(optionsDTO.getOptionName());
            evaluateDetail.setCreatedUserId(users.getId());
            evaluateDetail.setCreatedTime(new Date());
            evaluateDetail.setIsDelete(Constants.EST_NOT);
            evaluateDetails.add(evaluateDetail);
        }
        evaluateDetailService.addBatchEvaluateDetail(evaluateDetails);
        return EstBeanUtil.convertTo(evaluate,EvaluateDTO.class);
    }

    @Override
    public EvaluateDetailInfoDTO getAssessInfo(Long evaluateId) {
        Evaluate evaluate = evaluateDao.selectByPrimaryKey(evaluateId);
        if(ObjectUtils.isEmpty(evaluate)){
            throw new BusinessException(ResultEnum.FAIL_PARAM);
        }

        EvaluateDetailInfoDTO detailInfoDTO =
                EstBeanUtil.convertTo(evaluate, EvaluateDetailInfoDTO.class);

        ModelDTO modelDTO = modelService.getById(evaluate.getModelId());
        detailInfoDTO.setModel(modelDTO);

        List<EvaluateDetailDTO> evaluateDetailDTOS =
                evaluateDetailService.listByEvaluateId(evaluateId);
        detailInfoDTO.setDetailList(evaluateDetailDTOS);

        return detailInfoDTO;
    }
}
