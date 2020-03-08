package com.thundersdata.backend.basic.controller;


import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDetailsDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.service.WaybillOwnerService;
import com.thundersdata.backend.basic.vo.EnterpriseLlistVo;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.WaybillOwnerVo;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;
import java.util.List;

/**
 * @Classname WaybillOwnerController
 * @Description TODO
 * @Date 2019/12/6 10:28
 * @Created wrc
 */

@Api(tags = "企业信息接口")
@RestController
@RequestMapping("/WaybillOwner")
public class WaybillOwnerController {

    @Autowired
    private WaybillOwnerService waybillOwnerService;


    /**
     * 查询企业详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询企业详情", notes = "通过企业id")
    @GetMapping("listWaybillOwner")
    public WaybillOwnerDetailsDTO selectByPrimaryKey(@RequestParam String id) {
        return waybillOwnerService.selectByPrimaryKey(id);
    }

    /**
     * 企业列表
     *
     * @param enterpriseLlistVO
     * @return
     */
    @ApiOperation(value = "查询企业列表", notes = "可以通过企业名称，企业信用代码，联系人模糊查询")
    @PostMapping("list")
    public Pagination<WaybillOwnerDetailsDTO> selectWaybillOwnerLike(@RequestBody EnterpriseLlistVo enterpriseLlistVO ,@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        enterpriseLlistVO.setDataList(ownerIds);
        enterpriseLlistVO.setIsScope(isScope);
        return waybillOwnerService.selectWaybillOwnerLike(enterpriseLlistVO);
    }

    /**
     * 企业下拉框
     *
     * @param ownerName
     * @return
     */
    @ApiOperation(value = "企业下拉框", notes = "企业名字模糊查询")
    @PostMapping("dropDown")
    public List<WaybillOwnerDTO> dropDown( String ownerName) {
        return waybillOwnerService.dropdown(ownerName);
    }

    /**
     * 删除企业信息
     *
     * @param WaybillOwner
     * @return
     */
    @ApiOperation(value = "删除企业", notes = "企业id")
    @PostMapping("deleteId")
    public int deleteId( @RequestBody List<WaybillOwner> WaybillOwner,@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillOwnerService.deleteId(WaybillOwner, isScope, ownerIds);
    }

    /**
     * 添加企业
     * @param waybillOwnerVo
     * @return
     * +-
     */
    @ApiOperation(value = "添加企业", notes = "企业名称，工商注册号，统一信用代码 ，联系人，联系电话，法人，法人电话，经度，纬度，当前用户id")
    @PostMapping("insertSelective")
    public int insertSelective(@RequestBody WaybillOwnerVo waybillOwnerVo,String userId) {
        waybillOwnerVo.setPersonId(Integer.valueOf(userId));
        return waybillOwnerService.insertSelective(waybillOwnerVo);
    }

    /**
     * 修改企业信息
     *
     * @param record
     * @return
     */
    @ApiOperation(value = "修改企业", notes = "")
    @PostMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody WaybillOwnerVo record,@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        record.setDataList(ownerIds);
        record.setIsScope(isScope);
        return waybillOwnerService.updateByPrimaryKeySelective(record);
    }


    /**
     * 批量修改企业审批状态
     *
     * @param waybillAreas
     * @return
     */
    @ApiModelProperty(value = "批量修改企业审批状态",notes = "必填企业id")
    @PostMapping("batchModifyStatus")
    public int batchModifyStatus(@RequestBody List<WaybillArea> waybillAreas) {
        return waybillOwnerService.batchModifyStatus(waybillAreas);
    }



    /**
     * 数据范围判断下拉框
     *
     * @param ownerName
     * @param isScope
     * @param dataList
     * @return
     */
    @ApiModelProperty(value = "数据范围判断下拉框",notes = "必填企业名称")
    @PostMapping("dataDropDown")
    public WaybillOwnerDTO dataDropDown(String ownerName,@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillOwnerService.dataDropDown(ownerName,isScope,ownerIds);
    }


    /**
     * 查询查询经营许可证过期企业
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "查询经营许可证过期企业", notes = "区域")
    @PostMapping("UserExpired")
    public List<UserExpiredVO> ExpiredOwner(@RequestBody UserExpiredDTO userExpiredDTO) {
        return waybillOwnerService.ExpiredOwner(userExpiredDTO);
    }


    /**
     * 根据时间段查询经营许可证过期企业
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "根据时间段查询经营许可证过期企业", notes = "区域，开始时间，结束时间")
    @PostMapping("HistoryUserExpired")
    public List<UserExpiredVO> HistoryExpiredOwner(@RequestBody UserExpiredDTO userExpiredDTO) {
        return waybillOwnerService.HistoryExpiredOwner(userExpiredDTO);
    }

    /**
     * 根据多个企业id查询企业详细信息
     * @param ownerIds
     * @return
     */
    @ApiOperation(value = "多个查询企业详情", notes = "通过企业ownerIds")
    @PostMapping("detailByIds")
    public List<WaybillOwnerDetailsDTO> selectByOwerIds(@RequestBody List<Integer> ownerIds) {
        return waybillOwnerService.selectByOwerIds(ownerIds);
    }
}
