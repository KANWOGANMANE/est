package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.ClassifyDao;
import cn.gdrj.est.dto.BrandDTO;
import cn.gdrj.est.dto.ClassifyDTO;
import cn.gdrj.est.dto.ModelDTO;
import cn.gdrj.est.entity.Classify;
import cn.gdrj.est.service.BrandClassifyRelationService;
import cn.gdrj.est.service.BrandService;
import cn.gdrj.est.service.ClassifyService;
import cn.gdrj.est.service.ModelService;
import cn.gdrj.est.util.EstBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 商品类别业务组件
 *
 * @author jun
 * @date 2021/11/10
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    private ClassifyDao classifyDao;
    @Autowired
    private BrandClassifyRelationService brandClassifyRelationService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 查询所有的上架的,没有被删除的商品类别信息
     *
     * @return
     */
    @Override
    public List<ClassifyDTO> listClassify() {
        List<Classify> classifies =
                classifyDao.listClassifyBy(Constants.Model.Status.SALE, Constants.EST_NOT);
        return EstBeanUtil.convertTo(classifies, ClassifyDTO.class);
    }

    /**
     * 查询商品类别信息树(上架的,没有被删除的)
     *
     * @return
     */
    @Override
    public List<ClassifyDTO> listClassifyTree() {

        List<ClassifyDTO> classifyDTOS = (List<ClassifyDTO>) redisTemplate
                .opsForValue()
                .get(Constants.Redis.KEY_HOME_TREE);
        if(!ObjectUtils.isEmpty(classifyDTOS)){
            return classifyDTOS;
        }

        // 查询类别信息
       classifyDTOS = listClassify();
        for (ClassifyDTO classifyDTO : classifyDTOS) {
            // 根据类别信息查询品牌id信息
            Long classifyId = classifyDTO.getId();
            List<Long> brandIds = brandClassifyRelationService.listBrandIdByClassifyId(classifyId);

            // 根据品牌id信息查询品牌信息
            List<BrandDTO> brandDTOS = brandService.listBy(brandIds);
            for (BrandDTO brandDTO : brandDTOS) {
                Long brandId = brandDTO.getId();
                // 根据类别和品牌查询商品信息
                List<ModelDTO> modelDTOS = modelService.listBy(classifyId, brandId, 1, 5);

                // 组装到品牌对象中
                brandDTO.setModelList(modelDTOS);
            }

            // 把品牌信息组装到类别信息中
            classifyDTO.setBrandList(brandDTOS);
        }

        // 保存到缓存中
        redisTemplate.opsForValue()
                .set(Constants.Redis.KEY_HOME_TREE,classifyDTOS,1, TimeUnit.DAYS);

        return classifyDTOS;
    }
}
