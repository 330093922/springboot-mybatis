package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.WaybillGoodsDetectionMapper;
import com.thundersdata.backend.basic.model.WaybillGoodsDetection;
import com.thundersdata.backend.basic.service.WaybillGoodsDetectionService;
import com.thundersdata.backend.basic.vo.WaybillGoodsDetectionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 危险化学品货物-检查项信息
 */
@Service
public class WaybillGoodsDetectionServiceImpl implements WaybillGoodsDetectionService {
    @Autowired
    private WaybillGoodsDetectionMapper waybillGoodsDetectionMapper;

    /**
     * 新增危险化学品检查项信息
     * @param waybillGoodsDetectionVo
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int insertWaybillGoodsDetection(WaybillGoodsDetectionVO waybillGoodsDetectionVo) {
        WaybillGoodsDetection waybillGoodsDetection = new WaybillGoodsDetection();
        BeanUtils.copyProperties(waybillGoodsDetectionVo, waybillGoodsDetection, "id");
        int i = waybillGoodsDetectionMapper.insertSelective(waybillGoodsDetection);

        return i;
    }

    /**
     * 根据货物id删除危险化学品货物信息
     * @param goodsId
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int deleteWaybillGoodsDetectionByGoodsId(Integer goodsId) {

        return waybillGoodsDetectionMapper.deleteByGoodsId(goodsId);
    }

    /**
     * 修改后插入危险化学品货物相关的检查项信息
     * @param goodsId
     * @param listWaybillGoodsDetectionVO
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int deleteAndInsertWaybillGoodsDetectionByGoodsId(Integer goodsId, List<WaybillGoodsDetectionVO> listWaybillGoodsDetectionVO) {
        this.deleteWaybillGoodsDetectionByGoodsId(goodsId);

        if(null == listWaybillGoodsDetectionVO || listWaybillGoodsDetectionVO.size() == 0){
            return 0;
        }
        List<WaybillGoodsDetection> list = new ArrayList<>();
        for(int i = 0; i< listWaybillGoodsDetectionVO.size(); i++){
            WaybillGoodsDetectionVO waybillGoodsDetectionVO = listWaybillGoodsDetectionVO.get(i);
            waybillGoodsDetectionVO.setGoodsId(goodsId);
            WaybillGoodsDetection waybillGoodsDetection = new WaybillGoodsDetection();
            BeanUtils.copyProperties(waybillGoodsDetectionVO, waybillGoodsDetection);
            list.add(waybillGoodsDetection);
        }

        return waybillGoodsDetectionMapper.insertWaybillGoodsDetectionBatch(list);
    }

}
