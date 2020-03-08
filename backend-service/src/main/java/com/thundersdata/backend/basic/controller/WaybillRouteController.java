package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.WaybillRouteDTO;
import com.thundersdata.backend.basic.model.WaybillRouteWithBLOBs;
import com.thundersdata.backend.basic.service.WaybillRouteService;
import com.thundersdata.backend.basic.vo.WaybillRouteVO;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;

@Api(tags = "运输路线管理接口")
@RestController
@RequestMapping("route")
public class WaybillRouteController {
    @Autowired
    private WaybillRouteService waybillRouteService;

    /**
     * 条件查询线路管理列表
     * @param waybillRouteVO
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "条件查询线路列表", notes = "通过线路名称，途径，始发地，目的地")
    @PostMapping("selectList")
    public Pagination<WaybillRouteDTO> selectList(WaybillRouteVO waybillRouteVO,
                                                  Integer page,
                                                  Integer pageSize, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillRouteService.selectList(waybillRouteVO, page, pageSize, dataList, isScope);
    }

    /**
     * 查看线路管理详情
     * @param id
     * @return
     */
    @ApiOperation(value = "查询线路详情", notes = "通过线路id,返回线路详情")
    @GetMapping("selectById")
    public WaybillRouteWithBLOBs selectByPrimaryKey(String id, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillRouteService.selectByPrimaryKey(Integer.parseInt(id), dataList, isScope);
    }

    /**
     * 修改一条线路管理数据为“已删除”
     * @param id
     * @return
     */
    @ApiOperation(value = "删除线路", notes = "通过线路id,删除线路")
    @PostMapping("deleteById")
    public int deleteById(Integer id, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillRouteService.deleteById(id, dataList, isScope);
    }

    /**
     * 插入一条线路管理数据
     * @param record
     * @return
     */
    @ApiOperation(value = "新增线路", notes = "新增一条线路记录")
    @PutMapping("insert")
    public int insertSelective(WaybillRouteWithBLOBs record){
        System.out.println("添加：     "+record.toString());
        return waybillRouteService.insertSelective(record);
    }

    /**
     * 修改一条线路管理数据
     * @param record
     * @return
     */
    @ApiOperation(value = "修改线路", notes = "根据线路id，修改线路记录")
    @PostMapping("update")
    public int updateByPrimaryKeySelective(WaybillRouteWithBLOBs record, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        record.setDataList(dataList);
        record.setIsScope(isScope);
        return waybillRouteService.updateByPrimaryKeySelective(record);
    }

    /**
     * 线路下拉框查询
     * @param name
     * @return
     */
    @ApiOperation(value = "线路下拉框", notes = "根据线路名称，查询模糊匹配的20条线路")
    @PostMapping("selectDropList")
    public List<WaybillRouteDTO> selectDropList(String name, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillRouteService.selectDropList(name, dataList, isScope);
    }

    /**
     * 去掉分页功能，全列表返回的查询
     * @return
     */
    @ApiOperation(value = "线路信息全列表", notes = "去掉分页功能，全列表返回的查询")
    @GetMapping("selectWholeList")
    public List<WaybillRouteDTO> selectWholeList(@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){
        return waybillRouteService.selectWholeList(dataList, isScope);
    }
}