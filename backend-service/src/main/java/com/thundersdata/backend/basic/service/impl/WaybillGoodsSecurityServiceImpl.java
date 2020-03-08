package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.WaybillGoodsSecurityMapper;
import com.thundersdata.backend.basic.model.WaybillGoodsSecurityWithBLOBs;
import com.thundersdata.backend.basic.service.WaybillGoodsSecurityService;
import com.thundersdata.backend.basic.vo.WaybillGoodsSecurityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 危险化学品货物-安全卡信息
 */
@Service
public class WaybillGoodsSecurityServiceImpl implements WaybillGoodsSecurityService {
    @Autowired
    private WaybillGoodsSecurityMapper waybillGoodsSecurityMapper;

    /**
     * 添加危险化学品-安全卡信息
     * @param waybillGoodsSecurityVo
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int insertWaybillGoodsSecurity(WaybillGoodsSecurityVO waybillGoodsSecurityVo) {
        WaybillGoodsSecurityWithBLOBs waybillGoodsSecurity = new WaybillGoodsSecurityWithBLOBs();
        BeanUtils.copyProperties(waybillGoodsSecurityVo, waybillGoodsSecurity);
        int result = waybillGoodsSecurityMapper.insertSelective(waybillGoodsSecurity);

        return result;
    }

    /**
     * 根据货物id删除危险化学品货物安全卡信息
     * @param goodsId
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int deleteWaybillGoodsSecurityByGoodsId(Integer goodsId) {

        return waybillGoodsSecurityMapper.deleteByGoodsId(goodsId);
    }

    /**
     * 根据货物id添加或修改危险化学品货物安全卡信息
     * @param waybillGoodsSecurityVO
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int deleteAndInsertWaybillGoodsSecurityByGoodsId(Integer goodsId, WaybillGoodsSecurityVO waybillGoodsSecurityVO) {
        this.deleteWaybillGoodsSecurityByGoodsId(goodsId);
        waybillGoodsSecurityVO.setGoodsId(goodsId);
        WaybillGoodsSecurityWithBLOBs waybillGoodsSecurity = new WaybillGoodsSecurityWithBLOBs();
        BeanUtils.copyProperties(waybillGoodsSecurityVO, waybillGoodsSecurity);
        int result = waybillGoodsSecurityMapper.insertSelective(waybillGoodsSecurity);

        return result;
    }
}
