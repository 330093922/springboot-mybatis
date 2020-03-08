package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.WaybillConsignDTO;
import com.thundersdata.backend.basic.vo.WaybillConsignDispatchVO;
import com.thundersdata.backend.basic.vo.WaybillConsignInsertVO;
import com.thundersdata.backend.basic.vo.WaybillConsignUpdateVO;
import com.thundersdata.backend.common.utils.Pagination;

import java.util.List;

/**
 * @Classname WaybillConsignService
 * @Description 托运单service
 * @Date 2020-2-1
 * @Created wanghaibo
 */
public interface WaybillConsignService {

    /**
     * 插入托运单
     *
     * @param waybillConsignInsertVO
     * @return
     */
    int insertWaybillConsign(WaybillConsignInsertVO waybillConsignInsertVO);

    /**
     * 修改托运单
     *
     * @param waybillConsignUpdateVO
     * @param ownerIds
     * @param isScope
     * @return
     */
    int updateWaybillConsign(WaybillConsignUpdateVO waybillConsignUpdateVO, Integer[] ownerIds, Boolean isScope);

    /**
     * 删除托运单
     *
     * @param id
     * @param ownerIds
     * @param isScope
     * @return
     */
    int deleteWaybillConsign(Integer id, Integer[] ownerIds, Boolean isScope);

    /**
     * 根据主键查询托运单
     *
     * @param id
     * @return
     */
    WaybillConsignDTO selectConsignDetail(Integer id);


    Pagination<WaybillConsignDTO> selectConsignList(String consignCode, String owner, List<Integer> states,
                                                    Integer page, Integer pageSize, Integer[] ownerIds, Boolean isScope);

    /**
     * 拒绝托运单
     *
     * @param id
     * @param remark
     * @param ownerIds
     * @param isScope
     * @return
     */
    int rejectWaybillConsign(Integer id, String remark, Integer[] ownerIds, Boolean isScope);

    /**
     * 接单
     * @param id
     * @param ownerIds
     * @param isScope
     * @return
     */
    int acceptWaybillConsign(Integer id, Integer[] ownerIds, Boolean isScope);

    /**
     * 派发订单
     * @param waybillConsignDispatchVO
     * @return
     */
    int dispatchConsign(WaybillConsignDispatchVO waybillConsignDispatchVO, Integer userId, Integer userType, Integer[] ownerIds, Boolean isScope);

    /**
     * 查询多个ID的电子运单详情
     * @param ides
     * @return
     */
    List<WaybillConsignDTO> getWaybillConsignByIdes(String ides);

}
