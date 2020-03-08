package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.WaybillAreaMapper;
import com.thundersdata.backend.basic.dto.WaybillAreaDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.service.WaybillAreaService;
import com.thundersdata.backend.basic.vo.WaybillAreaVo;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname WaybillAreaServiceImpl
 * @Description TODO
 * @Date 2019/12/13 11:18
 * @Created wrc
 */
@Service
public class WaybillAreaServiceImpl implements WaybillAreaService {

    @Autowired
    private WaybillAreaMapper waybillAreaMapper;


    /**
     * 装卸区域列表
     *
     * @param waybillAreaVo
     * @return
     */
    @Override
    public Pagination<WaybillArea> selectWaybillOwnerLike(WaybillAreaVo waybillAreaVo) {
        // 校验分页参数，赋默认值
        PageParam pageparam = PageUtils.init(waybillAreaVo.getPage(),waybillAreaVo.getPageSize());
        int total = waybillAreaMapper.getCountByCondition(waybillAreaVo);
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }
        waybillAreaVo.setPageSize(pageparam.getLimit());
        waybillAreaVo.setPage(pageparam.getOffset());
        List<WaybillArea> waybillAreaList = waybillAreaMapper.selectWaybillOwnerLike(waybillAreaVo);
        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, waybillAreaList);
    }

    /**
     * 查询装卸区详情
     *
     * @param id
     * @return
     */
    @Override
    public WaybillArea selectByPrimaryKey(Integer id) {
        return waybillAreaMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加装卸区域
     *
     * @param waybillArea
     * @return
     */
    @Override
    public int insertSelective(WaybillArea waybillArea) {
        return waybillAreaMapper.insertSelective(waybillArea);
    }

    /**
     * 删除装卸区域
     *
     * @param idList
     * @return
     */
    @Override
    public int deleteId(List<WaybillArea> idList,
                        Boolean isScope,
                        Integer[] ownerIds) {
        return waybillAreaMapper.deleteId(idList,isScope,ownerIds);
    }

    /**
     * 装卸区域信息修改
     * @param record
     * @param isScope
     * @param ownerIds
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(WaybillArea record,
                                           Boolean isScope,
                                           Integer[] ownerIds) {
        return waybillAreaMapper.updateByPrimaryKeySelective(record,isScope,ownerIds);
    }

    /**
     * 装卸区域下拉框
     *
     * @param areaNo
     * @return
     */
    @Override
    public  List<WaybillAreaDTO> dropDown(String areaNo) {
        return waybillAreaMapper.dropDown(areaNo);
    }

    /**
     * 数据范围判断装卸区域下拉框
     *
     * @param areaNo
     * @param isScope
     * @param ownerIds
     * @return
     */
    @Override
    public List<WaybillAreaDTO> dataDropDown(String areaNo, Boolean isScope, Integer[] ownerIds) {
        return waybillAreaMapper.dataDropDown(areaNo,isScope,ownerIds);
    }
}
