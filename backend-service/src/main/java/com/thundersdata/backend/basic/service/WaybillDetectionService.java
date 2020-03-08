package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.WaybillDetectionDTO;
import com.thundersdata.backend.basic.dto.WaybillDetectionDictDTO;
import com.thundersdata.backend.basic.model.WaybillDetection;
import com.thundersdata.backend.basic.model.WaybillDetectionWithBLOBs;
import com.thundersdata.backend.common.utils.Pagination;

import java.util.List;

/**
 * 自检内容管理
 */
public interface WaybillDetectionService {
    /**
     * 查询自检内容信息列表分页
     * @param type 检查类型
     * @param project 检查项目
     * @param content 检查内容
     * @param page
     * @param pageSize
     * @return
     */
    Pagination<WaybillDetectionDTO> selectDetection(String type,
                                                    String project,
                                                    String content,
                                                    Integer page, Integer pageSize);

    /**
     * 新增自检内容管理
     * @param waybillDetectionWithBLOBs
     * @return
     */
    int insertDetection(WaybillDetectionWithBLOBs waybillDetectionWithBLOBs);

    /**
     * 修改自检内容管理
     * @param waybillDetectionWithBLOBs
     * @return
     */
    int updateDetection(WaybillDetectionWithBLOBs waybillDetectionWithBLOBs);

    /**
     * 删除自检内容
     * @param waybillDetectionList 自检id集合
     * @return
     */
    int deleteDetection(List<WaybillDetection> waybillDetectionList);

    /**
     * 自检内容管理下拉列表框
     * @param type
     * @param project
     * @param content
     * @return
     */
    List<WaybillDetectionDictDTO>dropList (String type, String project, String content);

    /**
     * 根据id查询自检内容管理详细信息
     * @param id
     * @return
     */
    WaybillDetectionDTO getDetail(Integer id);
}
