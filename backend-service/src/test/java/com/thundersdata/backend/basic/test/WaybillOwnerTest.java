package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dao.WaybillOwnerMapper;
import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDetailsDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.service.WaybillOrderService;
import com.thundersdata.backend.basic.service.WaybillOwnerService;
import com.thundersdata.backend.basic.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname WaybillOwnerTest
 * @Description TODO
 * @Date 2019/12/7 11:24
 * @Created wrc
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class WaybillOwnerTest {


    @Autowired
    private WaybillOwnerMapper waybillOwnerMapper;
    @Autowired
    private WaybillOwnerService waybillOwnerService;
    @Autowired
    private WaybillOrderService waybillOrderService;

    //下拉框测试
    @Test
    public void dropdown() {
//        List<WaybillOwnerDTO> dictDTOS = waybillOwnerMapper.dropdown("生产");
//        System.out.println(dictDTOS.toString());
        LocalDate LocalDate = java.time.LocalDate.now();
        System.out.println(LocalDate);

    }

    //企业列表测试
    @Test
    public void selectWaybillOwnerLike() {
        EnterpriseLlistVo enterpriseLlistVO = new EnterpriseLlistVo();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        enterpriseLlistVO.setOwnerName("淄博");
        enterpriseLlistVO.setOwnerTypes(list);
//        EnterpriseLlistVo enterpriseLlistVO = new EnterpriseLlistVo("","",
//                "9137",0,10,new Integer[]{4,5,6},false);
        System.out.println( waybillOwnerService.selectWaybillOwnerLike(enterpriseLlistVO));
    }


    //派发测试
    @Test
    public void  distriBute() {
        DistriButeVo distriButeVO = new DistriButeVo();
        distriButeVO.setId(2);
        distriButeVO.setPilotId(4);
        distriButeVO.setSupercargoId(5);
        distriButeVO.setVehicleId(4);
        distriButeVO.setTrailerId(5);
        Integer[] ownerIds = {1,2,3};
        System.out.println(waybillOrderService.distriBute(distriButeVO,ownerIds,false,2));
    }


    //审批测试
    @Test
    public void  exaMine(){
        List<ExaMineVo> exaMineVOS = new ArrayList<ExaMineVo>();
        ExaMineVo exaMineVO1 = new ExaMineVo(2,4,5,4,5,"测试1",
                "节点流程11","2019120811111111",-1,4);
//        ExaMineVo exaMineVO2 = new ExaMineVo(3,6,7,6,7,"测试2",
//                "节点流程11","2019120822222",-1,4);
        exaMineVOS.add(exaMineVO1);
  //      exaMineVOS.add(exaMineVO2);
//        System.out.println(waybillOrderService.exaMine(exaMineVOS));

    }


     //根据运单id查询线路详情
    @Test
    public void lineDetails() {
        System.out.println(waybillOrderService.lineDetails(1).toString());
    }


    /**
     * 添加企业
     *
     */
    @Test
    public void insertSelective() {
        WaybillOwnerVo waybillOwner = new WaybillOwnerVo();
        waybillOwner.setOwnerName("王234");
        waybillOwner.setIcbcCode("5555");
        waybillOwner.setCreditCode("5555");
        waybillOwner.setContact("15964467450");
        waybillOwner.setContactPhone("15964467450");
        waybillOwner.setCorpName("王2");
        waybillOwner.setCorpPhone("15964467450");
        waybillOwner.setLon("1");
        waybillOwner.setLat("1");
        waybillOwner.setPersonId(1);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2019-12-30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        waybillOwner.setCity(79);
        waybillOwner.setProvince(6);
        waybillOwner.setCounty(916);
        waybillOwner.setCreditEndDate(date);
        waybillOwnerService.insertSelective(waybillOwner);
    }


    /**
     * 批量修改企业审批状态
     */
    @Test
    public void BatchModifyStatus() {
        List<WaybillArea> waybillAreas = new ArrayList<WaybillArea>();
        WaybillArea waybillArea01= new WaybillArea();
        waybillArea01.setId(1);
        waybillAreas.add(waybillArea01);
        WaybillArea waybillArea02= new WaybillArea();
        waybillArea02.setId(4);
        waybillAreas.add(waybillArea02);
        WaybillArea waybillArea03= new WaybillArea();
        waybillArea03.setId(5);
        waybillAreas.add(waybillArea03);
        WaybillArea waybillArea04= new WaybillArea();
        waybillArea04.setId(6);
        waybillAreas.add(waybillArea04);
         waybillOwnerService.batchModifyStatus(waybillAreas);
    }


    //修改
    @Test
    public void updateByPrimaryKeySelective() {
        WaybillOwnerVo record = new WaybillOwnerVo();
        record.setId("128");
        record.setOwnerName("测试");
        Integer[] ownerIds = {125,126,128};
        record.setDataList(ownerIds);
        record.setIsScope(true);
        record.setCity(79);
        record.setProvince(6);
        record.setCounty(916);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2019-12-30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        record.setCreditEndDate(date);

        waybillOwnerService.updateByPrimaryKeySelective(record);
    }

    //删除
    @Test
    public void deleteId() {
        List<WaybillOwner> waybillOwners = new ArrayList<WaybillOwner>();
        WaybillOwner waybillOwner1 = new WaybillOwner();
        waybillOwner1.setId("4");
        WaybillOwner waybillOwner2 = new WaybillOwner();
        waybillOwner2.setId("5");
        WaybillOwner waybillOwner3 = new WaybillOwner();
        waybillOwner3.setId("6");
        waybillOwners.add(waybillOwner1);
        waybillOwners.add(waybillOwner2);
        waybillOwners.add(waybillOwner3);
        waybillOwnerMapper.deleteId(waybillOwners, false, new Integer[]{4,5,6});
    }

    //查询企业详情
    @Test
    public void selectByPrimaryKey() {
        /*WaybillOwnerDetailsDTO waybillOwnerDetailsDTO = waybillOwnerMapper.selectByPrimaryKey("129");
        System.out.println(waybillOwnerDetailsDTO.toString());*/
        EnterpriseLlistVo enterpriseLlistVO = new EnterpriseLlistVo();
        enterpriseLlistVO.setPage(0);
        enterpriseLlistVO.setIsScope(true);
        enterpriseLlistVO.setPageSize(2);
        Integer[] dataLsit=new Integer[3];
        dataLsit[0]=128;
        dataLsit[1]=126;
        dataLsit[2]=125;
        enterpriseLlistVO.setDataList(dataLsit);
        enterpriseLlistVO.setOwnerName("淄博");
        List<WaybillOwnerDetailsDTO> list = waybillOwnerMapper.selectWaybillOwnerLike(enterpriseLlistVO);
        System.out.println(list.toString());
    }


    /**
     * 查询经营许可证过期企业
     *
     * @param
     * @return
     */
    @Test
    public void ExpiredOwner() {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO().setProvince("1").
                setCity("22").
                setCounty("33");
        System.out.println(waybillOwnerService.ExpiredOwner(userExpiredDTO));
    }


    /**
     * 根据时间段查询经营许可证过期企业
     *
     */
    @Test
    public void HistoryExpiredOwner() {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO().setProvince("1").
                setCity("22").
                setCounty("33").setStartDate("2019-1-20").setEndDate("2020-2-22");
        System.out.println( waybillOwnerMapper.HistoryExpiredOwner(userExpiredDTO));

    }

}
