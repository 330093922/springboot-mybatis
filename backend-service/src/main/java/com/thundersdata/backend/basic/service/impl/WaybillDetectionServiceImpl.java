package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.WaybillDetectionMapper;
import com.thundersdata.backend.basic.dto.WaybillDetectionDTO;
import com.thundersdata.backend.basic.dto.WaybillDetectionDictDTO;
import com.thundersdata.backend.basic.model.WaybillDetection;
import com.thundersdata.backend.basic.model.WaybillDetectionWithBLOBs;
import com.thundersdata.backend.basic.service.WaybillDetectionService;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自检内容管理
 */
@Service
public class WaybillDetectionServiceImpl implements WaybillDetectionService {
    @Autowired
    private WaybillDetectionMapper waybillDetectionMapper;

    /**
     * 查询自检内容管理列表
     * @param type 检查类型
     * @param project 检查项目
     * @param content 检查内容
     * @param page 页数
     * @param pageSize 数量
     * @return
     */
    @Override
    public Pagination<WaybillDetectionDTO> selectDetection(String type, String project, String content, Integer page, Integer pageSize) {
        // 校验分页参数，赋默认值
        PageParam pageparam = PageUtils.init(page, pageSize);
        int total=waybillDetectionMapper.getCountByDetection(type,project,content);
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }
        List<WaybillDetectionDTO> waybillDetectionDTOList =waybillDetectionMapper.selectAllDetection(type,project,content,pageparam.getLimit(), pageparam.getOffset());
        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, waybillDetectionDTOList);
    }

    /**
     * 新增自检内容管理
     * @param waybillDetectionWithBLOBs
     * @return
     */
    @Override
    public int insertDetection(WaybillDetectionWithBLOBs waybillDetectionWithBLOBs) {
        isNullWaybillDetection(waybillDetectionWithBLOBs);
        return waybillDetectionMapper.insertSelective(waybillDetectionWithBLOBs);
    }

    /**
     * 修改自检内容管理
     * @param waybillDetectionWithBLOBs
     * @return
     */
    @Override
    public int updateDetection(WaybillDetectionWithBLOBs waybillDetectionWithBLOBs) {
        isNullWaybillDetection(waybillDetectionWithBLOBs);
        return waybillDetectionMapper.updateByPrimaryKeySelective(waybillDetectionWithBLOBs);
    }

    /**
     * 删除自检内容管理
     * @param waybillDetectionList 自检内容id集合
     * @return
     */
    @Override
    public int deleteDetection(List<WaybillDetection> waybillDetectionList) {
        return waybillDetectionMapper.deleteDetection(waybillDetectionList);
    }

    /**
     * 自检内容管理下拉列表框
     * @param type 检查类型
     * @param project 检查项目
     * @param content 检查内容
     * @return
     */
    @Override
    public List<WaybillDetectionDictDTO> dropList(String type, String project, String content) {
        return waybillDetectionMapper.dropList(type,project,content);
    }
    /**
     * 根据id查询自检内容管理详细信息
     * @param id
     * @return
     */
    @Override
    public WaybillDetectionDTO getDetail(Integer id) {
        return waybillDetectionMapper.getDetail(id);
    }

    /**
     * 判断必填内容不能为null
     * @param waybillDetectionWithBLOBs
     * @return
     */
    private String isNullWaybillDetection(WaybillDetectionWithBLOBs waybillDetectionWithBLOBs){
        return  Assert.notNull(waybillDetectionWithBLOBs.getContent(), "检查内容不能为空！")+
        Assert.notNull(waybillDetectionWithBLOBs.getTime(), "检查时间不能为空！")+
        Assert.notNull(waybillDetectionWithBLOBs.getType(), "检查类型不能为空！")+
        Assert.notNull(waybillDetectionWithBLOBs.getProject(), "检查项不能为空！");
    }
}
