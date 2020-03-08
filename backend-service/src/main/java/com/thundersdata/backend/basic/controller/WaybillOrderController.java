package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WaybillOrderDTO;
import com.thundersdata.backend.basic.dto.lineDetailsDTO;
import com.thundersdata.backend.basic.dto.waybillSupplementDTO;
import com.thundersdata.backend.basic.model.WaybillOrder;
import com.thundersdata.backend.basic.model.WaybillOrderWithBLOBs;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.service.WaybillOrderService;
import com.thundersdata.backend.basic.vo.*;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子运单
 */
@Api(tags = "电子运单接口")
@RestController
@RequestMapping("order")
public class WaybillOrderController {
    @Autowired
    private WaybillOrderService waybillOrderService;

    /**
     * 修改电子运单
     *
     * @param waybillOrderDTO
     * @return
     */
    @ApiOperation(value = "修改电子运单详情", notes = "全量修改")
    @PostMapping("update")
    public int updateWaybillOrder(@RequestBody WaybillOrderDTO waybillOrderDTO, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillOrderService.updateWaybillOrder(waybillOrderDTO, ownerIds, isScope);
    }

    /**
     * 新增电子运单
     *
     * @param waybillOrderDTO
     * @return
     */
    @ApiOperation(value = "新增电子运单", notes = "全量新增")
    @PostMapping("insert")
    public int insertWaybillOrder(@RequestBody WaybillOrderDTO waybillOrderDTO) {
        return waybillOrderService.insertWaybillOrder(waybillOrderDTO);
    }

    /**
     * 删除电子运单
     *
     * @param waybillOrder
     * @return
     */
    @ApiOperation(value = "删除电子运单", notes = "订单id")
    @PostMapping("delete")
    public int deleteWaybillOrder(@RequestBody WaybillOrder waybillOrder, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillOrderService.deleteWaybillOrder(waybillOrder.getId(), waybillOrder.getOrderCode(), ownerIds, isScope);
    }

    /**
     * 查询运单列表
     *
     * @param userId          用户id
     * @param loginType       登录类型
     * @param selectOrderList 运单
     * @return list
     */
    @ApiOperation(value = "查询运单列表", notes = "userId,loginType为网关注入,所以不需要体现在文档上")
    @PostMapping("orderList")
    public Pagination<WaybillOrderDTO> selectOrderList(@RequestBody SelectOrderList selectOrderList,
                                                       @ApiIgnore() Integer userId, @ApiIgnore() Integer loginType,
                                                       @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {

        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);


        return waybillOrderService.selectOrderList(userId, loginType, selectOrderList.getOrderCode(),
                selectOrderList.getPilot(), selectOrderList.getOwner(), selectOrderList.getPage(),
                selectOrderList.getPageSize(), selectOrderList.getStates(), ownerIds, isScope);
    }

    /**
     * 查询订单详情
     *
     * @param orderId 订单id
     * @return orderDetail
     */
    @ApiOperation(value = "查询运单详情", notes = "订单id")
    @GetMapping("orderDetail")
    public WaybillOrderDTO selectOrderDetail(Integer orderId) {

        return waybillOrderService.selectOrderDetail(orderId);
    }

    /**
     * 查询订单详情,为扫码查询提供,不需要权限过滤
     *
     * @param orderCode 运单编号
     * @return orderDetail
     */
    @ApiOperation(value = "查询运单详情,为扫码展示使用", notes = "运单编号")
    @PostMapping("orderDetailForApp")
    public WaybillOrderDTO selectOrderDetailForApp(String orderCode) {

        return waybillOrderService.selectOrderDetailByOrderCode(orderCode);
    }

    /**
     * 下一步
     *
     * @param nextOrderNode 下一步订单
     * @return waybillOrderWithBLOBs
     */
    @ApiOperation(value = "下一步操作接口", notes = "订单id")
    @PostMapping("nextOrderNode")
    public WaybillOrderWithBLOBs nextOrderNode(@RequestBody NextOrderNodeVo nextOrderNode, @ApiIgnore() Integer userId,
                                               @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillOrderService.nextOrderNode(nextOrderNode.getOrderId(), nextOrderNode.getNodeId(), nextOrderNode.getContent(), userId, ownerIds, isScope);
    }

