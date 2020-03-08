package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillAreaDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.vo.WaybillAreaVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WaybillAreaMapper {

    /**
     * 删除装卸区域
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * 添加装卸区域
     * @param record
     * @return
     */
    int insertSelective(WaybillArea record);

    /**
     * 查询装卸区详情
     * @param id
     * @return
     */
    WaybillArea selectByPrimaryKey(Integer id);

    /**
     * 装卸区域信息修改
     * @param record
     * @param isScope
     * @param ownerIds
     * @return
     */
    int updateByPrimaryKeySelective(@Param("WaybillArea")WaybillArea record,
                                    @Param("isScope")Boolean isScope,
                                    @Param("dataList")Integer[] ownerIds);

    int updateByPrimaryKey(WaybillArea record);


    /**
     * 条件查询装卸区域条数
     * @param waybillAreaVo
     * @return
     */
    int  getCountByCondition(@Param("waybillAreaVo")WaybillAreaVo waybillAreaVo);

    /**
     * 装卸区域列表
     *
     * @param waybillAreaVo
     * @return
     */
    List<WaybillArea> selectWaybillOwnerLike(@Param("waybillAreaVo") WaybillAreaVo waybillAreaVo);

    /**
     * 删除装卸区域
     * @param idList
     * @param isScope
     * @param ownerIds
     * @return
     */
    int deleteId(@Param("list") List<WaybillArea> idList,
                 @Param("isScope")Boolean isScope,
                 @Param("dataList")Integer[] ownerIds);


    /**
     * 装卸区域下拉框
     * @param areaNo
     * @return
     */
    List<WaybillAreaDTO> dropDown(@Param("areaNo") String areaNo);


    /**
     * 数据范围判断装卸区域下拉框
     * @param areaNo
     * @return
     */
    List<WaybillAreaDTO> dataDropDown(@Param("areaNo") String areaNo,
                                      @Param("isScope")Boolean isScope,
                                      @Param("dataList")Integer[] ownerIds);
}


