package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillRouteDTO;
import com.thundersdata.backend.basic.model.WaybillRoute;
import com.thundersdata.backend.basic.model.WaybillRouteWithBLOBs;
import com.thundersdata.backend.basic.vo.WaybillRouteVO;
import org.apache.ibatis.annotations.Param;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

public interface WaybillRouteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillRouteWithBLOBs record);

    /**
     * 插入一条线路管理数据
     * @param record
     * @return
     */
    int insertSelective(WaybillRouteWithBLOBs record);

    /**
     * 查看线路管理详情
     * @param id
     * @return
     */
    WaybillRouteWithBLOBs selectByPrimaryKey(@Param("id") Integer id, @Param("dataList") String dataList, @Param("isScope") Boolean isScope);

    /**
     * 修改一条线路管理数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WaybillRouteWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WaybillRouteWithBLOBs record);

    int updateByPrimaryKey(WaybillRoute record);

    /**
     * 条件查询线路管理列表
     * @param waybillRouteVO
     * @param page
     * @param pageSize
     * @return
     */
    List<WaybillRouteDTO> selectList(@Param("waybillRouteVO") WaybillRouteVO waybillRouteVO, @Param("page") Integer page, @Param("pageSize") Integer pageSize, @Param("dataList") String dataList, @Param("isScope") Boolean isScope);

    /**
     * 统计条件查询中的总条数
     * @param waybillRouteVO
     * @return
     */
    int getCountByCondition(WaybillRouteVO waybillRouteVO);

    /**
     * 修改一条线路管理数据为“已删除”
     * @param id
     * @return
     */
    int deleteById(@Param("id") Integer id, @Param("dataList") String dataList, @Param("isScope") Boolean isScope);

    /**
     * 线路下拉框查询
     * @param name
     * @return
     */
    List<WaybillRouteDTO> selectDropList(@Param("name") String name, @Param("dataList") String dataList, @Param("isScope") Boolean isScope);

    /**
     * 去掉分页功能，全列表返回的查询
     * @return
     */
    List<WaybillRouteDTO> selectWholeList(@Param("dataList") String dataList, @Param("isScope") Boolean isScope);
}