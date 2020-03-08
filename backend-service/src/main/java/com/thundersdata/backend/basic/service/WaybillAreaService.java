package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.WaybillAreaDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.vo.WaybillAreaVo;
import com.thundersdata.backend.common.utils.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname WaybillAreaService
 * @Description TODO
 * @Date 2019/12/13 11:16
 * @Created wrc
 */
public interface WaybillAreaService {


    /**
     * 装卸区域列表
     *
     * @param waybillAreaVo
     * @return
     */
    Pagination<WaybillArea> selectWaybillOwnerLike( WaybillAreaVo waybillAreaVo);

    /**
     * 查询装卸区详情
     * @param id
     * @return
     */
    WaybillArea selectByPrimaryKey(Integer id);

    /**
     * 添加装卸区域
     * @param record
     * @return
     */
    int insertSelective(WaybillArea record);

    /**
     * 删除装卸区域
     * @param idList
     * @param isScope
     * @param ownerIds
     * @return
     */
    int deleteId( List<WaybillArea> idList,
                  Boolean isScope,
                  Integer[] ownerIds);


    /**
     * 装卸区域信息修改
     * @param record
     * @param isScope
     * @param ownerIds
     * @return
     */
    int updateByPrimaryKeySelective(WaybillArea record,
                                    Boolean isScope,
                                    Integer[] ownerIds);

    /**
     * 装卸区域下拉框
     * @param areaNo
     * @return
     */
    List<WaybillAreaDTO>  dropDown(String areaNo);

    /**
     * 数据范围判断装卸区域下拉框
     * @param areaNo
     * @return
     */
    List<WaybillAreaDTO> dataDropDown(@Param("areaNo") String areaNo,
                                      @Param("isScope")Boolean isScope,
                                      @Param("ownerIds")Integer[] ownerIds);

}
