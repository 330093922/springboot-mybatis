package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.WaybillAreaDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.service.WaybillAreaService;
import com.thundersdata.backend.basic.vo.WaybillAreaVo;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Classname WaybillAreaController
 * @Description TODO
 * @Date 2019/12/13 14:32
 * @Created wrc
 */
@Api(tags = "装卸信息接口")
@RestController
@RequestMapping("WaybillArea")
public class WaybillAreaController {
    @Autowired
    private WaybillAreaService waybillAreaService;

    /**
     * 装卸区域列表
     *
     * @param waybillAreaVo
     * @return
     */
    @ApiOperation(value = "查询装卸区域列表", notes = "可以通过装卸区域编号，装卸区域名称，可装卸货货物名称")
    @PostMapping("list")
    public Pagination<WaybillArea> selectWaybillOwnerLike(@RequestBody WaybillAreaVo waybillAreaVo,@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        waybillAreaVo.setDataList(ownerIds);
        waybillAreaVo.setIsScope(isScope);
        return waybillAreaService.selectWaybillOwnerLike(waybillAreaVo);
    }

    /**
     * 查询装卸区详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询装卸区域详情", notes = "通过id")
    @PostMapping("get")
    public WaybillArea selectByPrimaryKey(@RequestParam Integer id) {
        Assert.notNull(id, "装卸区域id不能为空！");
        return waybillAreaService.selectByPrimaryKey(id);
    }

    /**
     * 添加装卸区域
     *
     * @param waybillArea
     * @return
     */
    @ApiOperation(value = "添加装卸区域", notes = "必填字段装卸区域编号 ，装卸区域名称，装卸区类型，危险特性分类，装卸区域名称，危险特性分类，投入日期，装卸区域实景")
    @PostMapping("add")
    public int updateByPrimaryKeyWithBLOBs(@RequestBody WaybillArea waybillArea) {
        Assert.notNull(waybillArea.getOwnerId(),"企业id不能为空！");
        Assert.notNull(waybillArea.getAreaNo(),"卸区域编号不能为空！");
        Assert.notNull(waybillArea.getAreaName(),"装卸区域名称不能为空！");
        Assert.notNull(waybillArea.getType(),"装卸区类型不能为空！");
        Assert.notNull(waybillArea.getGoodsName(),"可装卸货货物名称不能为空！");
        Assert.notNull(waybillArea.getClassify(),"危险特性分类不能为空！");
        Assert.notNull(waybillArea.getInputDate(),"投入日期不能为空！");
        Assert.notNull(waybillArea.getImageUrl(),"装卸区域实景不能为空！");
        return waybillAreaService.insertSelective(waybillArea);
    }

    /**
     * 批量删除装卸区域
     *
     * @param idList
     * @return
     */
    @ApiOperation(value = "删除装卸区域", notes = "通过id")
    @PostMapping("delete")
    public int deleteId(@RequestBody List<WaybillArea> idList,@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillAreaService.deleteId(idList,isScope,ownerIds);
    }

    /**
     * 修改装卸区
     *
     * @param record
     * @return
     */
    @ApiOperation(value = "修改装卸区域", notes = "通过id")
    @PostMapping("update")
    public int updateByPrimaryKey(@RequestBody WaybillArea record, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillAreaService.updateByPrimaryKeySelective(record,isScope,ownerIds);
    }

    /**
     * 装卸区域下拉框
     *
     * @param areaNo
     * @return
     */
    @ApiOperation(value = "装卸区域下拉框", notes = "装卸区域编号 ")
    @PostMapping("dropDown")
    public List<WaybillAreaDTO> dropDown(String areaNo) {
        return waybillAreaService.dropDown(areaNo);
    }


    /**
     * 数据范围判断装卸区域下拉框
     *
     * @param areaNo
     * @param isScope
     * @param dataList
     * @return
     */
    @PostMapping("dataDropDown")
    public List<WaybillAreaDTO> dataDropDown(String areaNo, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillAreaService.dataDropDown(areaNo,isScope,ownerIds);
    }




}
