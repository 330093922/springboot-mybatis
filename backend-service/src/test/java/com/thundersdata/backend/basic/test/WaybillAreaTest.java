package com.thundersdata.backend.basic.test;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dto.lineDetailsDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.service.WaybillAreaService;
import com.thundersdata.backend.basic.vo.WaybillAreaVo;
import com.thundersdata.backend.common.utils.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname WaybillAreaTest
 * @Description TODO
 * @Date 2019/12/13 15:42
 * @Created wrc
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class WaybillAreaTest {

    @Autowired
    private WaybillAreaService waybillAreaService;

    //添加
    @Test
    public void updateByPrimaryKeyWithBLOBs() {
        WaybillArea waybillArea = new WaybillArea();
        waybillArea.setAreaNo("1");
        waybillArea.setAreaName("测试1");
        waybillArea.setType((byte) 2);
        waybillArea.setGoodsName("石渣");
        waybillArea.setClassify("1");
        waybillArea.setInputDate(new Date());
        waybillArea.setVolume(1);
        waybillArea.setLength(1);
        waybillArea.setWide(1);
        waybillArea.setImageUrl("/images/111.png");
        waybillArea.setRemark("测试备注");
        waybillAreaService.insertSelective(waybillArea);
    }

    //修改
    @Test
    public void  updateByPrimaryKey() {
        WaybillArea waybillArea = new WaybillArea();
        waybillArea.setId(2);
        waybillArea.setAreaNo("11");
        waybillAreaService.updateByPrimaryKeySelective(waybillArea,true,new Integer[]{4,5});
    }

    //详情
    @Test
    public void selectByPrimaryKey() {
        System.out.println(waybillAreaService.selectByPrimaryKey(1));
    }

    //列表
    @Test
    public void selectWaybillOwnerLike() {
        WaybillAreaVo waybillArea = new WaybillAreaVo();
        waybillArea.setAreaName("测试");
        waybillArea.setPage(1);
        waybillArea.setPageSize(2);
        waybillArea.setDataList(new Integer[]{4,5});
        waybillArea.setIsScope(true);
        System.out.println(waybillAreaService.selectWaybillOwnerLike(waybillArea));
    }


    //删除
    @Test
    public void deleteId() {
        List<WaybillArea> idList = new ArrayList<WaybillArea>();
        WaybillArea waybillArea01 = new WaybillArea();
        waybillArea01.setId(1);
        idList.add(waybillArea01);
        WaybillArea waybillArea02 = new WaybillArea();
        waybillArea02.setId(2);
        idList.add(waybillArea02);
        System.out.println(waybillAreaService.deleteId(idList,true,new Integer[]{4,5}));
    }

    //下拉框测试
    @Test
    public void lineDetails() {
        System.out.println(waybillAreaService.dataDropDown("1",true,new Integer[]{4,5}).toString());
    }




}
