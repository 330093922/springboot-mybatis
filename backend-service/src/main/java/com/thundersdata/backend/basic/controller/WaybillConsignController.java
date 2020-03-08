package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.WaybillConsignDTO;
import com.thundersdata.backend.basic.service.WaybillConsignService;
import com.thundersdata.backend.basic.vo.*;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 托运单
 */
@Api(tags = "托运单接口")
@RestController
@RequestMapping("consign")
public class WaybillConsignController {
    @Autowired
    private WaybillConsignService waybillConsignService;


    /**
     * 新增托运单
     *
     * @param waybillConsignInsertVO
     * @return
     */
    @ApiOperation(value = "新增托运单", notes = "全量新增")
    @PostMapping("insert")
    public int insertWaybillConsign(@RequestBody WaybillConsignInsertVO waybillConsignInsertVO) {
        return waybillConsignService.insertWaybillConsign(waybillConsignInsertVO);
    }

    /**
     * 修改托运单
     *
     * @param waybillConsignUpdateVO
     * @return
     */
    @ApiOperation(value = "修改托运单详情", notes = "全量修改")
    @PostMapping("update")
    public int updateWaybillConsign(@RequestBody WaybillConsignUpdateVO waybillConsignUpdateVO, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillConsignService.updateWaybillConsign(waybillConsignUpdateVO, ownerIds, isScope);
    }

    /**
     * 接单托运单
     * @param id
     * @param dataList
     * @param isScope
     * @return
     */
    @ApiOperation(value = "接单", notes = "接单")
    @PostMapping("accept")
    public int acceptWaybillConsign(Integer id, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillConsignService.acceptWaybillConsign(id, ownerIds, isScope);
    }

    /**
     * 拒绝托运单
     * @param id
     * @param remark
     * @param dataList
     * @param isScope
     * @return
     */
    @ApiOperation(value = "拒绝托运单", notes = "拒绝托运单")
    @PostMapping("reject")
    public int rejectWaybillConsign(Integer id, String remark, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillConsignService.rejectWaybillConsign(id, remark, ownerIds, isScope);
    }


    /**
     * 删除托运单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除托运单", notes = "托运单id")
    @DeleteMapping("delete")
    public int deleteWaybillConsign(Integer id, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillConsignService.deleteWaybillConsign(id, ownerIds, isScope);
    }

    /**
     * 查询托运单列表
     *
     * @param selectConsignList 运单
     * @return list
     */
    @ApiOperation(value = "查询运单列表", notes = "userId,loginType为网关注入,所以不需要体现在文档上")
    @PostMapping("consignList")
    public Pagination<WaybillConsignDTO> selectConsignList(@RequestBody SelectConsignList selectConsignList,
                                                       @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope) {
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);


        return waybillConsignService.selectConsignList(selectConsignList.getConsignCode(), selectConsignList.getOwner(), selectConsignList.getStates(), selectConsignList.getPage(),
                selectConsignList.getPageSize(), ownerIds, isScope);
    }

    /**
     * 查询托运单详情
     *
     * @param id 托运单id
     * @return consignDetail
     */
    @ApiOperation(value = "查询运单详情", notes = "订单id")
    @GetMapping("consignDetail")
    public WaybillConsignDTO selectConsignDetail(Integer id) {

        return waybillConsignService.selectConsignDetail(id);
    }

    @ApiOperation(value = "派发订单", notes = "派发订单")
    @PostMapping("dispatchOrder")
    public int dispatchOrder(@RequestBody WaybillConsignDispatchVO waybillConsignDispatchVO, Integer userId, Integer userType, @ApiIgnore() String dataList, @ApiIgnore() Boolean isScope){
        Integer[] ownerIds = ArrayUtils.jsonArrayToList(dataList);

        return waybillConsignService.dispatchConsign(waybillConsignDispatchVO, userId, userType, ownerIds, isScope);
    }

    @ApiOperation(value = "查询多个ID的电子运单详情", notes = "查询多个ID的电子运单详情")
    @GetMapping("consignDetailes")
    public List<WaybillConsignDTO> getWaybillConsignByIdes(String ides) {
        return waybillConsignService.getWaybillConsignByIdes(ides);
    }

}
