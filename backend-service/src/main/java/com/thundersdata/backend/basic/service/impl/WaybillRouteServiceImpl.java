package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.WaybillRouteMapper;
import com.thundersdata.backend.basic.dto.WaybillRouteDTO;
import com.thundersdata.backend.basic.dto.WaybillVehicleDTO;
import com.thundersdata.backend.basic.model.WaybillRouteWithBLOBs;
import com.thundersdata.backend.basic.service.WaybillRouteService;
import com.thundersdata.backend.basic.vo.WaybillRouteVO;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class WaybillRouteServiceImpl implements WaybillRouteService {
    @Autowired
    private WaybillRouteMapper waybillRouteMapper;

    /**
     * 条件查询线路管理列表
     * @param waybillRouteVO
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<WaybillRouteDTO> selectList(WaybillRouteVO waybillRouteVO,
                                            Integer page,
                                            Integer pageSize,
                                            String dataList, Boolean isScope) {
        PageParam pageparam = PageUtils.init(page, pageSize);
        int total = waybillRouteMapper.getCountByCondition(waybillRouteVO);
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }
        System.out.println("这个是前台传回来的参数:   "+waybillRouteVO+"..."+page+"..."+pageSize+"..."+dataList+"..."+isScope);
        List<WaybillRouteDTO> list_ = waybillRouteMapper.selectList(waybillRouteVO, pageparam.getLimit(), pageparam.getOffset(), dataList, isScope);
        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, list_);
    }

    /**
     * 查看线路管理详情
     * @param id
     * @return
     */
    @Override
    public WaybillRouteWithBLOBs selectByPrimaryKey(Integer id, String dataList, Boolean isScope) {
        return waybillRouteMapper.selectByPrimaryKey(id, dataList, isScope);
    }

    /**
     * 修改一条线路管理数据为“已删除”
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id, String dataList, Boolean isScope) {
        return waybillRouteMapper.deleteById(id, dataList, isScope);
    }

    /**
     * 插入一条线路管理数据
     * @param record
     * @return
     */
    @Override
    public int insertSelective(@RequestBody WaybillRouteWithBLOBs record) {
        return waybillRouteMapper.insertSelective(record);
    }

    /**
     * 修改一条线路管理数据
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(WaybillRouteWithBLOBs record) {
        return waybillRouteMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 线路下拉框查询
     * @param name
     * @return
     */
    @Override
    public List<WaybillRouteDTO> selectDropList(String name, String dataList, Boolean isScope) {
        return waybillRouteMapper.selectDropList(name, dataList, isScope);
    }

    /**
     * 去掉分页功能，全列表返回的查询
     * @return
     */
    @Override
    public List<WaybillRouteDTO> selectWholeList(String dataList, Boolean isScope) {
        return waybillRouteMapper.selectWholeList(dataList, isScope);
    }
}
