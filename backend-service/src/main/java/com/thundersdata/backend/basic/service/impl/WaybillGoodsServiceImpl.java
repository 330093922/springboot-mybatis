package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.WaybillGoodsDetectionMapper;
import com.thundersdata.backend.basic.dao.WaybillGoodsDisciplineMapper;
import com.thundersdata.backend.basic.dao.WaybillGoodsMapper;
import com.thundersdata.backend.basic.dao.WaybillGoodsSecurityMapper;
import com.thundersdata.backend.basic.dto.*;
import com.thundersdata.backend.basic.model.WaybillGoods;
import com.thundersdata.backend.basic.model.WaybillGoodsDetection;
import com.thundersdata.backend.basic.model.WaybillGoodsDiscipline;
import com.thundersdata.backend.basic.model.WaybillGoodsSecurity;
import com.thundersdata.backend.basic.service.WaybillGoodsDetectionService;
import com.thundersdata.backend.basic.service.WaybillGoodsDisciplineService;
import com.thundersdata.backend.basic.service.WaybillGoodsSecurityService;
import com.thundersdata.backend.basic.service.WaybillGoodsService;
import com.thundersdata.backend.basic.utils.OrderUtil;
import com.thundersdata.backend.basic.vo.WaybillGoodsDetailVO;
import com.thundersdata.backend.basic.vo.WaybillGoodsVO;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 危险化学品货物信息
 */
@Service
public class WaybillGoodsServiceImpl implements WaybillGoodsService {
    @Autowired
    private WaybillGoodsMapper waybillGoodsMapper;
    @Autowired
    private WaybillGoodsDetectionMapper waybillGoodsDetectionMapper;
    @Autowired
    private WaybillGoodsDisciplineMapper waybillGoodsDisciplineMapper;
    @Autowired
    private WaybillGoodsSecurityMapper waybillGoodsSecurityMapper;
    @Autowired
    private WaybillGoodsDetectionService waybillGoodsDetectionService;
    @Autowired
    private WaybillGoodsDisciplineService waybillGoodsDisciplineService;
    @Autowired
    private WaybillGoodsSecurityService waybillGoodsSecurityService;

    /**
     * 新增危险化学品货物信息
     * @param waybillGoodsVo
     * @return
     */
    @Override
    public int insertWaybillGoods(WaybillGoodsVO waybillGoodsVo) {
        Assert.notBlank(waybillGoodsVo.getUnNo(), "联合国货物编号不能为空");
        Assert.notBlank(waybillGoodsVo.getName(), "货物名称不能为空");
        WaybillGoods waybillGoods = new WaybillGoods();
        BeanUtils.copyProperties(waybillGoodsVo, waybillGoods);

        return waybillGoodsMapper.insertSelective(waybillGoods);
    }

    /**
     * 修改危险化学品货物信息
     * @param waybillGoodsVo
     * @return
     */
    @Override
    public int updateWaybillGoods(WaybillGoodsVO waybillGoodsVo) {
        Assert.notBlank(waybillGoodsVo.getUnNo(), "联合货物编号不能为空");
        Assert.notBlank(waybillGoodsVo.getName(), "货物名称不能为空");
        WaybillGoods waybillGoods = new WaybillGoods();
        BeanUtils.copyProperties(waybillGoodsVo, waybillGoods);
        waybillGoods.setUpdatedAt(new Date());

        return waybillGoodsMapper.updateByPrimaryKeySelective(waybillGoods);
    }

    /**
     * 根据主键查询危险化学品货物信息
     * @param id
     * @return
     */
    @Override
    public WaybillGoodsDTO selectWaybillGoods(Integer id) {
        WaybillGoods waybillGoods = waybillGoodsMapper.selectByPrimaryKey(id);
        WaybillGoodsDTO waybillGoodsDTO = new WaybillGoodsDTO();
        BeanUtils.copyProperties(waybillGoods, waybillGoodsDTO);
        return waybillGoodsDTO;
    }

    @Override
    public WaybillGoodsDetailDTO selectWaybillGoodsDetail(Integer id) {
        WaybillGoods waybillGoods = waybillGoodsMapper.selectByPrimaryKey(id);
        Assert.notNull(waybillGoods, "危险化学品货物编号不存在");
        WaybillGoodsDetailDTO result  = new WaybillGoodsDetailDTO();
        result.setId(id);
        List<WaybillGoodsDetectionDTO> listDetection = waybillGoodsDetectionMapper.selectByGoodsId(id);
        List<WaybillGoodsDisciplineDTO> listDiscipline = waybillGoodsDisciplineMapper.selectByGoodsId(id);
        WaybillGoodsSecurityDTO security = waybillGoodsSecurityMapper.selectByGoodsId(id);
        result.setListDetection(listDetection);
        result.setListDiscipline(listDiscipline);
        result.setSecurity(security);

        return result;
    }

    /**
     * 根据主键删除危险化学品货物信息
     * @param id
     * @return
     */
    @Override
    public int updateWaybillGoodsDeleteed(Integer id) {
        WaybillGoods waybillGoods = waybillGoodsMapper.selectByPrimaryKey(id);
        Assert.notNull(waybillGoods, "危险化学品货物信息不存在");
        Assert.isFalse(waybillGoods.getIsDeleted() == 1, "危险化学品货物信息已经是删除状态");
        waybillGoods.setIsDeleted((byte) 1);
        int result = waybillGoodsMapper.updateByPrimaryKeySelective(waybillGoods);
        Assert.isTrue(result > 0, "危险化学品货物信息删除失败");

        return result;
    }

    @Override
    public int updateWaybillGoodsDeleteedBatch(Integer[] ids) {
        int i = waybillGoodsMapper.updateWaybillGoodsDeleteedBatch(ids);
        Assert.isTrue(i > 0, "危险化学品货物信息删除失败");

        return i;
    }


    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int updateWaybillGoodsDetail(WaybillGoodsDetailVO waybillGoodsDetailVo) {
        waybillGoodsDetectionService.deleteAndInsertWaybillGoodsDetectionByGoodsId(waybillGoodsDetailVo.getId(),waybillGoodsDetailVo.getListDetection());
        waybillGoodsDisciplineService.deleteAndInsertWaybillGoodsDisciplineByGoodsId(waybillGoodsDetailVo.getId(),waybillGoodsDetailVo.getListDiscipline());
        waybillGoodsSecurityService.deleteAndInsertWaybillGoodsSecurityByGoodsId(waybillGoodsDetailVo.getId(),waybillGoodsDetailVo.getSecurity());

        return 1;
    }

    @Override
    public Pagination<WaybillGoodsDTO> selectWaybillGoods(Integer page, Integer pageSize, String unNo, String name, String alias) {
        PageParam pageparam = PageUtils.init(page, pageSize);
        int total = waybillGoodsMapper.countWaybillGoods(OrderUtil.getLikeStr(unNo), OrderUtil.getLikeStr(name), OrderUtil.getLikeStr(alias));
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }
        List<WaybillGoodsDTO> list = waybillGoodsMapper.selectWaybillGoods(pageparam, OrderUtil.getLikeStr(unNo), OrderUtil.getLikeStr(name), OrderUtil.getLikeStr(alias));

        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, list);
    }

    @Override
    public List<DictDTO> selectWaybillGoodsCombo(String value) {
        value = value==null?"":value;
        return waybillGoodsMapper.selectWaybillGoodsCombo(OrderUtil.getLikeStr(value));
    }

}
