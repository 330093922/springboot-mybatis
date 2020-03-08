package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.WaybillCameraDTO;
import com.thundersdata.backend.basic.dto.WaybillVehicleDTO;
import com.thundersdata.backend.basic.model.WaybillCamera;
import com.thundersdata.backend.basic.model.WaybillVehicle;
import com.thundersdata.backend.basic.service.CameraService;
import com.thundersdata.backend.basic.vo.WaybillCameraVO;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "摄像头管理接口")
@RestController
@RequestMapping("Camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    /**
     * 条件查询摄像头列表
     *
     * @param queryVO 查询条件
     * @return 列表
     */
    @ApiOperation(value = "条件查询摄像头列表", notes = "通过设备编号,设备名称,安装区域,返回摄像头列表")
    @PostMapping("getList")
    public Pagination<WaybillCameraDTO> getList(@RequestBody WaybillCameraVO queryVO, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){

        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return cameraService.getListByCondition(queryVO,ownerIds,isScope);
    }

    /**
     * 查询摄像头所有列表
     *
     * @param queryVO 查询条件
     * @return 列表
     */
    @ApiOperation(value = "条件查询所有摄像头", notes = "通过设备编号,设备名称,安装区域,返回所有摄像头")
    @PostMapping("getListAll")
    public List<WaybillCameraDTO> getListAll(@RequestBody WaybillCameraVO queryVO, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){

        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return cameraService.getListWithoutPagination(queryVO,ownerIds,isScope);
    }
    /**
     *     查询摄像头详情
     *
     * @param id 摄像头id
     * @return 列表
     */
    @ApiOperation(value = "查询摄像头详情", notes = "通过摄像头id,返回摄像头详细信息")
    @GetMapping("getDetails")
    public WaybillCameraDTO getDetails(Integer id){
        Assert.notNull(id, "摄像头id不能为空");

        return cameraService.getDetailsByCondition(id);
    }


    /**
     * 下拉框
     * @param name
     * @return
     */
    @ApiOperation(value = "摄像头下拉框", notes = "通过摄像头name,返回摄像头下拉框")
    @PostMapping("dropList")
    public List<WaybillCameraDTO> dropList(String name){
        return cameraService.dropList(name);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改摄像头状态", notes = "修改摄像头状态")
    @PostMapping("update")
    public int updateCamera(@RequestBody WaybillCamera waybillCamera){
        Integer id = waybillCamera.getId();
        Assert.notNull(id,"摄像头id不能为空");

        return cameraService.updateCamera(waybillCamera);
    }
}
