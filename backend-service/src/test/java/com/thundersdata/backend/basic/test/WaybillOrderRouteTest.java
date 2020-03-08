package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.model.WaybillOrderRoute;
import com.thundersdata.backend.basic.service.WaybillOrderRouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname WaybillOrderRouteTets
 * @Description TODO
 * @Date 2019/12/17 17:15
 * @Created wrc
 */
@SpringBootTest(classes = ServiceApplication.class)
@RunWith(SpringRunner.class)
public class WaybillOrderRouteTest {

    @Autowired
    private WaybillOrderRouteService waybillOrderRouteService;

    //新增运单线路
    @Test
    public void insertSelective( ) {
        List<WaybillOrderRoute> waybillOrderRoutes = new ArrayList<WaybillOrderRoute>();
        WaybillOrderRoute waybillOrderRoute = new WaybillOrderRoute();
        waybillOrderRoute.setOrderId(555).setRouteId(555);
        waybillOrderRoutes.add(waybillOrderRoute);

        WaybillOrderRoute waybillOrderRoute1 = new WaybillOrderRoute();
        waybillOrderRoute1.setOrderId(666).setRouteId(666);
        waybillOrderRoutes.add(waybillOrderRoute1);

        System.out.println(waybillOrderRouteService.batchInsert(waybillOrderRoutes));
    }
}
