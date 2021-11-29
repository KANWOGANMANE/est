package cn.gdrj.est.service;

import cn.gdrj.est.dto.MalfunctionDTO;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/15
 */
public interface MalfunctionService {
    /**
     * 根据商品型号id查询故障问题及关联的选项问题
     * @param modelId 商品型号
     * @return
     */
    List<MalfunctionDTO> listByModelId(Long modelId);

    /**
     * 根据故障id查询故障信息
     * @param id 故障id
     * @return
     */
    MalfunctionDTO getById(Long id);
}
