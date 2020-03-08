package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillCameraDTO;
import com.thundersdata.backend.basic.model.WaybillCamera;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillCameraMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillCamera record);

    int insertSelective(WaybillCamera record);

    WaybillCamera selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillCamera record);

    int updateByPrimaryKey(WaybillCamera record);

    /**
     * 查询数量
     * @param equipmentNo 设备编号
     * @param name  设备名称
     * @param installArea 安装区域
     * @return
     */
    int getCountByCondition(@Param("equipmentNo") String equipmentNo,
                            @Param("name") String name,
                            @Param("installArea") String installArea,
                            @Param("ownerIds") Integer[] ownerIds,
                            @Param("isScope") Boolean isScope);

    /**
     * 摄像头列表
     * @param equipmentNo 设备编号
     * @param name 设备名称
     * @param installArea 按装区域
     * @return
     */
    List<WaybillCameraDTO> getListByCondition(@Param("equipmentNo") String equipmentNo,
                                              @Param("name") String name,
                                              @Param("installArea") String installArea,
                                              @Param("limit") Integer limit,
                                              @Param("offset") Integer offset,
                                              @Param("ownerIds") Integer[] ownerIds,
                                              @Param("isScope") Boolean isScope);

    /**
     * 摄像头详情
     * @param id id
     * @return
     */
    WaybillCameraDTO getDetailsByCondition( Integer id);


    /**
     * 下拉框
     * @param name
     * @return
     */
    List<WaybillCameraDTO> dropList(@Param("name") String name);


    /**
     * 下拉框帶範圍
     * @param name
     * @return
     */
    List<WaybillCameraDTO> dropListWithScope(@Param("name") String name);
}