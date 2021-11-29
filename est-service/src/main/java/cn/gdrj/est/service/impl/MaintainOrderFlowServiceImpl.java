package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.MaintainOrderFlowDao;
import cn.gdrj.est.entity.MaintainOrderFlow;
import cn.gdrj.est.service.MaintainOrderFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
@Service
public class MaintainOrderFlowServiceImpl implements MaintainOrderFlowService {
    @Autowired
    private MaintainOrderFlowDao maintainOrderFlowDao;
    @Override
    public int addMaintainOrderFlow(MaintainOrderFlow flow) {
        flow.setCreatedTime(new Date());
        flow.setIsDelete(Constants.EST_NOT);
        return maintainOrderFlowDao.insert(flow);
    }
}
