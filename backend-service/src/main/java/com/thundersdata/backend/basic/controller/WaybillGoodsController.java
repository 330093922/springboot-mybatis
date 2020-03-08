package com.thundersdata.backend.basic.controller;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dto.DictDTO;
import com.thundersdata.backend.basic.dto.WaybillGoodsDTO;
import com.thundersdata.backend.basic.dto.WaybillGoodsDetailDTO;
import com.thundersdata.backend.basic.service.WaybillGoodsService;
import com.thundersdata.backend.basic.vo.WaybillGoodsDetailVO;
import com.thundersdata.backend.basic.vo.WaybillGoodsVO;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 危险化学品货物管理控制器
 */
@Api(tags = "危险化学品货物管理接口")
@RestController
@RequestMapping("goods")
public class WaybillGoodsController {
    @Autowired
    private WaybillGoodsService waybillGoodsService;

    /**
     * 新增危险化学品货物信息
     *
     * @param waybillGoods
     * @return
     */
    @ApiOperation(value = "新增危险化学品货物信息", notes = "全量新增")
    @PostMapping("insert")
    public int insertWaybillGoods(@RequestBody WaybillGoodsVO waybillGoods, Integer loginType) {
        Assert.isFalse(loginType == 0, "您没有权限！");

        return waybillGoodsService.insertWaybillGoods(waybillGoods);
    }


    /**
     * 修改危险化学品货物信息
     *
     * @param waybillGoods
     * @return
     */
    @ApiOperation(value = "修改危险化学品货物信息", notes = "选择修改")
    @PostMapping("update")
    public int updateWaybillGoods(@RequestBody WaybillGoodsVO waybillGoods, Integer loginType) {
        Assert.isFalse(loginType == 0, "您没有权限！");

        return waybillGoodsService.updateWaybillGoods(waybillGoods);
    }
    
    /**
     * 根据id返回危险化学品货物详情信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询危险化学品货物详情", notes = "危险化学品货物id")
    @GetMapping("detail")
    @ResponseBody
    public WaybillGoodsDTO detail(Integer id) {
        return waybillGoodsService.selectWaybillGoods(id);
    }

    /**
     * 根据id返回危险化学品货物详情信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询危险化学品货物详情,包含附加信息", notes = "查询危险化学品货物详情,包含附加信息,安全卡,操作规程,检查项")
    @GetMapping("detailMore")
    @ResponseBody
    public WaybillGoodsDetailDTO detailMore(Integer id) {
        return waybillGoodsService.selectWaybillGoodsDetail(id);
    }

    /**
     * 修改危险化学品货物关联信息
     *
     * @param WaybillGoodsDetail
     * @return
     */
    @ApiOperation(value = "修改危险化学品货物关联信息", notes = "修改危险化学品货物关联信息,安全卡,操作规程,检查项")
    @PutMapping("updateDetail")
    public int updateWaybillGoodsDetail(@RequestBody WaybillGoodsDetailVO WaybillGoodsDetail, Integer loginType) {
        Assert.isFalse(loginType == 0, "您没有权限！");

        return waybillGoodsService.updateWaybillGoodsDetail(WaybillGoodsDetail);
    }





    /**
     * 删除危险化学品货物信息
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除危险化学品货物信息", notes = "根据主键删除")
    @PostMapping("delete")
    public int deleteWaybillGoods(@RequestBody List<Map<String,Integer>> ids) {
        List<Integer> idList = new ArrayList<>();
        for (int i = 0 ; i < ids.size(); i++) {
            Map<String,Integer> map = ids.get(i);
            if(null != map && null != map.get("id")){
                idList.add(map.get("id"));
            }
        }
        Integer[] idArray = idList.toArray(new Integer[idList.size()]);

        return waybillGoodsService.updateWaybillGoodsDeleteedBatch(idArray);
    }

    /**
     * 分页查询危险化学品货物信息列表
     *
     * @return list
     */
    @ApiOperation(value = "查询危险化学品货物信息列表", notes = "通过参数进行模糊查询")
    @GetMapping("list")
    public Pagination<WaybillGoodsDTO> selectAllWaybillGoods(@RequestParam(name="page", defaultValue="1") Integer page, @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, String unNo, String name, String alias) {
        return waybillGoodsService.selectWaybillGoods(page, pageSize, unNo, name, alias);
    }


    @ApiOperation(value = "查询危险化学品货物信息下拉框", notes = "通过参数进行模糊查询")
    @GetMapping("combo")
    public List<DictDTO> selectAllWaybillGoodsCombo(@RequestParam(name="value", defaultValue="") String value) {
        List<DictDTO> list = waybillGoodsService.selectWaybillGoodsCombo(value);

        return list;
    }



}
