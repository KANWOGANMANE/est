package cn.gdrj.est.controller;

import cn.gdrj.est.annotations.TokenUser;
import cn.gdrj.est.common.BaseController;
import cn.gdrj.est.common.util.ResultUtil;
import cn.gdrj.est.dto.Result;
import cn.gdrj.est.dto.SimpleOrderDTO;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.SubmitMaintainOrderParam;
import cn.gdrj.est.service.MaintainOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
@RestController
public class MaintainOrderController extends BaseController {

    @Autowired
    private MaintainOrderService maintainOrderService;

    @PostMapping("/api/order/maintain/submit")
    public Result<SimpleOrderDTO> submitOrder(@Valid SubmitMaintainOrderParam orderParam,
                                              @TokenUser Users users){

        SimpleOrderDTO orderDTO = maintainOrderService.submit(orderParam, users);

        return ResultUtil.success(orderDTO);
    }
}
