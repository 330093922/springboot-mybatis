package com.thundersdata.backend.basic.controller;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WayBillUserDictDto;
import com.thundersdata.backend.basic.dto.WaybillUserDTO;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.service.WaybillUserService;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.UserHistogramVO;
import com.thundersdata.backend.basic.vo.UserListVo;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * 人员信息控制器
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("user")
public class WaybillUserController {
    @Autowired
    private WaybillUserService waybillUserService;

    /**
     * 根据id返回人员详情信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户详情", notes = "用户id")
    @PostMapping("info")
    @ResponseBody
    public WaybillUserDTO selectWaybillUserByPrimaryKey(Integer id) {
        return waybillUserService.selectWaybillUser(id);
    }

    /**
     * 人员信息列表
     *
     * @return list
     */
    @ApiOperation(value = "查询人员信息列表", notes = "通过参数进行模糊查询")
    @PostMapping("list")
    public Pagination<WaybillUserDTO> selectAllWaybillUser(@RequestBody UserListVo userListVo, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillUserService.selectAllWaybillUser(userListVo.getName(),
                userListVo.getIdCard(), userListVo.getPhone(), userListVo.getPage(), userListVo.getPageSize(), ownerIds, isScope);
    }

    /**
     * 用户下拉框
     *
     * @param waybillUser
     * @return
     */
    @ApiOperation(value = "用户下拉框", notes = "通过人员姓名 模糊查询人员下拉框")
    @PostMapping("listAll")
    public List<WayBillUserDictDto> selectAllList(@RequestBody WaybillUser waybillUser) {
        return waybillUserService.selectAllList(waybillUser.getName(),waybillUser.getPhone(),waybillUser.getIdCard());
    }

    /**
     * 新增人员信息
     *
     * @param waybillUser
     * @return
     */
    @ApiOperation(value = "新增人员信息", notes = "全量新增")
    @PostMapping("insert")
    public int insertWaybillUser(@RequestBody WaybillUser waybillUser, Integer loginType) {
        Assert.isFalse(loginType == 0, "您没有权限！");

        return waybillUserService.insertWaybillUser(waybillUser);
    }

    /**
     * 修改人员信息
     * @param waybillUser 人员实体类
     * @param dataList 企业id
     * @param isScope 范围
     * @return
     */
    @ApiOperation(value = "修改人员信息", notes = "选择修改")
    @PostMapping("update")
    public int updateWaybillUser(@RequestBody WaybillUser waybillUser, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillUserService.updateWaybillUser(waybillUser,ownerIds,isScope);
    }

    /**
     * 删除人员信息
     * @param waybillUserList 人员实体类
     * @param dataList 企业id
     * @param isScope 范围
     * @return
     */
    @ApiOperation(value = "删除人员信息", notes = "全量删除")
    @PostMapping("delete")
    public int deleteWaybillUser(@RequestBody List<WaybillUser> waybillUserList,  @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillUserService.deleteWaybillUser(waybillUserList,ownerIds,isScope);
    }

    /**
     * 修改人员状态,根据用户id
     * 当前人员状态为0改为1,为1改为0
     * 返回值data为受影响行数
     * @param personId
     * @return
     */
    @ApiOperation(value = "修改人员状态", notes = "人员状态0改为1,1改为0,返回值为受影响行数")
    @GetMapping("updateUserStatus")
    public int updateWaybillUserStatus(Integer personId, Integer loginType) {
        Assert.isFalse(loginType == 0, "您没有权限！");

        return waybillUserService.updateWaybillUserStatus(personId);
    }
    /**
     * 用户下拉框(根据企业id)
     *
     * @param waybillUser
     * @return
     */
    @ApiOperation(value = "用户下拉框", notes = "通过人员姓名 模糊查询人员下拉框")
    @PostMapping("dropList")
    public List<WayBillUserDictDto> selectDropList(@RequestBody WaybillUser waybillUser,@ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);
        return waybillUserService.selectDropList(waybillUser.getName(),waybillUser.getPhone(),waybillUser.getIdCard(),ownerIds,isScope);
    }

    /**
     * 修改是司机或是调度员
     * @param personId
     * @return
     */
    @ApiOperation(value = "修改人员为调度员或是司机", notes = "调度员0改为1,司机1改为0")
    @GetMapping("updateWaybillUserByloginType")
    public int updateWaybillUserByloginType(Integer personId) {

        return waybillUserService.updateWaybillUserByloginType(personId);
    }


    /**
     * 查询从业资格证过期人员
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "查询从业资格证过期人员", notes = "区域")
    @PostMapping("UserExpired")
    public List<UserExpiredVO> ExpiredUser(@RequestBody UserExpiredDTO userExpiredDTO) {
        return waybillUserService.ExpiredUser(userExpiredDTO);
    }


    /**
     * 根据时间段查询从业资格证过期人员
     *
     * @param userExpiredDTO
     * @return
     */
    @ApiOperation(value = "根据时间段查询从业资格证过期人员", notes = "区域，开始时间，结束时间")
    @PostMapping("HistoryUserExpired")
    public List<UserHistogramVO> HistoryExpiredUser(@RequestBody UserExpiredDTO userExpiredDTO) {
        return waybillUserService.HistoryExpiredUser(userExpiredDTO);
    }


}
