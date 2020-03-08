package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.VehicleTrackDTO;
import com.thundersdata.backend.basic.dto.WaybillOrderGoodsDTO;
import com.thundersdata.backend.basic.dto.waybillSupplementDTO;
import com.thundersdata.backend.basic.model.WaybillOrder;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.service.WaybillOrderService;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.VehicleTrackVO;
import com.thundersdata.backend.basic.vo.selectOrderDetaillonlatVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname WaybillOrderTest
 * @Description TODO
 * @Date 2020/1/20 9:00
 * @Created wrc
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaybillOrderTest {

    @Autowired
    private WaybillOrderService waybillOrderService;



    //运单补录
    @Test
    public void waybillSupplement() {
        waybillSupplementDTO waybillOrder = new waybillSupplementDTO();
        waybillOrder.setQrCode("555555555555555555555");
        waybillOrder.setShipperId(1);
        waybillOrder.setReceivingId(2);
        waybillOrder.setTrailerId(1);
        waybillOrder.setOrderCode("370301000021");
        List<WaybillOrderGoodsDTO> goodsList = new ArrayList<WaybillOrderGoodsDTO>();
        WaybillOrderGoodsDTO orderGoodsDTO =new WaybillOrderGoodsDTO();
        WaybillOrderGoodsDTO orderGoodsDTO1 =new WaybillOrderGoodsDTO();

        orderGoodsDTO.setGoodsName("1121");
        orderGoodsDTO.setGoodsType("11");
        orderGoodsDTO.setGoodsVolume(1);
        orderGoodsDTO.setGoodsSize("1");
        orderGoodsDTO1.setGoodsName("1121");
        orderGoodsDTO1.setGoodsType("11");
        orderGoodsDTO1.setGoodsVolume(1);
        orderGoodsDTO1.setGoodsSize("1");
        goodsList.add(orderGoodsDTO);
        goodsList.add(orderGoodsDTO1);
        waybillOrder.setGoodsList(goodsList);
       waybillOrderService.waybillSupplement(waybillOrder);
    }


    //运单补录
    @Test
    public void waybillSupplement1() {
        waybillSupplementDTO waybillOrder = new waybillSupplementDTO();
        waybillOrder.setExpediterId(34);
        waybillOrder.setAttention("1");
        waybillOrder.setMeasures("2");
        waybillOrder.setReceivingId(30);
        waybillOrder.setRemark("111");
        waybillOrder.setShipperId(41);
        List<WaybillOrderGoodsDTO> goodsList = new ArrayList<WaybillOrderGoodsDTO>();
        WaybillOrderGoodsDTO orderGoodsDTO =new WaybillOrderGoodsDTO();
        orderGoodsDTO.setGoodsCode("1075");
        orderGoodsDTO.setGoodsName("液化石油气");
        orderGoodsDTO.setGoodsType("2");
        orderGoodsDTO.setGoodsSize("液化石油气");
        goodsList.add(orderGoodsDTO);
        waybillOrder.setGoodsList(goodsList);
        waybillOrderService.waybillSupplement(waybillOrder);
    }








    /**
     * 电子运单超时预警
     *
     * @return
     */
    @Test
    public void ExpiredOrder() {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO(). setProvince("370000").
                setCity("370300").
                setCounty("370303").setStartDate("2020-01-20 17:03:44").setEndDate("2020-01-25 20:03:44");
        System.out.println(waybillOrderService.ExpiredOrder(userExpiredDTO));
    }


    /**
     * 根据时间段查询电子运单超时预警
     *
     * @param
     * @return
     */
    @Test
    public void HistoryExpiredOrder() {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO(). setProvince("1").
                setCity("22").
                setCounty("33").setStartDate("2020-01-20 17:03:44").setEndDate("2020-01-20 20:03:44");
        System.out.println(waybillOrderService.HistoryExpiredOrder(userExpiredDTO));
    }



    /**
     * 根据运单id 查询货车轨迹
     *
     * @return
     */
    @Test
    public void WaybillTrack() {
        System.out.println(waybillOrderService.WaybillTrack("16563"));
    }



    /**
     * 查询订单详情和经纬度
     *
     */
    @Test
    public void selectOrderDetaillonlat() {
        String ordercode = "37030500006820200121000,37030500004920200121000";
        String[] strs = ordercode.split(",");
        System.out.println(   waybillOrderService.selectOrderDetaillonlat(ordercode));
    }

}
