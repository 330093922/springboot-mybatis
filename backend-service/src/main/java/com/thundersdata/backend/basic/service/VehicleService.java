package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.VehicleTrackDTO;
import com.thundersdata.backend.basic.dto.WaybillVehicleDTO;
import com.thundersdata.backend.basic.model.WaybillVehicle;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.VehicleTrackVO;
import com.thundersdata.backend.basic.vo.WaybillVehicleVO;
import com.thundersdata.backend.common.utils.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VehicleService {

    /**
     * 查询车辆列表
     *
     * @param queryVO 查询条件
     * @return 列表
     */
    Pagination<WaybillVehicleDTO> getListByCondition(WaybillVehicleVO queryVO,
                                                     Integer page,
                                                     Integer pageSize,
                                                     Integer[] ownerIds,
                                                     Boolean isScope);

    /**
     * 查询车辆详情
     *
     * @param id 查询详情
     * @return 列表
     */
    List<WaybillVehicleDTO> getDetailsByCondition(String id);

    /**
     * 车辆下拉框
     * @return
     */
    List<WaybillVehicleDTO> dropList(String vehicleCode);

    /**
     * 车辆下拉框
     * @return
     */
    List<WaybillVehicleDTO> dropListWithScope(String vehicleCode,
                                              Integer[] ownerIds,
                                              Boolean isScope);

    /**
     * 新增车辆
     *
     * @param waybillVehicle
     */
    int insertVehicle(WaybillVehicle waybillVehicle);

    /**
     * 修改车辆
     * @param waybillVehicle
     * @return
     */
    int updateVehicleWithScope(WaybillVehicle waybillVehicle,
                               Integer[] ownerIds,
                               Boolean isScope);


    /**
     * 删除车辆
     * @param list
     * @return
     */
    Integer deleteVehicle(List<WaybillVehicleVO> list,
                          Integer[] ownerIds,
                          Boolean isScope);


    /**
     * 查询过期道路运输证号车辆
     * @return
     */
    List<UserExpiredVO> ExpiredVehicle( UserExpiredDTO userExpiredDTO);

    /**
     * 根据时间段查询从业资格证过期人员
     * @param userExpiredDTO
     * @return
     */
    List<UserExpiredVO> HistoryExpiredVehicle(UserExpiredDTO userExpiredDTO);



    /**
     * 车辆行驶轨迹查询
     * @param vehicleTrackDTO
     * @return
     */
    List<VehicleTrackVO> VehicleTrack(VehicleTrackDTO vehicleTrackDTO);
}
