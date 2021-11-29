package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.BrandDao;
import cn.gdrj.est.dto.BrandDTO;
import cn.gdrj.est.entity.Brand;
import cn.gdrj.est.service.BrandService;
import cn.gdrj.est.util.EstBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌业务组件
 *
 * @author jun
 * @date 2021/11/9
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    /**
     * 根据品牌ids信息查询上架的并且没有被删除品牌信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<BrandDTO> listBy(List<Long> ids) {
        List<Brand> brands =
                brandDao.listByIds(ids, Constants.Model.Status.SALE, Constants.EST_NOT);

        return EstBeanUtil.convertTo(brands,BrandDTO.class);
    }
}
