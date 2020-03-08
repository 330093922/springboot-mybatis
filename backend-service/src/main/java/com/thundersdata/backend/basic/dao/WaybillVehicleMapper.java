package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.VehicleTrackDTO;
import com.thundersdata.backend.basic.dto.WaybillVehicleDTO;
import com.thundersdata.backend.basic.model.WaybillVehicle;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.VehicleTrackVO;
import com.thundersdata.backend.basic.vo.WaybillVehicleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillVehicleMapper {

    Integer deleteWaybillVehicle(@Param("list") List<WaybillVehicleVO> list,
                                 @Param("ownerIds") Integer[] ownerIds,
                                 @Param("isScope") Boolean isScope);

    int deleteByPrimaryKey(String id);

    int insert(WaybillVehicle record);

    int insertSelective(WaybillVehicle record);

    WaybillVehicle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillVehicle record);

    int updateByPrimaryKey(WaybillVehicle record);

    int updateByPrimaryKeySelectiveWithScope(@Param("record") WaybillVehicle record,
                                    @Param("ownerIds") Integer[] ownerIds,
                                    @Param("isScope") Boolean isScope);

    /**
     * 查询车辆数量
     *
     * @param queryVO 查询条件
     * @return 数量
     */
    int getCountByCondition(@Param("queryVO") WaybillVehicleVO queryVO,
                            @Param("ownerIds") Integer[] ownerIds,
                            @Param("isScope") Boolean isScope);

    /**
     * 查询车辆列表
     *
     * @param queryVO 查询条件
     * @param limit    查询数量
     * @param offset   偏移量
     * @return 列表
     */
    List<WaybillVehicleDTO> getListByCondition(@Param("queryVO") WaybillVehicleVO queryVO,
                                               @Param("limit") Integer limit,
                                               @Param("offset") Integer offset,
                                               @Param("ownerIds") Integer[] ownerIds,
                                               @Param("isScope") Boolean isScope);

    /**
     * 查询车辆详情
     *
     * @param id 查询详情
     * @return 列表
     */
    WaybillVehicleDTO getDetailsByCondition(@Param("id") String id);


    /**
     * 修改车辆和挂车状态
     * @param waybillVehicle
     * @return
     */
    int stateModification(WaybillVehicle waybillVehicle);



    /**
     * 车辆下拉菜单
     *
     * @param vehicleCode 车牌号
     * @return 列表
     */
    List<WaybillVehicleDTO>dropList(@Param("vehicleCode") String vehicleCode);


    /**
     * 车辆下拉菜单--带权限
     *
     * @param vehicleCode 车牌号
     * @return 列表
     */
    List<WaybillVehicleDTO>dropListWithScope(@Param("vehicleCode") String vehicleCode,
                                             @Param("ownerIds") Integer[] ownerIds,
                                             @Param("isScope") Boolean isScope);

    /**
     * 改状态
     * @param vehicleId
     * @param trailerId
     * @return
     */
    int updateStates(@Param("vehicleId") Integer vehicleId,
                     @Param("trailerId") Integer trailerId);


    /**
     *   查询过期道路运输证号车辆
     * @return
     */
    List<UserExpiredVO> ExpiredVehicle(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO, @Param("newTime") String newTime);

    /**
     * 根据时间段查询从业资格证过期人员
     * @param userExpiredDTO
     * @return
     */
    List<UserExpiredVO> HistoryExpiredVehicle(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO);


    /**
     * 车辆行驶轨迹查询
     * @param vehicleTrackDTO
     * @return
     */
    List<VehicleTrackVO> VehicleTrack(@Param("vehicleTrackDTO") VehicleTrackDTO vehicleTrackDTO);
}