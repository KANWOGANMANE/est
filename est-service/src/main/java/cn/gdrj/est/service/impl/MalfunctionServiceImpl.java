package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.MalfunctionDao;
import cn.gdrj.est.dto.MalfunctionDTO;
import cn.gdrj.est.dto.MalfunctionOptionsDTO;
import cn.gdrj.est.entity.Malfunction;
import cn.gdrj.est.service.MalfunctionOptionsService;
import cn.gdrj.est.service.MalfunctionService;
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
public class MalfunctionServiceImpl implements MalfunctionService {
    @Autowired
    private MalfunctionDao malfunctionDao;
    @Autowired
    private MalfunctionOptionsService malfunctionOptionsService;
    /**
     * 根据商品型号id查询故障问题及关联的选项问题
     *
     * @param modelId 商品型号
     * @return
     */
    @Override
    public List<MalfunctionDTO> listByModelId(Long modelId) {
        List<MalfunctionDTO> malfunctionDTOS = listMalFunctionByModelId(modelId);

        for (MalfunctionDTO malfunctionDTO : malfunctionDTOS) {
            Long malfId = malfunctionDTO.getId();
            List<MalfunctionOptionsDTO> optionsDTOS =
                    malfunctionOptionsService.listByMalfId(malfId);
            malfunctionDTO.setOptionsList(optionsDTOS);

        }
        return malfunctionDTOS;
    }

    @Override
    public MalfunctionDTO getById(Long id) {
        Malfunction malfunction = malfunctionDao.selectByPrimaryKey(id);
        return EstBeanUtil.convertTo(malfunction,MalfunctionDTO.class);
    }

    public List<MalfunctionDTO> listMalFunctionByModelId(Long modelId) {
        List<Malfunction> malfunctions =
                malfunctionDao.listByModelId(modelId, Constants.EST_NOT);
        return EstBeanUtil.convertTo(malfunctions,MalfunctionDTO.class);
    }
}
