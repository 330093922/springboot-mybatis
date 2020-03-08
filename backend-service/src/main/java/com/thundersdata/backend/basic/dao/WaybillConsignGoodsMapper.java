package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillConsignGoodsDTO;
import com.thundersdata.backend.basic.model.WaybillConsignGoods;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsDispatchVO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WaybillConsignGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillConsignGoods record);

    int insertSelective(WaybillConsignGoods record);

    WaybillConsignGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillConsignGoods record);

    int updateByPrimaryKeyWithBLOBs(WaybillConsignGoods record);

    int updateByPrimaryKey(WaybillConsignGoods record);

    /**
     * 批量插入托运单货物关联信息
     * @param list
     * @return
     */
    int insertBatch(List<WaybillConsignGoodsVO> list);

    /**
     * 根据托运单id,删除商品信息
     * @param consignId
     * @return
     */
    int deleteByConsignId(Integer consignId);

    /**
     * 根据托运单id,逻辑删除删除商品信息
     * @param consignId
     * @return
     */
    @Update("update waybill_consign_goods set is_deleted = 1 where consign_id = #{consignId}")
    int deleteLogicByConsignId(Integer consignId);

    /**
     * 根据托运单id,查询托运单关联的货物信息
     * @param consignId
     * @return
     */
    @Select("select cg.id, cg.goods_id goodsId, cg.consign_id consignId, cg.surplus_no surplusNo, cg.total_no totalNo,cg.remark, cg.created_at createdAt, cg.updated_at updatedAt, cg.is_deleted isDeleted," +
            " goods.un_no unNo, goods.name goodsName, goods.type goodsType, goods.packaging_type packagingType, goods.number goodsDensity from waybill_consign_goods cg " +
            " left join waybill_goods goods on cg.goods_id=goods.id  where cg.consign_id = #{consignId}")
    List<WaybillConsignGoodsDTO> selectByConsignId(@Param("consignId") Integer consignId);

    /**
     * 修改托运单关联货物的剩余数量
     * @param consignGoodsId
     * @param surplusNo
     * @return
     */
    @Update("update waybill_consign_goods set surplus_no = #{surplusNo} where id = #{consignGoodsId}")
    int updateWaybillConsignGoodsSurplusNo(@Param("consignGoodsId") Integer consignGoodsId, @Param("surplusNo") Integer surplusNo);


    /**
     *查询托运单剩余派送总数量
     * @param consignGoodsId
     * @return
     */
    int getSumSurplusNoByConsignId(@Param("consignGoodsId") Integer consignGoodsId);
}