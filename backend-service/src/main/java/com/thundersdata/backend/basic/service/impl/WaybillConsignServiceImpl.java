package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.*;
import com.thundersdata.backend.basic.dto.*;
import com.thundersdata.backend.basic.model.*;
import com.thundersdata.backend.basic.service.WaybillConsignGoodsService;
import com.thundersdata.backend.basic.service.WaybillConsignService;
import com.thundersdata.backend.basic.service.WaybillOrderService;
import com.thundersdata.backend.basic.service.WaybillOwnerService;
import com.thundersdata.backend.basic.utils.OrderUtil;
import com.thundersdata.backend.basic.vo.*;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.thundersdata.backend.common.constant.SymbolConstants.*;

/**
 * @Classname WaybillConsignServiceImpl
 * @Description 托运单service实现类
 * @Date 2020-2-1
 * @Created wanghaibo
 */
@Service
public class WaybillConsignServiceImpl implements WaybillConsignService {

    @Autowired
    private WaybillConsignMapper waybillConsignMapper;

    @Autowired
    private WaybillOwnerMapper waybillOwnerMapper;

    @Autowired
    private WaybillConsignGoodsMapper waybillConsignGoodsMapper;

    @Autowired
    private WaybillOrderMapper waybillOrderMapper;

    @Autowired
    private WaybillUserMapper waybillUserMapper;

    @Autowired
    private WaybillVehicleMapper waybillVehicleMapper;

    @Autowired
    private WaybillOrderGoodsMapper waybillOrderGoodsMapper;

    @Autowired
    private WaybillConsignGoodsService waybillConsignGoodsService;

    @Autowired
    private WaybillOwnerService waybillOwnerService;

    @Autowired
    private WaybillOrderRouteMapper waybillOrderRouteMapper;

    /**
     * 插入托运单
     * @param waybillConsignInsertVO
     * @return
     */
    @Override
    public int insertWaybillConsign(WaybillConsignInsertVO waybillConsignInsertVO) {
        Assert.notNull(waybillConsignInsertVO.getShipperId(), "发货企业id不能为空!");
        Assert.notNull(waybillConsignInsertVO.getReceivingId(), "收货企业id不能为空！");
        Assert.notNull(waybillConsignInsertVO.getShipmentId(), "运输企业不能为空");
        WaybillConsignWithBLOBs waybillConsignWithBLOBs = new WaybillConsignWithBLOBs();
        BeanUtils.copyProperties(waybillConsignInsertVO, waybillConsignWithBLOBs);

        WaybillOwnerDetailsDTO shipper = waybillOwnerService.selectByPrimaryKey(waybillConsignInsertVO.getShipperId() + "");
        Assert.notNull(shipper, "发货企业不存在");
        String consignCode = OrderUtil.generateConsignNo(shipper.getCreditCode());
        waybillConsignWithBLOBs.setConsignCode(consignCode);

        int result = waybillConsignMapper.insertSelective(waybillConsignWithBLOBs);
        Integer consignId = waybillConsignWithBLOBs.getId();
        waybillConsignGoodsService.insertWaybillConsignGoodsBatch(consignId, waybillConsignInsertVO.getListConsignGoods());
        return result;
    }

    /**
     * 修改托运单
     * @param waybillConsignUpdateVO
     * @param ownerIds
     * @param isScope
     * @return
     */
    @Override
    public int updateWaybillConsign(WaybillConsignUpdateVO waybillConsignUpdateVO, Integer[] ownerIds, Boolean isScope) {
        Assert.notNull(waybillConsignUpdateVO.getShipperId(), "发货企业id不能为空!");
        Assert.notNull(waybillConsignUpdateVO.getReceivingId(), "收货企业id不能为空！");
        Assert.notNull(waybillConsignUpdateVO.getShipmentId(), "运输企业不能为空");
        WaybillConsignWithBLOBs waybillConsignWithBLOBs = new WaybillConsignWithBLOBs();
        BeanUtils.copyProperties(waybillConsignUpdateVO, waybillConsignWithBLOBs);

        waybillConsignGoodsMapper.deleteByConsignId(waybillConsignUpdateVO.getId());
        int result = waybillConsignMapper.updateByPrimaryKeySelective(waybillConsignWithBLOBs);
        waybillConsignGoodsService.updateWaybillConsignGoodsBatch(waybillConsignUpdateVO.getId(), waybillConsignUpdateVO.getListConsignGoods());
        return result;
    }

    /**
     * 删除托运单
     * @param id
     * @param ownerIds
     * @param isScope
     * @return
     */
    @Override
    public int deleteWaybillConsign(Integer id, Integer[] ownerIds, Boolean isScope) {
        int result = waybillConsignMapper.deleteLogicByPrimaryKey(id, ownerIds, isScope);
        waybillConsignGoodsMapper.deleteLogicByConsignId(id);
        return result;
    }

