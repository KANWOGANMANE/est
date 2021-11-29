package cn.gdrj.est.service;

import cn.gdrj.est.dto.BrandDTO;

import java.util.List;

/**
 * 品牌业务接口
 *
 * @author jun
 * @date 2021/11/9
 */
public interface BrandService {
    /**
     * 根据品牌ids信息查询上架的并且没有被删除品牌信息
     * @param ids
     * @return
     */
    List<BrandDTO> listBy(List<Long> ids);
}
