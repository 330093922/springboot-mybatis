package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.WaybillDetectionDTO;
import com.thundersdata.backend.basic.dto.WaybillDetectionDictDTO;
import com.thundersdata.backend.basic.model.WaybillDetection;
import com.thundersdata.backend.basic.model.WaybillDetectionWithBLOBs;
import com.thundersdata.backend.basic.service.WaybillDetectionService;
import com.thundersdata.backend.basic.vo.DetectionListVo;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 自检内容管理控制器
 */
@Api(tags = "自检内容管理接口")
@RestController
@RequestMapping("detection")
public class WaybillDetectionController {
    @Autowired
    private WaybillDetectionService waybillDetectionService;

    /**
     * 查询自检内容管理分页
     * @param detectionListVo
     * @return
     */
    @ApiOperation(value = "查询自检内容管理信息列表", notes = "通过参数进行模糊查询")
    @PostMapping("list")
    public Pagination<WaybillDetectionDTO> selectAllWaybillDetection(@RequestBody DetectionListVo detectionListVo) {
        return waybillDetectionService.selectDetection(detectionListVo.getType(),detectionListVo.getProject(),detectionListVo.getContent(),detectionListVo.getPage(), detectionListVo.getPageSize());
    }

    /**
     * 新增自检内容管理
     * @param waybillDetectionWithBLOBs
     * @return
     */
    @ApiOperation(value = "新增自检内容管理", notes = "全量新增")
    @PostMapping("insert")
    public int insertDetection(@RequestBody WaybillDetectionWithBLOBs waybillDetectionWithBLOBs) {
        return waybillDetectionService.insertDetection(waybillDetectionWithBLOBs);
    }

    /**
     * 修改自检内容管理
     * @param waybillDetectionWithBLOBs
     * @return
     */
    @ApiOperation(value = "修改自检内容管理", notes = "全量修改")
    @PostMapping("update")
    public int updateDetection(@RequestBody WaybillDetectionWithBLOBs waybillDetectionWithBLOBs) {
        return waybillDetectionService.updateDetection(waybillDetectionWithBLOBs);
    }

    /**
     * 删除自检内容管理
     * @param waybillDetectionList
     * @return
     */
    @ApiOperation(value = "删除自检内容管理", notes = "全量删除")
    @PostMapping("delete")
    public int deleteDetection(@RequestBody List<WaybillDetection> waybillDetectionList) {
        return waybillDetectionService.deleteDetection(waybillDetectionList);
    }
    /**
     * 根据id查询自检内容管理详细信息
     * @param id
     * @return
     */
    @ApiOperation(value = "自检内容管理详细信息", notes = "根据id查询")
    @PostMapping("detail")
    public WaybillDetectionDTO getDetail(Integer id) {
        return waybillDetectionService.getDetail(id);
    }
    /**
     * 自检内容下拉列表框
     * @param detectionListVo
     * @return
     */
    @ApiOperation(value = "自检内容管理下拉框", notes = "模糊匹配")
    @PostMapping("dropList")
    public List<WaybillDetectionDictDTO> dropList(@RequestBody DetectionListVo detectionListVo) {
        return waybillDetectionService.dropList(detectionListVo.getType(),detectionListVo.getProject(),detectionListVo.getContent());
    }
}
