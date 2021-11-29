package cn.gdrj.est.controller;

import cn.gdrj.est.annotations.TokenUser;
import cn.gdrj.est.common.util.ResultUtil;
import cn.gdrj.est.dto.EvaluateDTO;
import cn.gdrj.est.dto.EvaluateDetailInfoDTO;
import cn.gdrj.est.dto.MalfunctionDTO;
import cn.gdrj.est.dto.Result;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.param.AssessParam;
import cn.gdrj.est.service.EvaluateService;
import cn.gdrj.est.service.MalfunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/15
 */
@RestController
@Validated
public class EvaluateController {

    @Autowired
    private MalfunctionService malfunctionService;
    @Autowired
    private EvaluateService evaluateService;

    @GetMapping("/api/evaluate/specList")
    public Result<List<MalfunctionDTO>> evaluateSpecList(
            @NotNull(message = "商品型号id不能为空")
            @Min(value = 1,message = "id值不能小于1")
                    Long modelId){

        List<MalfunctionDTO> malfunctionDTOS =
                malfunctionService.listByModelId(modelId);
        return ResultUtil.success(malfunctionDTOS);
    }

    @PostMapping("/api/evaluate/assess")
    public Result<EvaluateDTO> assess(@Valid AssessParam assessParam,
                                      @TokenUser Users users){
        EvaluateDTO evaluateDTO = evaluateService.assess(assessParam, users);
        return ResultUtil.success(evaluateDTO);
    }


    @GetMapping("/api/evaluate/{id}/assessInfo")
    public Result<EvaluateDetailInfoDTO> getAssessInfo(@PathVariable Long id){
        EvaluateDetailInfoDTO evaluateDTO = evaluateService.getAssessInfo(id);
        return ResultUtil.success(evaluateDTO);
    }
}
