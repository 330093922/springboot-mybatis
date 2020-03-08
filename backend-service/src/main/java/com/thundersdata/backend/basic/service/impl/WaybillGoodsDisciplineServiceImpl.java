package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.WaybillGoodsDisciplineMapper;
import com.thundersdata.backend.basic.model.WaybillGoodsDiscipline;
import com.thundersdata.backend.basic.service.WaybillGoodsDisciplineService;
import com.thundersdata.backend.basic.vo.WaybillGoodsDisciplineVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 危险化学品货物-操作规程信息
 */
@Service
public class WaybillGoodsDisciplineServiceImpl implements WaybillGoodsDisciplineService {
    @Autowired
    private WaybillGoodsDisciplineMapper waybillGoodsDisciplineMapper;

    /**
     * 新增危险化学品操作规程信息
     * @param waybillGoodsDisciplineVo
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int insertWaybillGoodsDiscipline(WaybillGoodsDisciplineVO waybillGoodsDisciplineVo) {
        WaybillGoodsDiscipline waybillGoodsDiscipline = new WaybillGoodsDiscipline();
        BeanUtils.copyProperties(waybillGoodsDisciplineVo, waybillGoodsDiscipline, "id");
        int i = waybillGoodsDisciplineMapper.insertSelective(waybillGoodsDiscipline);

        return i;
    }

    /**
     * 根据货物id删除危险化学品货物操作规程信息
     * @param goodsId
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int deleteWaybillGoodsDisciplineByGoodsId(Integer goodsId) {

        return waybillGoodsDisciplineMapper.deleteByGoodsId(goodsId);
    }

    /**
     * 删除并插入危险化学品货物信息的操作规程信息
     * @param goodsId
     * @param listWaybillGoodsDisciplineVO
     * @return
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public int deleteAndInsertWaybillGoodsDisciplineByGoodsId(Integer goodsId, List<WaybillGoodsDisciplineVO> listWaybillGoodsDisciplineVO) {
        this.deleteWaybillGoodsDisciplineByGoodsId(goodsId);
        if(null == listWaybillGoodsDisciplineVO || listWaybillGoodsDisciplineVO.size() == 0){
            return 0;
        }
        List<WaybillGoodsDiscipline> list = new ArrayList<>();
        for(int i = 0; i< listWaybillGoodsDisciplineVO.size(); i++){
            WaybillGoodsDisciplineVO waybillGoodsDisciplineVO = listWaybillGoodsDisciplineVO.get(i);
            waybillGoodsDisciplineVO.setGoodsId(goodsId);
            WaybillGoodsDiscipline waybillGoodsDiscipline = new WaybillGoodsDiscipline();
            BeanUtils.copyProperties(waybillGoodsDisciplineVO, waybillGoodsDiscipline);
            list.add(waybillGoodsDiscipline);
        }


        return waybillGoodsDisciplineMapper.insertWaybillGoodsDisciplineBatch(list);
    }

}
