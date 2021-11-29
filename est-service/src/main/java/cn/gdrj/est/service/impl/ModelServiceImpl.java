package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.ModelDao;
import cn.gdrj.est.dto.ModelDTO;
import cn.gdrj.est.dto.PageResult;
import cn.gdrj.est.entity.Model;
import cn.gdrj.est.param.ModelPageSearchParam;
import cn.gdrj.est.service.ModelService;
import cn.gdrj.est.util.EstBeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品业务组件
 *
 * @author jun
 * @date 2021/11/9
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Value("${oss.url}")
    private String ossUrl;

    @Autowired
    private ModelDao modelDao;

    @Override
    public List<ModelDTO> listBy(Long classifyId, Long brandId, int pageNo, int pageSize) {

        // 查询条件
        Model model = new Model();
        model.setClassifyId(classifyId);
        model.setBrandId(brandId);
        // 上架的
        model.setStatus(Constants.Model.Status.SALE);
        // 没有被删除的
        model.setIsDelete(Constants.EST_NOT);

        List<Model> models = modelDao.listByPage(model, (pageNo - 1) * pageSize, pageSize);

        // List<ModelDTO> modelDTOS = new ArrayList<>();
        // for (Model m : models) {
        //     ModelDTO dto = new ModelDTO();
        //     BeanUtils.copyProperties(m, dto);
        //     modelDTOS.add(dto);
        // }
        // return modelDTOS;
        return EstBeanUtil.convertTo(models,ModelDTO.class);
    }

    /**
     * 根据分页查询条件查询分页数据
     * @param searchParam
     * @return
     */
    @Override
    public PageResult<ModelDTO> listByPage(ModelPageSearchParam searchParam) {
        // 1. 根据条件查询数量
        Model model = new Model();
        model.setModelName(searchParam.getModelName());
        model.setClassifyId(searchParam.getClassifyId());
        model.setBrandId(searchParam.getBrandId());
        model.setStatus(Constants.Model.Status.SALE);
        model.setIsDelete(Constants.EST_NOT);
        int count = modelDao.countBy(model);
        Integer pageNo = searchParam.getPageNo();
        Integer pageSize = searchParam.getPageSize();
        PageResult<ModelDTO> pageResult = new PageResult<>(pageSize,count);
        pageResult.setPageNo(pageNo);
        // 2. 根据条件查询数据
        if(count > 0){
            int offset = (pageNo -1)* pageSize;
            List<Model> models = modelDao.listByPage(model, offset, pageSize);
            List<ModelDTO> modelDTOS = new ArrayList<>();
            for (Model m : models) {
                ModelDTO modelDTO = convertToModelDTO(m);
                modelDTOS.add(modelDTO);
            }
            pageResult.setList(modelDTOS);
        }
        // 3. 返回结果
        return pageResult;
    }

    private ModelDTO convertToModelDTO(Model m) {
        ModelDTO modelDTO = EstBeanUtil.convertTo(m, ModelDTO.class);
        // 给图片地址拼接oss的url
        modelDTO.setFaceImg(ossUrl + modelDTO.getFaceImg());
        modelDTO.setContentImg(ossUrl + modelDTO.getContentImg());
        return modelDTO;
    }

    @Override
    public ModelDTO getById(Long id) {
        Model model = modelDao.selectByPrimaryKey(id);
        return  convertToModelDTO(model);
    }


}
