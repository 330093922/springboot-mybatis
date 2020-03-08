package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDetailsDTO;
import com.thundersdata.backend.basic.model.WaybillArea;
import com.thundersdata.backend.basic.model.WaybillOwner;
import com.thundersdata.backend.basic.vo.EnterpriseLlistVo;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.WaybillOwnerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface WaybillOwnerMapper {


    int deleteByPrimaryKey(String id);

    int insert(WaybillOwner record);

    /**
     * 添加企业
     * @param waybillOwnerVo
     * @return
     */
    int insertSelective(WaybillOwnerVo waybillOwnerVo);


    WaybillOwnerDetailsDTO selectByPrimaryKey(String id);

    /**
     * 修改企业信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WaybillOwnerVo record);

    int updateByPrimaryKey(WaybillOwner record);


    /**
     * 企业列表
     *
     * @param enterpriseLlistVO
     * @return
     */
    List<WaybillOwnerDetailsDTO> selectWaybillOwnerLike(@Param("enterpriseLlistVO") EnterpriseLlistVo enterpriseLlistVO);

    /**
     * 企业下拉框
     *
     * @param ownerName
     * @return
     */
    List<WaybillOwnerDTO> dropdown(@Param("ownerName") String ownerName);

    /**
     * 条件查询企业条数
     * @param enterpriseLlistVO
     * @return
     */
    int  getCountByCondition(@Param("enterpriseLlistVO") EnterpriseLlistVo enterpriseLlistVO);
    /**
     * 删除企业信息
     * @param WaybillOwner
     * @param isScope
     * @param ownerIds
     * @return
     */
    int deleteId(@Param("list") List<WaybillOwner> WaybillOwner,
                 @Param("isScope")Boolean isScope,
                 @Param("dataList")Integer[] ownerIds);


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
                                 @Param("dataList")Integer[] ownerIds);


    /**
     * 查询经营许可证过期企业
     * @param userExpiredDTO
     * @param newTime
     * @return
     */
    List<UserExpiredVO> ExpiredOwner(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO, @Param("newTime") String newTime);


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
    List<WaybillOwnerDetailsDTO> selectByOwerIds(@Param("ownerIds") List<Integer> ownerIds);
}