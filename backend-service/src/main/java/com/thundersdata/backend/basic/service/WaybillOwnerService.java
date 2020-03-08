package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDetailsDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.vo.EnterpriseLlistVo;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.WaybillOwnerVo;
import com.thundersdata.backend.common.utils.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname WaybillOwnerService
 * @Description TODO
 * @Date 2019/12/6 10:08
 * @Created wrc
 */
public interface WaybillOwnerService {

    /**
     *根据企业id查询企业详情
     * @param id
     * @return
     */
    WaybillOwnerDetailsDTO selectByPrimaryKey(String id);


    /**
     * 企业信息
     * @param enterpriseLlistVO
     * @return
     */
    Pagination<WaybillOwnerDetailsDTO> selectWaybillOwnerLike(EnterpriseLlistVo enterpriseLlistVO);

    /**
     * 添加企业信息
     * @param record
     * @return
     */
    int insert(WaybillOwner record);

    /**
     * 企业下拉框
     * @param ownerName
     * @return
     */
    List<WaybillOwnerDTO>  dropdown(String ownerName);

    /**
     * 删除企业信息
     * @param WaybillOwner
     * @param isScope
     * @param ownerIds
     * @return
     */
    int deleteId( List<WaybillOwner> WaybillOwner, Boolean isScope, Integer[] ownerIds);
    /**
     * 添加企业
     * @param waybillOwnerVo
     * @return
     */
    int insertSelective(WaybillOwnerVo waybillOwnerVo);

    /**
     * 修改企业信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WaybillOwnerVo record);


    /**
     * 批量修改企业审批状态
     * @param waybillAreas
     * @return
     */
    int batchModifyStatus(List<WaybillArea> waybillAreas);



    /**
     * 数据范围判断下拉框
     * @param ownerName
     * @param isScope
     * @param ownerIds
     * @return
     */
    WaybillOwnerDTO dataDropDown(@Param("ownerName") String ownerName,
                                 @Param("isScope")Boolean isScope,
                                 @Param("ownerIds")Integer[] ownerIds);
    /**
     * 查询经营许可证过期企业
     * @param userExpiredDTO
     * @param
     * @return
     */
    List<UserExpiredVO> ExpiredOwner( UserExpiredDTO userExpiredDTO);

    /**
     * 根据时间段查询经营许可证过期企业
     * @param userExpiredDTO
     * @return
     */
    List<UserExpiredVO> HistoryExpiredOwner(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO);

    /**
     * 根据多个企业id查询企业详细信息
     * @param ownerIds
     * @return
     */
    List<WaybillOwnerDetailsDTO> selectByOwerIds(List<Integer> ownerIds);
}
