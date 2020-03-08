package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.dto.DictDTO;
import com.thundersdata.backend.basic.dto.WaybillGoodsDTO;
import com.thundersdata.backend.basic.dto.WaybillGoodsDetailDTO;
import com.thundersdata.backend.basic.vo.WaybillGoodsDetailVO;
import com.thundersdata.backend.basic.vo.WaybillGoodsVO;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.Pagination;

import java.util.List;

/**
 * 危险化学品货物管理
 */
public interface WaybillGoodsService {

    /**
     * 新增危险化学品货物信息
     * @param waybillGoodsVo
     * @return
     */
    int insertWaybillGoods(WaybillGoodsVO waybillGoodsVo);

    /**
     * 修改危险化学品货物信息
     * @param waybillGoodsVo
     * @return
     */
    int updateWaybillGoods(WaybillGoodsVO waybillGoodsVo);

    /**
     * 根据主键查询危险化学品货物信息
     * @param id
     * @return
     */
    WaybillGoodsDTO selectWaybillGoods(Integer id);
    /**
     * 根据主键查询危险化学品货物信息
     * @param id
     * @return
     */
    WaybillGoodsDetailDTO selectWaybillGoodsDetail(Integer id);

    /**
     * 根据主键修改危险化学品货物信息为删除状态
     * @param id
     * @return
     */
    int updateWaybillGoodsDeleteed(Integer id);


    /**
     * 根据主键数组,批量修改危险化学品货物为删除状态
     * @param ids
     * @return
     */
    int updateWaybillGoodsDeleteedBatch(Integer[] ids);

    /**
     * 修改危险化学品货物关联信息
     * @param waybillGoodsDetailVo
     * @return
     */
    int updateWaybillGoodsDetail(WaybillGoodsDetailVO waybillGoodsDetailVo);

    /**
     * 分页查询危险化学品货物信息
     * @param page
     * @param pageSize
     * @param unNo
     * @param name
     * @param alias
     * @return
     */
    Pagination<WaybillGoodsDTO> selectWaybillGoods(Integer page, Integer pageSize, String unNo, String name, String alias);

    /**
     * 查询危险化学货物信息下拉框
     * @param value
     * @return
     */
    List<DictDTO> selectWaybillGoodsCombo(String value);
}
