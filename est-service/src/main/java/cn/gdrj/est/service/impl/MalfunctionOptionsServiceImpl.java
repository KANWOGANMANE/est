package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.MalfunctionOptionsDao;
import cn.gdrj.est.dto.MalfunctionOptionsDTO;
import cn.gdrj.est.entity.MalfunctionOptions;
import cn.gdrj.est.service.MalfunctionOptionsService;
import cn.gdrj.est.util.EstBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/15
 */
@Service
public class MalfunctionOptionsServiceImpl implements MalfunctionOptionsService {
    @Autowired
    private MalfunctionOptionsDao malfunctionOptionsDao;
    /**
     * 根据故障问题id查询选项信息（没有被删除的）
     *
     * @param malfId
     * @return
     */
    @Override
    public List<MalfunctionOptionsDTO> listByMalfId(Long malfId) {
        List<MalfunctionOptions> malfunctionOptions =
                malfunctionOptionsDao.listByMalfId(malfId, Constants.EST_NOT);
        return EstBeanUtil.convertTo(malfunctionOptions,MalfunctionOptionsDTO.class);
    }

    @Override
    public List<MalfunctionOptionsDTO> listByIds(List<Long> ids) {
        List<MalfunctionOptions> malfunctionOptions =
                malfunctionOptionsDao.listByIds(ids);
        return EstBeanUtil.convertTo(malfunctionOptions,MalfunctionOptionsDTO.class);
    }
}
