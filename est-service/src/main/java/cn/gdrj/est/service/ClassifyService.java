package cn.gdrj.est.service;

import cn.gdrj.est.dto.ClassifyDTO;

import java.util.List;

/**
 * 商品类别业务接口
 *
 * @author jun
 * @date 2021/11/10
 */
public interface ClassifyService {

    /**
     * 查询所有的上架的,没有被删除的商品类别信息
     * @return
     */
    List<ClassifyDTO> listClassify();

    /**
     * 查询商品类别信息树(上架的,没有被删除的)
     * @return
     */
    List<ClassifyDTO> listClassifyTree();
}
