package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.model.WaybillDetection;
import com.thundersdata.backend.basic.model.WaybillDetectionWithBLOBs;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.service.WaybillDetectionService;
import com.thundersdata.backend.basic.service.WaybillUserService;
import com.thundersdata.backend.basic.vo.DetectionListVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname WaybillOwnerTest
 * @Description TODO
 * @Date 2019/12/17 14:24
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class WaybillDetectionTest {


    @Autowired
    private WaybillDetectionService waybillDetectionService;
    @Autowired
    private WaybillUserService waybillUserService;

    //新增接口测试
    @Test
    public void insert() {
        WaybillDetectionWithBLOBs waybillDetectionWithBLOBs = new WaybillDetectionWithBLOBs();
        waybillDetectionWithBLOBs.setContent("测试检查内容");
        waybillDetectionWithBLOBs.setProject("测试检查项目");
        waybillDetectionWithBLOBs.setTime("2019-12-15");
        waybillDetectionWithBLOBs.setRemark("测试备注");
        waybillDetectionWithBLOBs.setType("车辆检查");
        int result =waybillDetectionService.insertDetection(waybillDetectionWithBLOBs);
        System.out.println(result);
    }

    //修改接口测试
    @Test
    public void update() {
        WaybillDetectionWithBLOBs waybillDetectionWithBLOBs = new WaybillDetectionWithBLOBs();
        waybillDetectionWithBLOBs.setContent("测试检查内容2222");
        //waybillDetectionWithBLOBs.setProject("测试检查项目333");
        waybillDetectionWithBLOBs.setTime("2019-12-16");
        waybillDetectionWithBLOBs.setType("车辆卸货检查");
        waybillDetectionWithBLOBs.setId(13);
        int result =waybillDetectionService.updateDetection(waybillDetectionWithBLOBs);
        System.out.println(result);
    }


    //删除接口测试
    @Test
    public void  delete() {
        List<WaybillDetection> list = new ArrayList<WaybillDetection>();
        for(int i=18;i<22;i++){
            WaybillDetection waybillDetection = new WaybillDetection();
            waybillDetection.setId(i);
            list.add(waybillDetection);
        }

        int result =waybillDetectionService.deleteDetection(list);
    }
    //查询接口测试
    @Test
    public void  select(){
        DetectionListVo detectionListVo = new DetectionListVo();
        /*detectionListVo.setType("装车");
        detectionListVo.setContent("关闭");
        detectionListVo.setProject("车辆");*/
        detectionListVo.setPage(1);
        detectionListVo.setPageSize(5);
        System.out.println(waybillDetectionService.selectDetection(detectionListVo.getType(),detectionListVo.getProject(),detectionListVo.getProject(),detectionListVo.getPage(), detectionListVo.getPageSize()).toString());

    }
    //查询下拉列表框
    @Test
    public void  dropList(){
        DetectionListVo detectionListVo = new DetectionListVo();
        detectionListVo.setType("装车");
        detectionListVo.setContent("关闭");
        detectionListVo.setProject("车辆");
        System.out.println(waybillDetectionService.dropList(detectionListVo.getType(),detectionListVo.getProject(),detectionListVo.getProject()).toString());

    }
    //测试详细接口
    @Test
    public void  detail() {
        System.out.println(waybillDetectionService.getDetail(12).toString());
    }
    //人员接口批量删除
    @Test
    public void  deleteUser() {
        List<WaybillUser> list = new ArrayList<WaybillUser>();
        for(int i=2351;i<2352;i++){
            WaybillUser waybillDetection = new WaybillUser();
            waybillDetection.setId(i);
            list.add(waybillDetection);
        }

        int result =waybillUserService.deleteWaybillUser(list,null,false);
    }
}
