package com.thundersdata.backend.basic.test;


import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dto.SysLocationDTO;
import com.thundersdata.backend.basic.service.SysLocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSON;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class CityLocationTest {
    @Autowired
    private SysLocationService sysLocationService;

    @Test
    public void getCityLocation() {
        List<SysLocationDTO> list = sysLocationService.getSysLocation();
        for (SysLocationDTO a : list) {
            System.out.println(JSON.toJSONString(a));
        }

    }


}
