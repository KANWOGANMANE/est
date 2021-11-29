package cn.gdrj.est.controller;

import cn.gdrj.est.common.util.ResultUtil;
import cn.gdrj.est.dto.ModelDTO;
import cn.gdrj.est.dto.PageResult;
import cn.gdrj.est.dto.Result;
import cn.gdrj.est.param.ModelPageSearchParam;
import cn.gdrj.est.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品控制器
 *
 * @author jun
 * @date 2021/11/10
 */
@Api(tags="商品API")
@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    @ApiOperation("根据条件分页查询商品信息")
    @GetMapping("/api/model/search")
    public Result<PageResult<ModelDTO>> search(ModelPageSearchParam searchParam){
        PageResult<ModelDTO> pageResult = modelService.listByPage(searchParam);
        return ResultUtil.success(pageResult);
    }

    @ApiOperation("根据id查询商品信息")
    @GetMapping("/api/model/{id}/detail")
    public Result<ModelDTO> getById(@PathVariable Long id){
        ModelDTO modelDTO = modelService.getById(id);
        return ResultUtil.success(modelDTO);
    }
}