    /**
     * 拒绝订单
     *
     * @return
     */
    @ApiOperation(value = "拒绝订单", notes = "司机通过id拒绝订单")
    @PostMapping("refuseOrder")
    public Integer refuseOrder(@RequestBody RefuseOrderVo refuseOrderVO, @ApiIgnore() Integer userId,
                               @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillOrderService.refuseOrder(refuseOrderVO.getOrderId(), refuseOrderVO.getReason(), userId, ownerIds, isScope);
    }

    /**
     * 派发运单
     *
     * @param distriButeVO
     * @return
     */
    @ApiOperation(value = "运单派发", notes = "运单id，驾驶员id，押运员id，货车id，挂车id")
    @PostMapping("distriBute")
    public String distriBute(@RequestBody DistriButeVo distriButeVO,
                             @ApiIgnore() Integer userId,
                             @ApiIgnore() String dataList,
                             @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillOrderService.distriBute(distriButeVO, ownerIds, isScope,userId);
    }

    /**
     * 运单审批
     *
     * @param ExaMineVOS
     * @return
     */
    @ApiOperation(value = "运单审批", notes = "运单id，运单编号，备注，节点内容，驾驶员id，押运员id，车辆id，挂车id，可以传多个以集合的形式")
    @PostMapping("exaMine")
    public String exaMine(@RequestBody List<ExaMineVo> ExaMineVOS) {
        return waybillOrderService.exaMine(ExaMineVOS);
    }

    /**
     * 根据运单id查询线路详情
     *
     * @param orderId
     * @return
     */
    @ApiOperation(value = "据运单id查询线路详情", notes = "运单id")
    @PostMapping("lineDetails")
    public lineDetailsDTO lineDetails(Integer orderId) {
        return waybillOrderService.lineDetails(orderId);
    }

    /**
     * 根据运单id查询线路详情
     *
     * @param waybillOrder
     * @return
     */
    @ApiOperation(value = "装货接口", notes = "运单id,装货人姓名联系电话")
    @PostMapping("expediter")
    public String expediter(@RequestBody WaybillOrder waybillOrder ) {
        return waybillOrderService.expediter(waybillOrder);
    }

    /**
     * 运单补录
     *
     * @param waybillOrder
     * @return
     */
    @ApiOperation(value = "运单补录", notes = "运单全字段")
    @PostMapping("waybillSupplement")
    public String waybillSupplement(@RequestBody waybillSupplementDTO waybillOrder) {
        waybillOrder.setStatus(6);
        return waybillOrderService.waybillSupplement(waybillOrder);
    }


    /**
     * 电子运单超时预警
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "电子运单超时预警", notes = "区域")
    @PostMapping("ExpiredOrder")
    public List<UserExpiredVO> ExpiredOrder(@RequestBody UserExpiredDTO userExpiredDTO) {
        return waybillOrderService.ExpiredOrder(userExpiredDTO);
    }


    /**
     * 根据时间段查询电子运单超时预警
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "根据时间段查询电子运单超时预警", notes = "区域,开始时间，结束时间")
    @PostMapping("HistoryExpiredOrder")
    public List<UserExpiredVO> HistoryExpiredOrder(@RequestBody UserExpiredDTO userExpiredDTO) {
        return waybillOrderService.HistoryExpiredOrder(userExpiredDTO);
    }


    /**
     * 根据运单id查询线路详情
     *
     * @param orderId
     * @return
     */
    @ApiOperation(value = "据运单id查询货车轨迹", notes = "运单id")
    @GetMapping("WaybillTrack")
    public  List<VehicleTrackVO>  WaybillTrack(String orderId) {
        return waybillOrderService.WaybillTrack(orderId);
    }


    /**
     * 查询订单详情和经纬度
     *
     * @param ordercode
     * @return selectOrderDetaillonlatVO
     */
    @ApiOperation(value = "据运单号查询运单详情和经纬度", notes = "运单号")
    @GetMapping("selectOrderDetaillonlat")
    public List<selectOrderDetaillonlatVO> selectOrderDetaillonlat(String ordercode) {
        return waybillOrderService.selectOrderDetaillonlat(ordercode);
    }
}
