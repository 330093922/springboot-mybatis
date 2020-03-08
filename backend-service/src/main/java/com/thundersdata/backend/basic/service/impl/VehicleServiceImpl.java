package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.WaybillVehicleMapper;
import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.VehicleTrackDTO;
import com.thundersdata.backend.basic.dto.WaybillVehicleDTO;
import com.thundersdata.backend.basic.model.WaybillVehicle;
import com.thundersdata.backend.basic.service.VehicleService;
import com.thundersdata.backend.basic.utils.CoordinateUtils;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.VehicleTrackVO;
import com.thundersdata.backend.basic.vo.WaybillVehicleVO;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private WaybillVehicleMapper waybillVehicleMapper;

    /**
     * 查询车辆列表
     *
     * @param queryVO 查询条件
     * @return 列表
     */
    @Override
    public Pagination<WaybillVehicleDTO> getListByCondition(WaybillVehicleVO queryVO,
                                                            Integer page,
                                                            Integer pageSize,
                                                            Integer[] ownerIds,
                                                            Boolean isScope) {

        // 校验分页参数，赋默认值
        PageParam pageparam = PageUtils.init(page, pageSize);

        int total = waybillVehicleMapper.getCountByCondition(queryVO,ownerIds,isScope);
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }

        // 查询车辆数量
        List<WaybillVehicleDTO> vehicleList = waybillVehicleMapper.getListByCondition(queryVO, pageparam.getLimit(), pageparam.getOffset(),ownerIds,isScope);

        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, vehicleList);
    }

    /**
     * 查询车辆详情
     *
     * @param id 查询详情
     * @return 列表
     */
    @Override
    public  List<WaybillVehicleDTO> getDetailsByCondition(String id) {
        Assert.notNull(id, "id不能为空");
        List<WaybillVehicleDTO>  waybillVehicleDTOS = new ArrayList<>();
        String[] strings = id.split(",");
        for(String s:strings){
            waybillVehicleDTOS.add(waybillVehicleMapper.getDetailsByCondition(s));
        }
        return waybillVehicleDTOS;
    }


    /**
     * 车辆下拉框
     * @return
     */
    @Override
    public List<WaybillVehicleDTO> dropList(String vehicleCode){
        return waybillVehicleMapper.dropList(vehicleCode);
    }

    /**
     * 车辆下拉框--带范围
     * @return
     */
    @Override
    public List<WaybillVehicleDTO> dropListWithScope(String vehicleCode,
                                                     Integer[] ownerIds,
                                                     Boolean isScope){

        return waybillVehicleMapper.dropListWithScope(vehicleCode, ownerIds, isScope);
    }

    /**
     * 新增车辆
     * @param waybillVehicle
     * @return
     */
    @Override
    public int insertVehicle(WaybillVehicle waybillVehicle) {
        Assert.notNull(waybillVehicle.getVehicleCode(),"车牌号不能为空");
        Assert.notNull(waybillVehicle.getManageArea(),"经营范围不能为空");
        Assert.notNull(waybillVehicle.getTransportNumber(),"道路许可证不能为空");
        return waybillVehicleMapper.insertSelective(waybillVehicle);
    }

    /**
     * 修改车辆
     * @param waybillVehicle
     * @return
     */
    @Override
    public int updateVehicleWithScope(WaybillVehicle waybillVehicle, Integer[] ownerIds, Boolean isScope) {

        return waybillVehicleMapper.updateByPrimaryKeySelectiveWithScope(waybillVehicle, ownerIds, isScope);
    }

    @Override
    public Integer deleteVehicle(List<WaybillVehicleVO> list, Integer[] ownerIds, Boolean isScope) {

        return waybillVehicleMapper.deleteWaybillVehicle(list, ownerIds, isScope);
    }

    /**
     * 查询过期道路运输证号车辆
     *
     * @param userExpiredDTO
     * @param
     * @return
     */
    @Override
    public List<UserExpiredVO> ExpiredVehicle(UserExpiredDTO userExpiredDTO) {
        LocalDate localDate = LocalDate.now();
        return waybillVehicleMapper.ExpiredVehicle(userExpiredDTO,localDate+"");
    }

    /**
     * 根据时间段查询从业资格证过期人员
     *
     * @param userExpiredDTO
     * @return
     */
    @Override
    public List<UserExpiredVO> HistoryExpiredVehicle(UserExpiredDTO userExpiredDTO) {
        return waybillVehicleMapper.HistoryExpiredVehicle(userExpiredDTO);
    }

    /**
     * 车辆行驶轨迹查询
     *
     * @param vehicleTrackDTO
     * @return
     */
    @Override
    public List<VehicleTrackVO> VehicleTrack(VehicleTrackDTO vehicleTrackDTO) {
        List<VehicleTrackVO> vehicleTrackVOS = waybillVehicleMapper.VehicleTrack(vehicleTrackDTO);
        //坐标纠偏
        for (VehicleTrackVO vehicleTrackVO:vehicleTrackVOS){
            double[] doubles = CoordinateUtils.wgs84ToBd09( Double.parseDouble(vehicleTrackVO.getLon()),Double.parseDouble(vehicleTrackVO.getLat()));
            vehicleTrackVO.setLon(String.valueOf(doubles[0]));
            vehicleTrackVO.setLat(String.valueOf(doubles[1]));
        }
        return vehicleTrackVOS;
    }

}
