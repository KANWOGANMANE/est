package cn.gdrj.est.service;

import cn.gdrj.est.dto.ModelDTO;
import cn.gdrj.est.dto.PageResult;
import cn.gdrj.est.param.ModelPageSearchParam;

import java.util.List;

/**
 * 商品业务接口
 *
 * @author jun
 * @date 2021/11/9
 */
public interface ModelService {
    /**
     * 根据类别和品牌分页查询没有被删除和上架的商品信息
     * @param classifyId 类别id
     * @param brandId 品牌id
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return
     */
    List<ModelDTO> listBy(Long classifyId,Long brandId,int pageNo,int pageSize);

    /**
     * 根据分页查询条件查询分页数据
     * @param searchParam
     * @return
     */
    PageResult<ModelDTO> listByPage(ModelPageSearchParam searchParam);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    ModelDTO getById(Long id);
}
