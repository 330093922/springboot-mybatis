package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.*;
import com.thundersdata.backend.basic.dto.*;
import com.thundersdata.backend.basic.model.*;
import com.thundersdata.backend.basic.service.WaybillOrderService;
import com.thundersdata.backend.basic.utils.OrderUtil;
import com.thundersdata.backend.basic.vo.*;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.thundersdata.backend.common.constant.SymbolConstants.*;

/**
 * @Classname WaybillOrderServiceImpl
 * @Description TODO
 * @Date 2019/12/6 17:37
 * @Created wrc
 */
@Service
public class WaybillOrderServiceImpl implements WaybillOrderService {

    @Autowired
    private WaybillOrderMapper waybillOrderMapper;
    @Autowired
    private WaybillUserMapper waybillUserMapper;
    @Autowired
    private WaybillVehicleMapper waybillVehicleMapper;
    @Autowired
    private WaybillOrderNodeMapper waybillOrderNodeMapper;
    @Autowired
    private WaybillNodesMapper waybillNodesMapper;
    @Autowired
    private WaybillOrderGoodsMapper waybillOrderGoodsMapper;


    /**
     * 运单派发
     *
     * @param distriButeVO
     * @return
     */
    @Transactional
    @Override
    public String distriBute(DistriButeVo distriButeVO, Integer[] ownerIds, Boolean isScope, Integer userId) {

        Assert.notNull(distriButeVO.getPilotId(), "请选择驾驶员！");
        Assert.notNull(distriButeVO.getSupercargoId(), "请选择押运员！");
        Assert.notNull(distriButeVO.getVehicleId(), "请选择车辆！");
        distriButeVO.setSchedulingUser(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        distriButeVO.setSchedulingDate(df.format(new Date()));
        ArrayList<Integer> flag = new ArrayList();
        //驾驶员信息修改,押运员信息修改
        flag.add(waybillOrderMapper.distriBute(distriButeVO, ownerIds, isScope));
        WaybillUser waybillUser = new WaybillUser();
        waybillUser.setId(distriButeVO.getPilotId());
        waybillUser.setState(1);
        waybillUser.setSex(distriButeVO.getSupercargoId());
        flag.add(waybillUserMapper.stateModification(waybillUser));

        //车辆信息修改 挂车id修改
        WaybillVehicle waybillVehicle = new WaybillVehicle();
        waybillVehicle.setId(String.valueOf(distriButeVO.getVehicleId()));
        waybillVehicle.setVehicleStatus(distriButeVO.getTrailerId());
        waybillVehicle.setState(1);
        flag.add(waybillVehicleMapper.stateModification(waybillVehicle));
        int i = 0;
        for (Integer flaged : flag) {
            if (flaged > 0) {
                i++;
            }
        }
        if (i == 3) {
            return "state:success";
        } else {
            throw new RuntimeException("state:error");
        }
    }

    /**
     * 运单审批
     *
     * @param exaMineVOS exaMineVOS
     * @return
     */
    @Transactional
    @Override
    public String exaMine(List<ExaMineVo> exaMineVOS) {
        ArrayList<Integer> judge = new ArrayList<>();
        for (ExaMineVo order : exaMineVOS) {
            ArrayList<Integer> flag = new ArrayList<>();
            order.setNodeId(-1);
            order.setStatus(4);
            flag.add(waybillOrderMapper.exaMine(order));
            //插入waybill_order_node表数据
            WaybillOrderNode waybillOrderNode = new WaybillOrderNode();
            waybillOrderNode.setOrderCode(order.getOrderCode());
            waybillOrderNode.setContent(order.getContent());
            waybillOrderNode.setNodeId(6);
            flag.add(waybillOrderNodeMapper.insertSelective(waybillOrderNode));
            //查询该运单的人和车id
            ExaMineVo exaMineVo = waybillOrderMapper.selectID(order.getId());
            //修改司机状态
            WaybillUser waybillUser = new WaybillUser();
            waybillUser.setId(exaMineVo.getPilotId());
            waybillUser.setState(0);
            waybillUser.setSex(exaMineVo.getSupercargoId());
            flag.add(waybillUserMapper.stateModification(waybillUser));
            //车辆信息修改
            WaybillVehicle waybillVehicle = new WaybillVehicle();
            waybillVehicle.setId(String.valueOf(exaMineVo.getVehicleId()));
            waybillVehicle.setState(0);
            waybillVehicle.setVehicleStatus(exaMineVo.getTrailerId());
            flag.add(waybillVehicleMapper.stateModification(waybillVehicle));
            //判断是否全部成功
            int i = 0;
            for (Integer flaged : flag) {
                if (flaged > 0) {
                    i++;
                }
            }
            if (i == 4) {
                judge.add(1);
            }
        }
        if (exaMineVOS.size() == judge.size()) {
            return "state:success";
        } else {
            throw new RuntimeException("state:error");
        }
    }

    /**
     * 修改电子运单
     *
     * @param waybillOrderDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateWaybillOrder(WaybillOrderDTO waybillOrderDTO, Integer[] ownerIds, Boolean isScope) {
        Assert.notNull(waybillOrderDTO.getShipperId(), "发货企业id不能为空！");
        Assert.notNull(waybillOrderDTO.getReceivingId(), "收货企业id不能为空！");
        Assert.notNull(waybillOrderDTO.getShipmentId(), "运输企业不能为空！");
        Assert.notNull(waybillOrderDTO.getGoodsList(), "运输货物不能为空！");
        waybillOrderDTO.setStatus(ZERO);
        int count = waybillOrderMapper.updateWaybillOrder(waybillOrderDTO, ownerIds, isScope);
        Assert.isTrue(count > 0, "修改电子运单失败");
        waybillOrderGoodsMapper.deleteWaybillOrderGoods(waybillOrderDTO.getOrderCode());

        return waybillOrderGoodsMapper.batchInsertWaybillOrderGoods(waybillOrderDTO.getGoodsList(), waybillOrderDTO.getOrderCode());
    }

    /**
     * 删除电子运单
     *
     * @param id
     * @return
     */
    @Override
    public int deleteWaybillOrder(int id, String orderCode,
                                  Integer[] ownerIds,
                                  Boolean isScope) {
        int count = waybillOrderMapper.deleteWaybillOrder(id, ownerIds, isScope);
        Assert.isTrue(count > 0, "删除电子运单失败");

        return waybillOrderGoodsMapper.deleteWaybillOrderGoods(orderCode);

    }

    /**
     * 电子云单总数
     *
     * @param pilotId
     * @return
     */
    @Override
    public int countWaybillOrder(int pilotId) {
        return waybillOrderMapper.countWaybillOrder(pilotId);
    }

    /**
     * 新增电子运单
     *
     * @param waybillOrderDTO
     * @return
     */
    @Override
    public int insertWaybillOrder(WaybillOrderDTO waybillOrderDTO) {
        Assert.notNull(waybillOrderDTO.getShipperId(), "发货企业id不能为空!");
        Assert.notNull(waybillOrderDTO.getReceivingId(), "收货企业id不能为空！");
        Assert.notNull(waybillOrderDTO.getShipmentId(), "运输企业不能为空");
        Assert.notNull(waybillOrderDTO.getShipmentCreditCode(), "经营许可证不存在！");
        if (waybillOrderDTO.getShipmentCreditCode() == null || "null".equals(waybillOrderDTO.getShipmentCreditCode())) {
            Assert.isTrue(true, "该运输企业没有运输经营许可证！");
        }

        String shipmentCreditCode = OrderUtil.generateOrderNo(waybillOrderDTO.getShipmentCreditCode());
        waybillOrderDTO.setOrderCode(shipmentCreditCode);

        waybillOrderMapper.insertWaybillOrder(waybillOrderDTO);

        Assert.notNull(waybillOrderDTO.getGoodsList(), "运输货物不能为空！");

        return waybillOrderGoodsMapper.batchInsertWaybillOrderGoods(waybillOrderDTO.getGoodsList(), shipmentCreditCode);
    }


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
    @Override
    public Pagination<WaybillOrderDTO> selectOrderList(Integer userId,
                                                       Integer loginType,
                                                       String orderCode,
                                                       String pilot,
                                                       String owner,
                                                       Integer page,
                                                       Integer pageSize,
                                                       List<Integer> states,
                                                       Integer[] ownerIds,
                                                       Boolean isScope) {
        Assert.notNull(states, "订单状态不能为空！");

        // 校验分页参数，赋默认值
        PageParam pageparam = PageUtils.init(page, pageSize);
        // 查询count
        int total = waybillOrderMapper.getOrderCountByList(userId, loginType,
                OrderUtil.getLikeStr(orderCode), OrderUtil.getLikeStr(pilot), OrderUtil.getLikeStr(owner), states, ownerIds, isScope);
        if (total == ZERO) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }

        List<WaybillOrderDTO> waybillOrderDTOList = waybillOrderMapper
                .selectOrderList(userId, loginType, OrderUtil.getLikeStr(orderCode),
                        OrderUtil.getLikeStr(pilot), OrderUtil.getLikeStr(owner), pageparam.getLimit(), pageparam.getOffset(), states, ownerIds, isScope);


        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, waybillOrderDTOList);
    }

    /**
     * 查询订单详情
     *
     * @param orderId 订单id
     * @return orderDetail
     */
    @Override
    public WaybillOrderDTO selectOrderDetail(Integer orderId) {
        Assert.notNull(orderId, "订单id不能为空");
        WaybillOrderDTO waybillOrderDTO = waybillOrderMapper.selectOrderDetail(orderId);
        Assert.notNull(waybillOrderDTO, "运单不存在");

        return waybillOrderDTO;
    }


    @Override
    public WaybillOrderDTO selectOrderDetailByOrderCode(String orderCode) {
        Assert.notNull(orderCode, "订单编码不能为空");
        WaybillOrderDTO waybillOrderDTO = waybillOrderMapper.selectOrderDetailByOrderCode(orderCode);
        Assert.notNull(waybillOrderDTO, "运单不存在");

        return waybillOrderDTO;
    }

    /**
     * 下一步
     *
     * @param orderId 订单id
     * @param nodeId  当前节点id
     * @param content 节点存储的内容
     * @return true
     */
    @Override
    public WaybillOrderWithBLOBs nextOrderNode(Integer orderId, Integer nodeId, String content, Integer userId,
                                               Integer[] ownerIds, Boolean isScope) {
        Assert.notNull(orderId, "订单不能为空！");
        Assert.notNull(nodeId, "当前节点不能为空！");
        WaybillNodes waybillNodes = waybillNodesMapper.selectByPrimaryKey(nodeId);
        Assert.notNull(waybillNodes, "当前节点不存在");
        WaybillOrderWithBLOBs waybillOrder = waybillOrderMapper.selectByPrimaryKey(orderId);
        Assert.notNull(waybillOrder, "未查询到电子运单");
        Assert.isTrue(userId.equals(waybillOrder.getPilotId()), "您没有操作该订单权限！");
        if (nodeId < ONE || nodeId > SIX) {
            Assert.isFalse(true, "节点参数错误！");
        }

        if (!waybillOrder.getStatus().equals(ONE) && !waybillOrder.getStatus().equals(TWO)) {
            Assert.isFalse(true, "当前运单不可以操作！");
        }

        if (FIVE.equals(nodeId)){
            waybillVehicleMapper.updateStates(waybillOrder.getVehicleId(), waybillOrder.getTrailerId());
            waybillUserMapper.updateStates(waybillOrder.getPilotId(), waybillOrder.getSupercargoId());
        }

        if (nodeId < FIVE) {
            waybillOrder.setStatus(TWO);
        } else if (nodeId.equals(FIVE)) {
            waybillOrder.setStatus(THREE);
        }

        // 更新订单状态
        waybillOrder.setNodeId(waybillOrder.getNodeId() + ONE);
        int count = waybillOrderMapper.updateByPrimaryKeyWithBLOBs(waybillOrder);
        Assert.isTrue(count > 0, "修改订单状态失败");

        // 插入历史节点
        WaybillOrderNode waybillOrderNode = new WaybillOrderNode();
        waybillOrderNode.setContent(content);
        waybillOrderNode.setOrderCode(waybillOrder.getOrderCode());
        waybillOrderNode.setNodeId(waybillOrder.getNodeId() - ONE);
        waybillOrderNodeMapper.insertSelective(waybillOrderNode);

        return waybillOrder;
    }

    /**
     * 拒绝
     *
     * @param orderId 订单id
     * @param reason  拒绝理由
     */
    @Override
    public int refuseOrder(Integer orderId, String reason, Integer userId,
                           Integer[] ownerIds, Boolean isScope) {

        Assert.notNull(orderId, "订单不能为空！");
        WaybillOrderWithBLOBs waybillOrder = waybillOrderMapper.selectByPrimaryKey(orderId);
        Assert.notNull(waybillOrder, "未找到电子运单");

        String orderCode = waybillOrder.getOrderCode();
        Assert.notNull(orderCode, "运单号不能为空");
        Assert.isTrue(userId.equals(waybillOrder.getPilotId()), "您不能操作该订单！");

        waybillVehicleMapper.updateStates(waybillOrder.getVehicleId(), waybillOrder.getTrailerId());
        waybillUserMapper.updateStates(waybillOrder.getPilotId(), waybillOrder.getSupercargoId());

        //修改运单状态  5：拒绝
        waybillOrder.setStatus(FIVE);
        //修改当前节点：-1
        waybillOrder.setNodeId(-ONE);
        //拒绝理由
        waybillOrder.setRemark(reason);

        waybillOrderMapper.updateByPrimaryKeyWithBLOBs(waybillOrder);

        //删除运单节点
        return waybillOrderNodeMapper.deleteWaybillOrderNode(orderCode);

    }

    /**
     * 根据运单id查询线路详情
     *
     * @param orderId
     * @return
     */
    @Override
    public lineDetailsDTO lineDetails(Integer orderId) {
        Assert.notNull(orderId, "运单ID不能为空");

        return waybillOrderMapper.lineDetails(orderId);
    }

    /**
     * 装货接口
     * @param waybillOrder
     * @return
     */
    @Override
    public String expediter(WaybillOrder waybillOrder) {
        Assert.notNull(waybillOrder.getExpediter(), "装货人姓名不能为空！");
        Assert.notNull(waybillOrder.getExpediterPhone(), "装货人手机号不能为空！");
        Assert.notNull(waybillOrder.getId(), "订单id不能为空！");
        int result = waybillOrderMapper.expediter(waybillOrder);
        if(result>0){
            return "state:success";
        }else{
            throw new RuntimeException("state:error");
        }
    }

    /**
     * 运单补录
     *
     * @param waybillOrder
     * @return
     */
    @Override
    @Transactional
    public String waybillSupplement(waybillSupplementDTO waybillOrder) {
        if (waybillOrder.getOrderCode() == null || "null".equals(waybillOrder.getOrderCode())) {
            Assert.isTrue(true, "该运输企业没有运输经营许可证！");
        }
        //根据运输经营许可证生成运单编号，这里传企业统一征信号码
        String orderCode = OrderUtil.generateOrderNo(waybillOrder.getOrderCode());
        waybillOrder.setOrderCode(orderCode);
        waybillOrder.setStatus(6);
        waybillOrder.setNodeId(-1);
        int batchInsertWaybillOrderGoodsFlag=0;
        int waybillSupplementFlag = waybillOrderMapper.waybillSupplement(waybillOrder);
        if(waybillOrder.getGoodsList()!=null &&waybillOrder.getGoodsList().size()>0){
           batchInsertWaybillOrderGoodsFlag =waybillOrderGoodsMapper.batchInsertWaybillOrderGoods(waybillOrder.getGoodsList(), orderCode);
        }
       if((waybillSupplementFlag+batchInsertWaybillOrderGoodsFlag)>=2){
            return "state:success";
        }else{
            throw new RuntimeException("state:error");
        }
    }

    /**
     * 电子运单超时预警
     *
     * @param userExpiredDTO
     * @return
     */
    @Override
    public List<UserExpiredVO> ExpiredOrder(UserExpiredDTO userExpiredDTO) {
        return waybillOrderMapper.ExpiredOrder(userExpiredDTO);
    }

    /**
     * 根据运单id 查询货车轨迹
     *
     * @param orderId
     * @return
     */
    @Override
    public List<VehicleTrackVO> WaybillTrack(String orderId) {
        VehicleTrackDTO  vehicleTrackDTO = waybillOrderMapper.WaybillTrack(orderId);
        return waybillVehicleMapper.VehicleTrack(vehicleTrackDTO);
    }

    /**
     * 根据时间段查询电子运单超时预警
     *
     * @param userExpiredDTO
     * @return
     */
    @Override
    public List<UserExpiredVO> HistoryExpiredOrder(UserExpiredDTO userExpiredDTO) {
        return waybillOrderMapper.HistoryExpiredOrder(userExpiredDTO);
    }


    /**
     * 查询订单详情和经纬度
     *
     * @param ordercode
     * @return selectOrderDetaillonlatVO
     */
    @Override
    public List<selectOrderDetaillonlatVO> selectOrderDetaillonlat(String ordercode) {
        String[] strs = ordercode.split(",");
        List<selectOrderDetaillonlatVO> selectOrderDetaillonlatVOS = new ArrayList<>();
        for(String str : strs){
            selectOrderDetaillonlatVOS.add(waybillOrderMapper.selectOrderDetaillonlat(str));
        }
        return selectOrderDetaillonlatVOS;
    }
}