    /**
     * 根据主键查询托运单和相关货物信息
     * @param id
     * @return
     */
    @Override
    public WaybillConsignDTO selectConsignDetail(Integer id) {
        WaybillConsignDTO waybillConsignDTO = waybillConsignMapper.getWaybillConsignById(id);
        Assert.notNull(waybillConsignDTO, "托运单不存在");
        List<WaybillConsignGoodsDTO> listGoods = waybillConsignGoodsMapper.selectByConsignId(id);
        waybillConsignDTO.setListGoods(listGoods);

        return waybillConsignDTO;
    }


    /**
     * 分页查询托运单
     * @param consignCode
     * @param owner
     * @param states
     * @param page
     * @param pageSize
     * @param ownerIds
     * @param isScope
     * @return
     */
    @Override
    public Pagination<WaybillConsignDTO> selectConsignList(String consignCode, String owner, List<Integer> states, Integer page, Integer pageSize, Integer[] ownerIds, Boolean isScope) {
        // 校验分页参数，赋默认值
        PageParam pageparam = PageUtils.init(page, pageSize);

        // 查询count
        int total = waybillConsignMapper.countConsignList(OrderUtil.getLikeStr(consignCode), OrderUtil.getLikeStr(owner), states, ownerIds, isScope);
        if (total == ZERO) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }

