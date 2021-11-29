package cn.gdrj.est.service;

import cn.gdrj.est.dto.MalfunctionOptionsDTO;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/15
 */
public interface MalfunctionOptionsService {

    /**
     * 根据故障问题id查询选项信息（没有被删除的）
     * @param malfId
     * @return
     */
    List<MalfunctionOptionsDTO> listByMalfId(Long malfId);

    /**
     * 根据ids查询故障选项信息
     * @param ids
     * @return
     */
    List<MalfunctionOptionsDTO> listByIds(List<Long> ids);
}
