package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dto.WaybillConsignDTO;
import com.thundersdata.backend.basic.dto.WaybillConsignGoodsDTO;
import com.thundersdata.backend.basic.model.WaybillConsignGoods;
import com.thundersdata.backend.basic.service.WaybillConsignGoodsService;
import com.thundersdata.backend.basic.service.WaybillConsignService;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsInsertVO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsUpdateVO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsVO;
import com.thundersdata.backend.basic.vo.WaybillConsignInsertVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname WaybillConsignGoodsTest
 * @Description TODO
 * @Date 2019/12/13 15:42
 * @Created wrc
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class WaybillConsignGoodsTest {

    @Autowired
    private WaybillConsignGoodsService waybillConsignGoodsService;

    /**
     * 添加
     */
    @Test
    public void testInsertConsignGoodsBatch() {
        WaybillConsignGoodsInsertVO w1 = new WaybillConsignGoodsInsertVO();
        w1.setGoodsId(12);
        w1.setTotalNo(300);
        WaybillConsignGoodsInsertVO w2 = new WaybillConsignGoodsInsertVO();
        w2.setGoodsId(13);
        w2.setTotalNo(300);
        WaybillConsignGoodsInsertVO w3 = new WaybillConsignGoodsInsertVO();
        w3.setGoodsId(14);
        w3.setTotalNo(300);
        WaybillConsignGoodsInsertVO w4 = new WaybillConsignGoodsInsertVO();
        w4.setGoodsId(16);
        w4.setTotalNo(300);

        ArrayList<WaybillConsignGoodsInsertVO> list = new ArrayList<>();
        list.add(w1);
        list.add(w2);
        list.add(w3);
        list.add(w4);

        waybillConsignGoodsService.insertWaybillConsignGoodsBatch(6, list);
    }

    @Test
    public void testUpdateConsignGoodsBatch() {
        WaybillConsignGoodsUpdateVO w1 = new WaybillConsignGoodsUpdateVO();
        w1.setGoodsId(12);
        w1.setTotalNo(301);
        WaybillConsignGoodsUpdateVO w2 = new WaybillConsignGoodsUpdateVO();
        w2.setGoodsId(13);
        w2.setTotalNo(3002);
        WaybillConsignGoodsUpdateVO w3 = new WaybillConsignGoodsUpdateVO();
        w3.setGoodsId(14);
        w3.setTotalNo(3003);
        WaybillConsignGoodsUpdateVO w4 = new WaybillConsignGoodsUpdateVO();
        w4.setGoodsId(16);
        w4.setTotalNo(3004);

        ArrayList<WaybillConsignGoodsUpdateVO> list = new ArrayList<>();
        list.add(w1);
        list.add(w2);
        list.add(w3);
        list.add(w4);

        waybillConsignGoodsService.updateWaybillConsignGoodsBatch(6,list);
    }


    @Test
    public void testSelectConsignGoodsBatch() {
        List<WaybillConsignGoodsDTO> list = waybillConsignGoodsService.selectConsignGoodsList(6);

        System.out.println(list);
    }






}
