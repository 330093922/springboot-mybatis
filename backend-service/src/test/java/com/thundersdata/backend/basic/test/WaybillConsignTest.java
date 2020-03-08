package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dto.WaybillConsignDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillConsign;
import com.thundersdata.backend.basic.service.WaybillAreaService;
import com.thundersdata.backend.basic.service.WaybillConsignService;
import com.thundersdata.backend.basic.vo.*;
import com.thundersdata.backend.common.utils.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Classname WaybillConsignTest
 * @Description TODO
 * @Date 2019/12/13 15:42
 * @Created wrc
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class WaybillConsignTest {

    @Autowired
    private WaybillConsignService waybillConsignServiceService;

    /**
     * 添加
     */
    @Test
    public void testInsertConsign() {
        WaybillConsignInsertVO waybillConsignInsertVO = new WaybillConsignInsertVO();
        waybillConsignInsertVO.setShipperId(34);
        waybillConsignInsertVO.setShipmentId(6);
        waybillConsignInsertVO.setReceivingId(16);
        waybillConsignInsertVO.setExpediterId(30);

        WaybillConsignGoodsInsertVO g1 = new WaybillConsignGoodsInsertVO();
        g1.setGoodsId(29);
        g1.setTotalNo(80);
        WaybillConsignGoodsInsertVO g2 = new WaybillConsignGoodsInsertVO();
        g2.setGoodsId(47);
        g2.setTotalNo(20);
        WaybillConsignGoodsInsertVO g3 = new WaybillConsignGoodsInsertVO();
        g3.setGoodsId(66);
        g3.setTotalNo(800);
        List<WaybillConsignGoodsInsertVO> listGoods = new ArrayList<>();
        listGoods.add(g1);
        listGoods.add(g2);
        listGoods.add(g3);
        waybillConsignInsertVO.setListConsignGoods(listGoods);

        waybillConsignServiceService.insertWaybillConsign(waybillConsignInsertVO);
    }

    /**
     * 修改
     */
    @Test
    public void testUpdateConsign() {
        WaybillConsignUpdateVO waybillConsignUpdateVO = new WaybillConsignUpdateVO();
        waybillConsignUpdateVO.setId(10);
        waybillConsignUpdateVO.setConsignCode("T37030500050920200202001");
        waybillConsignUpdateVO.setShipperId(34);
        waybillConsignUpdateVO.setShipmentId(6);
        waybillConsignUpdateVO.setReceivingId(16);
        waybillConsignUpdateVO.setExpediterId(30);

        WaybillConsignGoodsUpdateVO g1 = new WaybillConsignGoodsUpdateVO();
        g1.setGoodsId(29);
        g1.setTotalNo(80);
        g1.setSurplusNo(40);
        WaybillConsignGoodsUpdateVO g2 = new WaybillConsignGoodsUpdateVO();
        g2.setGoodsId(47);
        g2.setTotalNo(20);
        g2.setSurplusNo(13);
        WaybillConsignGoodsUpdateVO g3 = new WaybillConsignGoodsUpdateVO();
        g3.setGoodsId(66);
        g3.setTotalNo(800);
        g3.setSurplusNo(123);
        List<WaybillConsignGoodsUpdateVO> listGoods = new ArrayList<>();
        listGoods.add(g1);
        listGoods.add(g2);
        listGoods.add(g3);
        waybillConsignUpdateVO.setListConsignGoods(listGoods);

        waybillConsignServiceService.updateWaybillConsign(waybillConsignUpdateVO, new Integer[]{6}, true);
    }


    @Test
    public void testDeleteConsignById() {
        waybillConsignServiceService.deleteWaybillConsign(10, new Integer[]{6}, true);

    }

    @Test
    public void testSelectConsignById() {
        WaybillConsignDTO waybillConsignDTO = waybillConsignServiceService.selectConsignDetail(11);

        System.out.println(waybillConsignDTO);
    }

    @Test
    public void testSelectConsignPage() {
        Pagination<WaybillConsignDTO> pageList = waybillConsignServiceService.selectConsignList("", "", Arrays.asList(0), 1,10, new Integer[]{3},true);

        System.out.println(pageList);
    }


    @Test
    public void testConsignDispatch() {
        WaybillConsignDispatchVO waybillConsignDispatchVO = new WaybillConsignDispatchVO();
        waybillConsignDispatchVO.setConsignId(11);
        waybillConsignDispatchVO.setVehicleId(48507);
        waybillConsignDispatchVO.setTrailerId(48508);
        waybillConsignDispatchVO.setPilotId(267);
        waybillConsignDispatchVO.setSupercargoId(268);
        waybillConsignDispatchVO.setRouteId(3);

        List<WaybillConsignGoodsDispatchVO> listGoods = new ArrayList<>();
        WaybillConsignGoodsDispatchVO waybillConsignGoodsDispatchVO = new WaybillConsignGoodsDispatchVO();
        waybillConsignGoodsDispatchVO.setGoodsId(66);
        waybillConsignGoodsDispatchVO.setDispatchNo(40);
        listGoods.add(waybillConsignGoodsDispatchVO);
        waybillConsignDispatchVO.setListGoods(listGoods);

        waybillConsignServiceService.dispatchConsign(waybillConsignDispatchVO,2,0,new Integer[]{1}, false);


    }






}
