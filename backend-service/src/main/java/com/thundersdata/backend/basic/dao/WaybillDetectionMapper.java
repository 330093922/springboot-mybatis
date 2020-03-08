package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillDetectionDTO;
import com.thundersdata.backend.basic.dto.WaybillDetectionDictDTO;
import com.thundersdata.backend.basic.model.WaybillDetection;
import com.thundersdata.backend.basic.model.WaybillDetectionWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillDetectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillDetectionWithBLOBs record);

    int insertSelective(WaybillDetectionWithBLOBs record);

    WaybillDetectionWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillDetectionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WaybillDetectionWithBLOBs record);

    /**
     * 修改自检内容信息
     * @param record 自检内容对象
     * @return
     */
    int updateByPrimaryKey(WaybillDetection record);

    /**
     * 查询自检内容管理数量
     * @param type 自检类型
     * @param project 自检项目
     * @param content 自检内容
     * @return
     */
    int getCountByDetection(@Param("type") String type, @Param("project") String project, @Param("content") String content);

    /**
     * 自检内容管理列表(分页)
     * @param type 自检类型
     * @param project 自检项目
     * @param content 自检内容
     * @param limit  页码
     * @param offset 数量
     * @return
     */
    List<WaybillDetectionDTO> selectAllDetection(@Param("type") String type, @Param("project") String project, @Param("content") String content,
                                                 @Param("limit") Integer limit, @Param("offset") Integer offset);

    /**
     * 根据id删除自检内容
     * @param waybillDetectionList 自检id集合
     * @return
     */
    int deleteDetection(@Param("waybillDetectionList") List<WaybillDetection> waybillDetectionList);

    /**
     * 自检内容下拉列表框
     * @param type 自检类型
     * @param project 自检项目
     * @param content 自检内容
     * @return
     */
    List<WaybillDetectionDictDTO> dropList(@Param("type") String type, @Param("project") String project, @Param("content") String content);

    /**
     * 根据id查询自检内容详细信息
     * @param id
     * @return
     */
    WaybillDetectionDTO getDetail(Integer id);
}