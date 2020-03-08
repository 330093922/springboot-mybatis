package com.thundersdata.backend.basic.test;


import com.alibaba.fastjson.JSON;
import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.VehicleTrackDTO;
import com.thundersdata.backend.basic.model.WaybillVehicle;
import com.thundersdata.backend.basic.service.AuthorityService;
import com.thundersdata.backend.basic.service.CameraService;
import com.thundersdata.backend.basic.service.VehicleService;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.VehicleTrackVO;
import com.thundersdata.backend.basic.vo.WaybillCameraVO;
import com.thundersdata.backend.basic.vo.WaybillVehicleVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class VehicleTest {
    @Autowired
    private VehicleService vehicleService;

    @Test
    public void getDetails() {
        System.out.println(vehicleService.getDetailsByCondition("56612,53833"));
    }

    @Test
    public void getList() {
        Integer [] array = {1117,1293,1043,1072};
        WaybillVehicleVO waybillVehicleVO = new WaybillVehicleVO();

        waybillVehicleVO.setVehicleCode("57挂");

        System.out.println(vehicleService.getListByCondition(waybillVehicleVO,1,10,array,true));
    }

    @Test
    public void dropListWithScope() {
        Integer [] array = {1117,1293,1043,1072};

        System.out.println(vehicleService.dropListWithScope("57挂",array,true));

    }

    @Test
    public void dropList() {
        System.out.println(vehicleService.dropList("鲁"));
    }

    @Test
    public void update(){
        Integer [] array = {1117,1293,1043,1072};
        WaybillVehicle waybillVehicle = new WaybillVehicle();
        waybillVehicle.setId("16394");
        waybillVehicle.setColor(011);
        vehicleService.updateVehicleWithScope(waybillVehicle,array,false);
    }
    @Test
    public void del(){
        Integer [] array = {1117,1293,1043,1072};

        List<WaybillVehicleVO> list = new ArrayList<>();
        WaybillVehicleVO VehicleVO1 = new WaybillVehicleVO();
        VehicleVO1.setId(16398);
        WaybillVehicleVO VehicleVO2 = new WaybillVehicleVO();
        VehicleVO2.setId(16399);
        list.add(VehicleVO1);
        list.add(VehicleVO2);
        vehicleService.deleteVehicle(list,array,true);
    }

    //查询过期道路运输证号车辆
    @Test
    public void ExpiredVehicle() {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO().setProvince("1").setCity("22").setCounty("33");
        System.out.println( vehicleService.ExpiredVehicle(userExpiredDTO));
    }

    /**
     * 根据时间段过期道路运输证号车辆
     *
     */
    @Test
    public void  HistoryExpiredUser() {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO().setProvince("1").
                setCity("22").
                setCounty("33").setStartDate("2019-1-20").setEndDate("2020-1-22");
        System.out.println(vehicleService.HistoryExpiredVehicle(userExpiredDTO));

    }


    /**
     * 车辆行驶轨迹查询
     *
     * @return
     */
    @Test
    public void VehicleTrack() {
        VehicleTrackDTO vehicleTrackDTO = new VehicleTrackDTO().setVehicleId("56612").
                setStartDate("2018-11-30 16:29:10").
                setEndDate("2018-11-30 16:29:20");
        System.out.println(vehicleService.VehicleTrack(vehicleTrackDTO)); ;
    }

}
