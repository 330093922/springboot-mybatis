package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.*;
import com.thundersdata.backend.basic.service.AuthorityService;
import com.thundersdata.backend.basic.service.SysLocationService;
import com.thundersdata.backend.basic.vo.GetRoleDataVO;
import com.thundersdata.backend.basic.vo.RoleAddDataVO;
import com.thundersdata.backend.basic.vo.RoleAddMenuVO;
import com.thundersdata.backend.basic.vo.UserAddRoleVO;
import com.thundersdata.backend.common.model.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "省市区县接口")
@RestController
@RequestMapping("sysLocation")
public class SysLocationController {

    @Autowired
    private SysLocationService sysLocationService;


    /**
     * 查询地市菜单树
     *
     * @return
     */
    @ApiOperation(value = "查询地市菜单树", notes = "返回所有地市")
    @GetMapping("getAllCity")
    public List<SysLocationDTO> getAllCity() {

        return sysLocationService.getSysLocation();
    }
}
