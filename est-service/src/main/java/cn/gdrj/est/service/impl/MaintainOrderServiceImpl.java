package cn.gdrj.est.service.impl;

import cn.gdrj.est.common.exception.BusinessException;
import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.constants.MaintainStatusEnum;
import cn.gdrj.est.constants.ResultEnum;
import cn.gdrj.est.dao.MaintainOrderDao;
import cn.gdrj.est.dto.EvaluateDetailInfoDTO;
import cn.gdrj.est.dto.MaintainOrderDTO;
import cn.gdrj.est.dto.SimpleOrderDTO;
import cn.gdrj.est.entity.Appointment;
import cn.gdrj.est.entity.MaintainOrder;
import cn.gdrj.est.entity.MaintainOrderFlow;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.SubmitMaintainOrderParam;
import cn.gdrj.est.service.AppointmentService;
import cn.gdrj.est.service.EvaluateService;
import cn.gdrj.est.service.MaintainOrderFlowService;
import cn.gdrj.est.service.MaintainOrderService;
import cn.gdrj.est.util.EstBeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.xml.ws.soap.Addressing;
import java.util.Date;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
@Service
public class MaintainOrderServiceImpl implements MaintainOrderService {
    @Autowired
    private MaintainOrderDao maintainOrderDao;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private MaintainOrderFlowService maintainOrderFlowService;

    @Transactional
    @Override
    public SimpleOrderDTO submit(SubmitMaintainOrderParam orderParam, Users users) {
        // 根据评估id获取评估信息（商品id）
        EvaluateDetailInfoDTO assessInfo =
                evaluateService.getAssessInfo(orderParam.getEvaluateId());
        if(ObjectUtils.isEmpty(assessInfo)){
            throw new BusinessException(ResultEnum.FAIL_PARAM);
        }
        // 保存预约信息
        Long usersId = users.getId();
        saveAppointment(orderParam, usersId);
        // 保存维修订单信息
        MaintainOrder maintainOrder =
                saveMaintainOrder(orderParam, assessInfo, usersId);
        // 保存维修订单
        // 生成订单编号： yyyyMMddHHmmss + 四位随机数字 + 10位订单id
        String orderNo = generateOrderNo(maintainOrder);
        maintainOrder.setOrderNo(orderNo);
        //  更新订单编号
        maintainOrderDao.updateOrderNoById(maintainOrder.getId(),orderNo);
        // 保存维修订单的流水记录
        saveMaintainOrderFlow(maintainOrder);
        SimpleOrderDTO orderDTO = new SimpleOrderDTO();
        orderDTO.setOrderNo(orderNo);
        return orderDTO;
    }

    @Override
    public MaintainOrderDTO getMaintainOrderBy(String orderNo) {
        MaintainOrder maintainOrder = maintainOrderDao.getMaintainOrderBy(orderNo);
        return EstBeanUtil.convertTo(maintainOrder,MaintainOrderDTO.class);
    }

    @Override
    public int updateStatusToPaySubscription(String orderNo) {
        MaintainOrder maintainOrder = maintainOrderDao.getMaintainOrderBy(orderNo);
        maintainOrder.setStatus(Constants.Order.MaintainStatus.PAY_DEPOSIT);
        int count = maintainOrderDao.updateStatusById(maintainOrder.getId(),
                maintainOrder.getStatus());
        // 添加维修订单的流水记录
        saveMaintainOrderFlow(maintainOrder);
        return count;
    }

    private void saveMaintainOrderFlow(MaintainOrder maintainOrder) {
        MaintainOrderFlow  flow = new MaintainOrderFlow();
        flow.setOrderNo(maintainOrder.getOrderNo());
        Integer code = maintainOrder.getStatus();
        MaintainStatusEnum statusEnum = MaintainStatusEnum.getByCode(code);
        flow.setOrderStatus(statusEnum.getCode());
        flow.setOrderStatusDesc(statusEnum.getMsg());
        maintainOrderFlowService.addMaintainOrderFlow(flow);
    }

    private String generateOrderNo(MaintainOrder maintainOrder) {
        StringBuilder builder = new StringBuilder();
        builder.append(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
        builder.append(RandomUtil.randomNumbers(4));
        Long id = maintainOrder.getId();
        // 10 ==> 0000 0000 10
        String strId = StrUtil.padPre(id.toString(), 10, '0');
        builder.append(strId);
        return builder.toString();
    }

    private MaintainOrder saveMaintainOrder(SubmitMaintainOrderParam orderParam, EvaluateDetailInfoDTO assessInfo, Long usersId) {
        MaintainOrder maintainOrder = new MaintainOrder();
        // maintainOrder.setOrderNo();
        maintainOrder.setUserId(usersId);
        maintainOrder.setModelId(assessInfo.getModel().getId());
        maintainOrder.setEvaluateId(orderParam.getEvaluateId());
        maintainOrder.setSubscription(assessInfo.getSubscription());
        maintainOrder.setPrice(assessInfo.getPrice());
        maintainOrder.setStatus(Constants.Order.MaintainStatus.APPOINT);
        maintainOrder.setCreatedUserId(usersId);
        maintainOrder.setCreatedTime(new Date());
        maintainOrder.setIsComment(Constants.EST_NOT);
        maintainOrder.setIsDelete(Constants.EST_NOT);
        maintainOrderDao.insert(maintainOrder);
        return maintainOrder;
    }

    private void saveAppointment(SubmitMaintainOrderParam orderParam, Long usersId) {
        Appointment  appointment = new Appointment();
        appointment.setUserid(usersId);
        appointment.setPhone(orderParam.getPhone());
        appointment.setAppintdate(orderParam.getAppintDate());
        appointment.setTemporalinterval(orderParam.getTemporalInterval());
        appointment.setAdress(orderParam.getAdress());
        appointment.setCreateduserid(usersId);
        appointmentService.addAppointment(appointment);
    }
}
