package com.thundersdata.backend.basic.test;


import com.alibaba.fastjson.JSON;
import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.dto.AuthRoleDataDTO;
import com.thundersdata.backend.basic.dto.PermissionDataDTO;
import com.thundersdata.backend.basic.service.AuthorityService;
import com.thundersdata.backend.basic.vo.RoleAddMenuVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class AuthorityTest {

    private static final Logger log = LoggerFactory.getLogger(AuthorityTest.class);


    @Autowired
    private AuthorityService authorityService;

    /**
     * 查询菜单树
     */
    @Test
    public void getRoleMenuTest() {
        List<AuthResourceDTO> result = authorityService.getRoleMenu(1);
        log.info(JSON.toJSONString(result));
    }

    /**
     * 角色绑定菜单
     */
    @Test
    public void roleAddMenuTest() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        RoleAddMenuVO roleAddMenuVO = new RoleAddMenuVO();
        roleAddMenuVO.setRoleId(2);
        roleAddMenuVO.setMenuList(ids);
        Integer result = authorityService.roleAddMenu(roleAddMenuVO);
        log.info(JSON.toJSONString(result));
    }

    /**
     * 查询角色数据范围
     */
    @Test
    public void getRoleDataTest() {
        List<AuthRoleDataDTO> result = authorityService.getRoleData(1, 0);
        log.info(JSON.toJSONString(result));
    }

    /**
     * 查询用户数据范围
     */
    @Test
    public void getPermissionDataTest() {
        PermissionDataDTO result = authorityService.getPermissionData(884);
        log.info(JSON.toJSONString(result));
    }

}
