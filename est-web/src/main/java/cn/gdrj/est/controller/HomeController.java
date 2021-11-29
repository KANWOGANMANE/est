package cn.gdrj.est.controller;

import cn.gdrj.est.common.util.ResultUtil;
import cn.gdrj.est.dto.ClassifyDTO;
import cn.gdrj.est.dto.Result;
import cn.gdrj.est.service.ClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 主页控制器
 *
 * @author jun
 * @date 2021/11/10
 */
@Api(tags = "主页API")
@RestController
public class HomeController {

    @Autowired
    private ClassifyService classifyService;

    @ApiOperation("获取商品类别信息树")
    @GetMapping("/api/home/classifyTree")
    public Result<List<ClassifyDTO>> getClassifyTree(){
        List<ClassifyDTO> classifyDTOS = classifyService.listClassifyTree();
        return ResultUtil.success(classifyDTOS);
    }
}
