package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.*;
import com.thundersdata.backend.basic.model.WaybillOrder;
import com.thundersdata.backend.basic.model.WaybillOrderWithBLOBs;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillOrderWithBLOBs record);

    int insertSelective(WaybillOrderWithBLOBs record);

    WaybillOrderWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillOrderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WaybillOrderWithBLOBs record);

    int updateByPrimaryKey(WaybillOrder record);

    /**
     * 运单审批
     *
     * @param exaMineVO
     * @return
     */
    int exaMine(ExaMineVo exaMineVO);

    /**
     * 派发运单
     *
     * @param distriButeVO
     * @return
     */
    int distriBute(@Param("distriBute") DistriButeVo distriButeVO, @Param("ownerIds") Integer[] ownerIds, @Param("isScope") Boolean isScope);

    /**
     * 查询最大订单
     *
     * @param creditCode
     * @return
     */
    String selectByOrderCode(@Param("creditCode") String creditCode);

    /**
     * 删除电子运单
     *
     * @param id
     * @return
     */
    int deleteWaybillOrder(@Param("id") int id, @Param("ownerIds") Integer[] ownerIds, @Param("isScope") Boolean isScope);

    /**
     * 电子运单总数
     *
     * @return
     */
    int countWaybillOrder(int pilotId);

    /**
     * 查询企业列表
     *
     * @param userId    用户id
     * @param loginType 登录类型
     * @param orderCode 订单号
     * @param pilot     驾驶员
     * @param owner     托运企业
     * @return list
     */
    List<WaybillOrderDTO> selectOrderList(@Param("userId") Integer userId,
                                          @Param("loginType") Integer loginType,
                                          @Param("orderCode") String orderCode,
                                          @Param("pilot") String pilot,
                                          @Param("owner") String owner,
                                          @Param("limit") Integer limit,
                                          @Param("offset") Integer offset,
                                          @Param("states") List<Integer> states,
                                          @Param("ownerIds") Integer[] ownerIds,
                                          @Param("isScope") Boolean isScope);


    /**
     * 查询符合条件的企业列表数量
     *
     * @param userId
     * @param loginType
     * @param orderCode
     * @param pilot
     * @param owner
     * @return
     */
    int getOrderCountByList(@Param("userId") Integer userId,
                            @Param("loginType") Integer loginType,
                            @Param("orderCode") String orderCode,
                            @Param("pilot") String pilot,
                            @Param("owner") String owner,
                            @Param("states") List<Integer> states,
                            @Param("ownerIds") Integer[] ownerIds,
                            @Param("isScope") Boolean isScope);

    /**
     * 查询订单详情
     *
     * @param orderId 订单id
     * @return orderDetail
     */
    WaybillOrderDTO selectOrderDetail(@Param("orderId") Integer orderId);

    /**
     * 查询订单详情
     *
     * @param orderCode 订单编号
     * @return orderDetail
     */
    WaybillOrderDTO selectOrderDetailByOrderCode(@Param("orderCode") String orderCode);

    /**
     * 新增电子运单
     *
     * @param waybillOrderDTO
     * @return
     */
    int insertWaybillOrder(WaybillOrderDTO waybillOrderDTO);

    /**
     * 修改电子云单
     *
     * @param waybillOrderDTO
     * @return
     */
    int updateWaybillOrder(@Param("order") WaybillOrderDTO waybillOrderDTO,
                           @Param("ownerIds") Integer[] ownerIds,
                           @Param("isScope") Boolean isScope);

    /**
     * 查询人员和车辆id
     *
     * @param id
     * @return
     */
    ExaMineVo selectID(Integer id);

    /**
     * 根据运单id查询线路详情
     *
     * @param orderId
     * @return
     */
    lineDetailsDTO lineDetails(Integer orderId);

    /**
     * 装货信息
     * @param waybillOrder
     * @return
     */
    int expediter(WaybillOrder waybillOrder);


    /**
     * 运单补录
     * @param waybillOrder
     * @return
     */
    int waybillSupplement( waybillSupplementDTO waybillOrder);


    /**
     * 从托运单派发电子运单
     * @param waybillOrderDispatchVO
     * @return
     */
    int dispatchOrder(WaybillOrderDispatchVO waybillOrderDispatchVO);

    /**
     * 电子运单超时预警
     * @param userExpiredDTO
     * @return
     */
    List<UserExpiredVO> ExpiredOrder(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO);


    /**
     * 根据时间段查询电子运单超时预警
     * @param userExpiredDTO
     * @return
     */
    List<UserExpiredVO> HistoryExpiredOrder(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO);


    /**
     * 根据运单id 查询货车id,开始时间，结束时间
     * @param orderId
     * @return
     */
    VehicleTrackDTO WaybillTrack(@Param("orderId") String orderId);


    /**
     * 查询订单详情和经纬度
     *
     * @param ordercode
     * @return selectOrderDetaillonlatVO
     */
    selectOrderDetaillonlatVO selectOrderDetaillonlat(@Param("ordercode") String ordercode);

}