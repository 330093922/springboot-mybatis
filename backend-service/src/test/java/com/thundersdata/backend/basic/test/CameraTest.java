package com.thundersdata.backend.basic.test;


import com.alibaba.fastjson.JSON;
import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.service.AuthorityService;
import com.thundersdata.backend.basic.service.CameraService;
import com.thundersdata.backend.basic.vo.RoleAddMenuVO;
import com.thundersdata.backend.basic.vo.WaybillCameraVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class CameraTest {
    @Autowired
    private CameraService cameraService;
    @Autowired
    private AuthorityService authorityService;

    @Test
    public void getDetails() {
        System.out.println(cameraService.getDetailsByCondition(256));
    }

    @Test
    public void getList() {
        Integer [] array = {1001,1002,1003,1004};
        WaybillCameraVO waybillCameraVO = new WaybillCameraVO();
        waybillCameraVO.setEquipmentNo("ZB");
        waybillCameraVO.setInstallArea("4");
        waybillCameraVO.setName("摄像头");
        System.out.println(cameraService.getListByCondition(waybillCameraVO,array,true));
    }

    @Test
    public void getListAll() {
        Integer [] array = {1001,1002,1003,1004};
        WaybillCameraVO waybillCamera = new WaybillCameraVO();
        waybillCamera.setEquipmentNo("ZB");
        waybillCamera.setInstallArea("5");
        waybillCamera.setName("摄像头");
        System.out.println(cameraService.getListWithoutPagination(waybillCamera,array,true));

    }

    @Test
    public void dropList() {
        System.out.println(cameraService.dropList("摄像头"));
    }

    @Test
    public void getMenuTree(){
        List<AuthResourceDTO>list = authorityService.getRoleMenu(1);
        for (AuthResourceDTO a:list) {
            System.out.println(JSON.toJSONString(a));
        }

    }
}
