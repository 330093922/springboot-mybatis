package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.WaybillRouteDTO;
import com.thundersdata.backend.basic.model.WaybillRouteWithBLOBs;
import com.thundersdata.backend.basic.vo.WaybillRouteVO;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

public interface WaybillRouteService {
    /**
     * 条件查询线路管理列表
     * @param waybillRouteVO
     * @param page
     * @param pageSize
     * @return
     */
    Pagination<WaybillRouteDTO> selectList(WaybillRouteVO waybillRouteVO, Integer page, Integer pageSize, String dataList, Boolean isScope);

    /**
     * 查看线路管理详情
     * @param id
     * @return
     */
    WaybillRouteWithBLOBs selectByPrimaryKey(Integer id, String dataList, Boolean isScope);

    /**
     * 修改一条线路管理数据为“已删除”
     * @param id
     * @return
     */
    int deleteById(Integer id, String dataList, Boolean isScope);

    /**
     * 插入一条线路管理数据
     * @param record
     * @return
     */
    int insertSelective(WaybillRouteWithBLOBs record);

    /**
     * 修改一条线路管理数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WaybillRouteWithBLOBs record);

    /**
     * 线路下拉框查询
     * @param name
     * @return
     */
    List<WaybillRouteDTO> selectDropList(String name, String dataList, Boolean isScope);

    /**
     * 去掉分页功能，全列表返回的查询
     * @return
     */
    List<WaybillRouteDTO> selectWholeList(String dataList, Boolean isScope);
}
