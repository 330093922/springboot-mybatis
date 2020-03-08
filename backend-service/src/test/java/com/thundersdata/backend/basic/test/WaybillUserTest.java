package com.thundersdata.backend.basic.test;

import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.service.WaybillUserService;
import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dao.WaybillUserMapper;
import com.thundersdata.backend.basic.dto.WaybillUserDTO;

import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.utils.TimeProcessing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Classname WaybillUserTest
 * @Description TODO
 * @Date 2020/1/22 15:54
 * @Created wrc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WaybillUserTest {

    @Autowired
    private WaybillUserMapper waybillUserMapper;
    @Autowired
    private WaybillUserService waybillUserService;


    //查询从业资格证过期人员
    @Test
    public void UserExpired() {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO().setProvince("370000").
                setCity("370300").
                setCounty("370303")
                .setStartDate("2020-01-20")
                .setEndDate("2020-01-22");
        System.out.println(waybillUserService.ExpiredUser(userExpiredDTO));
    }


    /**
     * 根据时间段查询从业资格证过期人员
     */
    @Test
    public void HistoryUserExpired() throws ParseException {
        UserExpiredDTO userExpiredDTO = new UserExpiredDTO().
                setProvince("370000").
                setCity("370300").
                setCounty("370303").setStartDate("2020-01-20").setEndDate("2020-01-22");
        System.out.println(waybillUserService.HistoryExpiredUser(userExpiredDTO));
    }


    /**
     * 添加企业人员
     */
    @Test
    public void insertSelective() {

        Date a = new Date();
        WaybillUser waybillUser = new WaybillUser();
        waybillUser.setUsername("ceshiceshi");
        waybillUser.setPassword("123456");
        waybillUser.setLoginType(1);
        waybillUser.setPhone("1888811888");
        waybillUser.setEmployedThe("18888888");
        waybillUser.setEmployedEndDate(a);
        waybillUser.setName("测试姓名");
        waybillUserService.insertWaybillUser(waybillUser);
    }


    //查询企业人员详情
    @Test
    public void selectByPrimaryKey() {
        WaybillUserDTO waybillUserDTO = waybillUserMapper.selectWaybillUser(5);
        System.out.println(waybillUserDTO);
    }


}