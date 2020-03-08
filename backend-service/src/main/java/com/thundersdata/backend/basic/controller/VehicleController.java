package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.VehicleTrackDTO;
import com.thundersdata.backend.basic.dto.WaybillVehicleDTO;
import com.thundersdata.backend.basic.model.WaybillVehicle;
import com.thundersdata.backend.basic.service.VehicleService;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.VehicleTrackVO;
import com.thundersdata.backend.basic.vo.WaybillVehicleVO;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "车辆管理接口")
@RestController
@RequestMapping("Vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * 条件查询车辆列表
     *
     * @param queryVO 查询条件
     * @return 列表
     */
    @ApiOperation(value = "条件查询车辆列表", notes = "通过车牌号,车牌类型,营运状态,返回车辆列表")
    @PostMapping("getVehicleList")
    public Pagination<WaybillVehicleDTO> getVehicleList(@RequestBody WaybillVehicleVO queryVO , @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){

        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        Integer page = queryVO.getPage();
        Integer pageSize = queryVO.getPageSize();
        return vehicleService.getListByCondition(queryVO, page, pageSize,ownerIds,isScope);
    }

    /**
     *     查询车辆详情
     *
     * @param id 查询条件
     * @return 列表
     */
    @ApiOperation(value = "查询车辆详情", notes = "通过车牌id,返回车辆详情")
    @PostMapping("getDetails")
    public List<WaybillVehicleDTO> getDetails(String id){
        Assert.notNull(id, "车辆id不能为空");
        return vehicleService.getDetailsByCondition(id);
    }

    /**
     * 车辆下拉框
     * @return
     */
    @ApiOperation(value = "车辆下拉框", notes = "通过车牌号码,模糊匹配")
    @PostMapping("dropList")
    public List<WaybillVehicleDTO> dropList(String vehicleCode) {
        return vehicleService.dropList(vehicleCode);
    }


    /**
     * 车辆下拉框
     * @return
     */
    @ApiOperation(value = "车辆下拉框范围判断", notes = "通过车牌号码,模糊匹配")
    @PostMapping("dropListWithScope")
    public List<WaybillVehicleDTO> dropListWithScope(String vehicleCode, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return vehicleService.dropListWithScope(vehicleCode,ownerIds,isScope);
    }


    /**
     * 新增车辆
     * @param waybillVehicle
     * @return
     */
    @ApiOperation(value = "新增车辆", notes = "新增车辆实体类")
    @PostMapping("add")
    public int insertVehicle(@RequestBody WaybillVehicle waybillVehicle){

        return vehicleService.insertVehicle(waybillVehicle);
    }

    /**
     * 修改车辆
     * @param waybillVehicle
     * @return
     */
    @ApiOperation(value = "修改车辆", notes = "车辆实体类")
    @PostMapping("update")
    public int updateVehicle(@RequestBody WaybillVehicle waybillVehicle, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        String id = waybillVehicle.getId();
        Assert.notNull(id,"车辆id不能为空");
        return vehicleService.updateVehicleWithScope(waybillVehicle,ownerIds,isScope);
    }

    /**
     * delete
     * @param list
     * @return
     */
    @ApiOperation(value = "删除车辆", notes = "通过车辆id,删除车辆")
    @PostMapping("delete")
    public Integer deleteVehicle(@RequestBody List<WaybillVehicleVO>list, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){

        for (WaybillVehicleVO queryVO :list){
            Integer id = queryVO.getId();
            Assert.notNull(id, "车辆id 不能为空");
        }

        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return vehicleService.deleteVehicle(list,ownerIds,isScope);
    }

    /**
     * 查询查询道路运输证过期车辆
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "查询道路运输证过期车辆", notes = "区域")
    @PostMapping("ExpiredVehicle")
    public List<UserExpiredVO> ExpiredVehicle(@RequestBody UserExpiredDTO userExpiredDTO) {
        return vehicleService.ExpiredVehicle(userExpiredDTO);
    }


    /**
     * 根据时间段查询道路运输证过期车辆
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "根据时间段查询道路运输证过期车辆", notes = "区域，开始时间，结束时间")
    @PostMapping("HistoryExpiredVehicle")
    public List<UserExpiredVO> HistoryExpiredVehicle(@RequestBody UserExpiredDTO userExpiredDTO) {
        return vehicleService.HistoryExpiredVehicle(userExpiredDTO);
    }



    /**
     * 车辆行驶轨迹查询
     *
     * @param vehicleTrackDTO
     * @return
     */
    @ApiOperation(value = "车辆行驶轨迹查询", notes = "车辆id，开始时间，结束时间")
    @PostMapping("VehicleTrack")
    public List<VehicleTrackVO> VehicleTrack(@RequestBody VehicleTrackDTO vehicleTrackDTO) {
        return vehicleService.VehicleTrack(vehicleTrackDTO);
    }


}


