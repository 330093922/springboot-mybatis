package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.DictDTO;
import com.thundersdata.backend.basic.dto.WaybillGoodsDTO;
import com.thundersdata.backend.basic.model.WaybillGoods;
import com.thundersdata.backend.common.utils.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillGoods record);

    int insertSelective(WaybillGoods record);

    WaybillGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillGoods record);

    int updateByPrimaryKeyWithBLOBs(WaybillGoods record);

    int updateByPrimaryKey(WaybillGoods record);

    /**
     * 分页查询危险品货物信息
     * @param page
     * @param unNo
     * @param name
     * @param alias
     * @return
     */
    List<WaybillGoodsDTO> selectWaybillGoods(@Param("page") PageParam page, @Param("unNo") String unNo, @Param("name") String name, @Param("alias") String alias);

    /**
     * 获取分页查询危险品货物信息的条数
     * @param unNo
     * @param name
     * @param alias
     * @return
     */
    int countWaybillGoods(@Param("unNo") String unNo, @Param("name") String name, @Param("alias") String alias);

    /**
     * 分页查询危险品货物信息
     * @param value
     * @return
     */
    List<DictDTO> selectWaybillGoodsCombo(@Param("value") String value);

    /**
     * 根据主键数组,批量设置危险化学品货物信息为已删除状态
     * @param ids
     * @return
     */
    int updateWaybillGoodsDeleteedBatch(@Param("ids") Integer[] ids);
}