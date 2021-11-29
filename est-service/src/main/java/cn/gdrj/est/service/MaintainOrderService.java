package cn.gdrj.est.service;

import cn.gdrj.est.dto.MaintainOrderDTO;
import cn.gdrj.est.dto.SimpleOrderDTO;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.SubmitMaintainOrderParam;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
public interface MaintainOrderService {
    SimpleOrderDTO submit(SubmitMaintainOrderParam orderParam, Users users);

    MaintainOrderDTO getMaintainOrderBy(String orderNo);

    int updateStatusToPaySubscription(String orderNo);
}
