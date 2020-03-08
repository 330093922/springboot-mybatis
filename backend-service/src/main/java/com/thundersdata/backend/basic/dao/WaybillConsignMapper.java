package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillConsignDTO;
import com.thundersdata.backend.basic.model.WaybillConsign;
import com.thundersdata.backend.basic.model.WaybillConsignWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillConsignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillConsignWithBLOBs record);

    int insertSelective(WaybillConsignWithBLOBs record);

    WaybillConsignWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillConsignWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WaybillConsignWithBLOBs record);

    int updateByPrimaryKey(WaybillConsign record);

    /**
     * 根据主键逻辑删除
     *
     * @param id
     * @return
     */
    int deleteLogicByPrimaryKey(@Param("id") Integer id,
                                @Param("ownerIds") Integer[] ownerIds,
                                @Param("isScope") Boolean isScope);

    /**
     * 根据主键查询托运单
     *
     * @param id
     * @return
     */
    WaybillConsignDTO getWaybillConsignById(Integer id);

    /**
     * 查询最大运单
     *
     * @param creditCode
     * @return
     */
    String selectByConsignCode(@Param("creditCode") String creditCode);

    /**
     * 分页查询托运单
     *
     * @param consignCode
     * @param limit
     * @param offset
     * @param ownerIds
     * @param isScope
     * @return
     */
    List<WaybillConsignDTO> selectConsignList(@Param("consignCode") String consignCode,
                                              @Param("owner") String owner,
                                              @Param("states") List<Integer> states,
                                              @Param("limit") Integer limit,
                                              @Param("offset") Integer offset,
                                              @Param("ownerIds") Integer[] ownerIds,
                                              @Param("isScope") Boolean isScope);

    /**
     * 查询托运单的总条数
     *
     * @param consignCode
     * @param ownerIds
     * @param isScope
     * @return
     */
    int countConsignList(@Param("consignCode") String consignCode,
                         @Param("owner") String owner,
                         @Param("states") List<Integer> states,
                         @Param("ownerIds") Integer[] ownerIds,
                         @Param("isScope") Boolean isScope);

    /**
     * 拒绝托运单接口
     *
     * @param id
     * @param remark
     * @param ownerIds
     * @param isScope
     * @return
     */
    int rejectWaybillConsign(@Param("id") Integer id,
                             @Param("remark") String remark,
                             @Param("ownerIds") Integer[] ownerIds,
                             @Param("isScope") Boolean isScope);


    /**
     * 查询多个ID的电子运单详情
     * @param ides
     * @return
     */
    List<WaybillConsignDTO> getWaybillConsignByIdes(@Param("ides") String ides);
    /**
     * 修改托运单状态
     *
     * @param id
     * @param ownerIds
     * @param isScope
     * @return
     */
    int updateWaybillConsignStatus(@Param("id") Integer id, @Param("status") Integer Status,
                             @Param("ownerIds") Integer[] ownerIds,
                             @Param("isScope") Boolean isScope);
}