        List<WaybillConsignDTO> waybillConsignDTOList = waybillConsignMapper.selectConsignList(OrderUtil.getLikeStr(consignCode), OrderUtil.getLikeStr(owner), states, pageparam.getLimit(), pageparam.getOffset(), ownerIds, isScope);

        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, waybillConsignDTOList);
    }

    /**
     * 拒绝托运单
     * @param id
     * @param remark
     * @param ownerIds
     * @param isScope
     * @return
     */
    @Override
    public int rejectWaybillConsign(Integer id, String remark, Integer[] ownerIds, Boolean isScope) {

        return waybillConsignMapper.rejectWaybillConsign(id, remark, ownerIds, isScope);
    }

    @Override
    public int acceptWaybillConsign(Integer id, Integer[] ownerIds, Boolean isScope) {
        return 0;
    }

    /**
     * 根据托运单派发电子运单
     * @param waybillConsignDispatchVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int dispatchConsign(WaybillConsignDispatchVO waybillConsignDispatchVO, Integer userId, Integer userType, Integer[] ownerIds, Boolean isScope) {
        Integer consignId = waybillConsignDispatchVO.getConsignId();
        WaybillConsignDTO waybillConsignById = waybillConsignMapper.getWaybillConsignById(consignId);


        //根据id查询运输企业
        WaybillOwnerDetailsDTO ownerShipment = waybillOwnerMapper.selectByPrimaryKey(waybillConsignById.getShipmentId() + "");
        Assert.notNull(ownerShipment, "运输企业不存在！");
        if (StringUtils.isNotBlank(ownerShipment.getCreditCode())) {
            Assert.isTrue(true, "该运输企业没有运输经营许可证！");
        }
        //根据id查询装货企业
        WaybillOwnerDetailsDTO ownerExpediter = waybillOwnerMapper.selectByPrimaryKey(waybillConsignById.getExpediterId() + "");
        Assert.notNull(ownerExpediter, "装货企业不存在！");

        String orderCode = OrderUtil.generateOrderNo(ownerShipment.getCreditCode());
        //向电子运单表插入数据
        WaybillOrderDispatchVO waybillOrderDispatchVO = new WaybillOrderDispatchVO();

        BeanUtils.copyProperties(waybillConsignById, waybillOrderDispatchVO);

        waybillOrderDispatchVO.setOrderCode(orderCode);
        waybillOrderDispatchVO.setConsignmentId(consignId);
        waybillOrderDispatchVO.setExpediter(waybillConsignById.getExpediterContact());
        waybillOrderDispatchVO.setExpediterPhone(waybillConsignById.getExpediterContactPhone());
        waybillOrderDispatchVO.setPilotId(waybillConsignDispatchVO.getPilotId());
        waybillOrderDispatchVO.setSupercargoId(waybillConsignDispatchVO.getSupercargoId());
        waybillOrderDispatchVO.setVehicleId(waybillConsignDispatchVO.getVehicleId());
        waybillOrderDispatchVO.setTrailerId(waybillConsignDispatchVO.getTrailerId());
        waybillOrderDispatchVO.setSchedulingUser(userId);
        waybillOrderDispatchVO.setSchedulingDate(new Date());
        waybillOrderDispatchVO.setStatus(1);
        waybillOrderDispatchVO.setNodeId(1);

        waybillOrderMapper.dispatchOrder(waybillOrderDispatchVO);
        Integer orderId = waybillOrderDispatchVO.getId();

        //修改司机状态
        WaybillUser waybillUserPilot = new WaybillUser();
        waybillUserPilot.setId(waybillOrderDispatchVO.getPilotId());
        waybillUserPilot.setState(1);
        waybillUserMapper.stateModification(waybillUserPilot);

        //修改押运员状态
        WaybillUser waybillUserSupercargo = new WaybillUser();
        waybillUserSupercargo.setId(waybillOrderDispatchVO.getSupercargoId());
        waybillUserSupercargo.setState(1);
        waybillUserMapper.stateModification(waybillUserSupercargo);

        //车辆信息修改
        WaybillVehicle waybillVehicle = new WaybillVehicle();
        waybillVehicle.setId(String.valueOf(waybillOrderDispatchVO.getVehicleId()));
        waybillVehicle.setState(1);
        waybillVehicleMapper.stateModification(waybillVehicle);

        //挂车信息修改
        WaybillVehicle waybillVehicleTrailer = new WaybillVehicle();
        waybillVehicleTrailer.setId(String.valueOf(waybillOrderDispatchVO.getTrailerId()));
        waybillVehicleTrailer.setState(1);
        waybillVehicleMapper.stateModification(waybillVehicleTrailer);

        //修改线路信息
        List<WaybillOrderRoute> listRoute = new ArrayList<>();
        WaybillOrderRoute waybillOrderRoute = new WaybillOrderRoute();
        waybillOrderRoute.setOrderId(orderId);
        waybillOrderRoute.setRouteId(waybillConsignDispatchVO.getRouteId());
        listRoute.add(waybillOrderRoute);
        waybillOrderRouteMapper.batchInsert(listRoute);

        List<WaybillConsignGoodsDTO> listConsignGoods = waybillConsignGoodsService.selectConsignGoodsList(consignId);
        Map<Integer, WaybillConsignGoodsDTO> mapGoods = new HashMap<>();
        for (WaybillConsignGoodsDTO goodsDTO: listConsignGoods) {
            mapGoods.put(goodsDTO.getGoodsId(), goodsDTO);
        }

        //插入关联的货物信息
        List<WaybillConsignGoodsDispatchVO> listGoods = waybillConsignDispatchVO.getListGoods();
        for ( WaybillConsignGoodsDispatchVO goods : listGoods ) {
            Integer goodsId = goods.getGoodsId();
            WaybillConsignGoodsDTO consignGoods = mapGoods.get(goodsId);
            Integer surplusNo = consignGoods.getSurplusNo();
            Integer dispatchNo = goods.getDispatchNo();
            if(surplusNo == null || surplusNo <= 0){
                Assert.isTrue(false, "剩余数量为空");
            }
            if(dispatchNo == null || dispatchNo <= 0){
                continue;
            }
            if(dispatchNo > surplusNo){
                Assert.isTrue(false, "派发数量不能超过剩余数量");
            }
            int surplusNoNew = surplusNo - dispatchNo;

            WaybillOrderGoods waybillOrderGoods = new WaybillOrderGoods();
            waybillOrderGoods.setOrderCode(orderCode);
            waybillOrderGoods.setGoodsCode(consignGoods.getUnNo());
            waybillOrderGoods.setGoodsName(consignGoods.getGoodsName());
            waybillOrderGoods.setGoodsType(consignGoods.getGoodsType());
            waybillOrderGoods.setGoodsSize(consignGoods.getPackagingType());
            waybillOrderGoods.setGoodsWeight(consignGoods.getGoodsDensity()+"");
            waybillOrderGoods.setGoodsVolume(goods.getDispatchNo());
            waybillOrderGoods.setRemark("");
            waybillOrderGoodsMapper.insertSelective(waybillOrderGoods);
            waybillConsignGoodsMapper.updateWaybillConsignGoodsSurplusNo(consignGoods.getId(), surplusNoNew);
        }

        int sumSurplus = waybillConsignGoodsMapper.getSumSurplusNoByConsignId(consignId);
        if(sumSurplus == 0) {
            waybillConsignMapper.updateWaybillConsignStatus(consignId,4, ownerIds, isScope);
        }else{
            if(waybillConsignById.getStatus() == 1){
                waybillConsignMapper.updateWaybillConsignStatus(consignId,2, ownerIds, isScope);
            }
        }

        return 1;
    }



    /**
     * 查询多个ID的电子运单详情
     * @param ides
     * @return
     */
    @Override
    public List<WaybillConsignDTO> getWaybillConsignByIdes(String ides) {
        return waybillConsignMapper.getWaybillConsignByIdes(ides);
    }
}
