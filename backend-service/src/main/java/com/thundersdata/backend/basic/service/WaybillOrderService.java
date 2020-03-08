package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WaybillOrderDTO;
import com.thundersdata.backend.basic.dto.lineDetailsDTO;
import com.thundersdata.backend.basic.dto.waybillSupplementDTO;
import com.thundersdata.backend.basic.model.WaybillOrder;
import com.thundersdata.backend.basic.model.WaybillOrderWithBLOBs;
import com.thundersdata.backend.basic.vo.*;
import com.thundersdata.backend.common.utils.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname WaybillOrderService
 * @Description TODO
 * @Date 2019/12/6 17:36
 * @Created wrc
 */
public interface WaybillOrderService {

    /**
     * 派发运单
     *
     * @param distriButeVO
     * @return
     */
    String distriBute(DistriButeVo distriButeVO, Integer[] ownerIds, Boolean isScope,Integer userId);

    int updateWaybillOrder(WaybillOrderDTO waybillOrderDTO, Integer[] ownerIds, Boolean isScope);

    int deleteWaybillOrder(int id, String orderCode, Integer[] ownerIds, Boolean isScope);

    int countWaybillOrder(int pilotId);

    int insertWaybillOrder(WaybillOrderDTO waybillOrderDTO);

    /**
     * 运单审批
     *
     * @param ExaMineVOS
     * @return
     */
    String exaMine(List<ExaMineVo> ExaMineVOS);

    /**
     * 查询企业列表
     *
     * @param userId    用户id
     * @param loginType 登录类型
     * @param orderCode 订单号
     * @param pilot     驾驶员
     * @param owner     托运企业
     * @param states    状态列表
     * @return list
     */
    Pagination<WaybillOrderDTO> selectOrderList(Integer userId,
                                                Integer loginType,
                                                String orderCode,
                                                String pilot,
                                                String owner,
                                                Integer page,
                                                Integer pageSize,
                                                List<Integer> states,
                                                Integer[] ownerIds,
                                                Boolean isScope);

    /**
     * 查询订单详情
     *
     * @param orderId 订单id
     * @return orderDetail
     */
    WaybillOrderDTO selectOrderDetail(Integer orderId);

    /**
     * 查询订单详情
     *
     * @param orderCode 订单编号
     * @return orderDetail
     */
    WaybillOrderDTO selectOrderDetailByOrderCode(String orderCode);


    /**
     * 下一步
     *
     * @param orderId 订单id
     * @param nodeId  当前节点id
     * @param content 节点存储的内容
     * @return true
     */
    WaybillOrderWithBLOBs nextOrderNode(Integer orderId, Integer nodeId, String content, Integer userId,
                                        Integer[] ownerIds, Boolean isScope);


    /**
     * 拒绝
     *
     * @param orderId 订单id
     * @param reason  拒绝理由
     */
    int refuseOrder(Integer orderId, String reason, Integer userId,
                    Integer[] ownerIds, Boolean isScope);

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
    String expediter(WaybillOrder waybillOrder);


    /**
     * 运单补录
     * @param waybillOrder
     * @return
     */
    String waybillSupplement( waybillSupplementDTO waybillOrder);


    /**
     * 电子运单超时预警
     * @param userExpiredDTO
     * @return
     */
    List<UserExpiredVO> ExpiredOrder(UserExpiredDTO userExpiredDTO);

    /**
     * 根据运单id 根据运单id 查询货车轨迹
     * @param VehicleId
     * @return
     */
    List<VehicleTrackVO>  WaybillTrack(String VehicleId);


    /**
     * 根据时间段查询电子运单超时预警
     * @param userExpiredDTO
     * @return
     */
    List<UserExpiredVO> HistoryExpiredOrder(UserExpiredDTO userExpiredDTO);

    /**
     * 查询订单详情和经纬度
     *
     * @param ordercode
     * @return selectOrderDetaillonlatVO
     */
    List<selectOrderDetaillonlatVO> selectOrderDetaillonlat( String ordercode);
}
