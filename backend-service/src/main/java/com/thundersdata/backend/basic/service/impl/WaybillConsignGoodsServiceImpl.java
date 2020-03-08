package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.WaybillConsignGoodsMapper;
import com.thundersdata.backend.basic.dto.WaybillConsignGoodsDTO;
import com.thundersdata.backend.basic.model.WaybillConsignGoods;
import com.thundersdata.backend.basic.service.WaybillConsignGoodsService;
import com.thundersdata.backend.basic.service.WaybillOwnerService;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsInsertVO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsUpdateVO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname WaybillConsignGoodsServiceImpl
 * @Description 托运单或无关联service实现类
 * @Date 2020-2-1
 * @Created wanghaibo
 */
@Service
public class WaybillConsignGoodsServiceImpl implements WaybillConsignGoodsService {

    @Autowired
    private WaybillConsignGoodsMapper waybillConsignGoodsMapper;

    @Autowired
    private WaybillOwnerService waybillOwnerService;

    /**
     * 插入一条货运单物品关联信息
     *
     * @param waybillConsignGoodsInsertVO
     * @return
     */
    @Override
    public int insertWaybillConsignGoods(WaybillConsignGoodsInsertVO waybillConsignGoodsInsertVO) {
        WaybillConsignGoods waybillConsignGoods = new WaybillConsignGoods();
        BeanUtils.copyProperties(waybillConsignGoodsInsertVO, waybillConsignGoods);
        waybillConsignGoods.setSurplusNo(waybillConsignGoodsInsertVO.getTotalNo());

        return waybillConsignGoodsMapper.insertSelective(waybillConsignGoods);
    }

    /**
     * 批量插入托运单货物关联信息
     *
     * @param list
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int insertWaybillConsignGoodsBatch(Integer consignId, List<WaybillConsignGoodsInsertVO> list) {
        Assert.isTrue(list != null && list.size() > 0 , "货物列表不能为空");
        ArrayList<WaybillConsignGoodsVO> listInsert = new ArrayList<>();
        for (WaybillConsignGoodsInsertVO waybillConsignGoodsInsertVO : list) {
            WaybillConsignGoodsVO waybillConsignGoodsVO = new WaybillConsignGoodsVO();
            BeanUtils.copyProperties(waybillConsignGoodsInsertVO, waybillConsignGoodsVO);
            waybillConsignGoodsVO.setConsignId(consignId);
            waybillConsignGoodsVO.setSurplusNo(waybillConsignGoodsInsertVO.getTotalNo());
            listInsert.add(waybillConsignGoodsVO);
        }
        return waybillConsignGoodsMapper.insertBatch(listInsert);
    }

    /**
     * 批量更新托运单货物关联信息
     *
     * @param list
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateWaybillConsignGoodsBatch(Integer consignId, List<WaybillConsignGoodsUpdateVO> list) {
        Assert.isTrue(list != null && list.size() > 0 , "货物列表不能为空");
        waybillConsignGoodsMapper.deleteByConsignId(consignId);
        ArrayList<WaybillConsignGoodsVO> listUpdate = new ArrayList<>();
        for (WaybillConsignGoodsUpdateVO waybillConsignGoodsUpdateVO : list) {
            WaybillConsignGoodsVO waybillConsignGoodsVO = new WaybillConsignGoodsVO();
            BeanUtils.copyProperties(waybillConsignGoodsUpdateVO, waybillConsignGoodsVO);
            waybillConsignGoodsVO.setConsignId(consignId);
            listUpdate.add(waybillConsignGoodsVO);
        }
        return waybillConsignGoodsMapper.insertBatch(listUpdate);
    }

    /**
     * 查询托运单关联的所有货物信息
     *
     * @param consignId
     * @return
     */
    @Override
    public List<WaybillConsignGoodsDTO> selectConsignGoodsList(Integer consignId) {


        return waybillConsignGoodsMapper.selectByConsignId(consignId);
    }


}
