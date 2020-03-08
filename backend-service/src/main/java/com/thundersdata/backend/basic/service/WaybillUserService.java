package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WayBillUserDictDto;
import com.thundersdata.backend.basic.dto.WaybillUserDTO;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.UserHistogramVO;
import com.thundersdata.backend.common.utils.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface WaybillUserService {

    /**
     * 人员列表分页
     * @param name 人员姓名
     * @param idCard 证件号码
     * @param phone 手机号
     * @param page 页数
     * @param pageSize 数量
     * @param ownerIds 企业id
     * @param isScope 范围
     * @return
     */
    Pagination<WaybillUserDTO> selectAllWaybillUser(String name,
                                                    String idCard,
                                                    String phone,
                                                    Integer page, Integer pageSize, Integer[] ownerIds, Boolean isScope);

    /**
     * 人员下拉列表框
     * @param name 姓名
     * @param phone 手机号
     * @param idCard 证件号
     * @return
     */
    List<WayBillUserDictDto> selectAllList(String name, String phone, String idCard);




    /**
     * 添加人员信息
     *
     * @param waybillUser
     * @return
     */
    int insertWaybillUser(WaybillUser waybillUser);

    /**
     * 修改人员信息
     * @param waybillUser 人员实体类
     * @param ownerIds 企业id
     * @param isScope 范围
     * @return
     */
    int updateWaybillUser(WaybillUser waybillUser,Integer[] ownerIds, Boolean isScope);

    /**
     * 删除人员信息
     * @param waybillUserList 人员id集合
     * @param ownerIds 企业id
     * @param isScope 范围
     * @return
     */
    int deleteWaybillUser(List<WaybillUser> waybillUserList,Integer[] ownerIds, Boolean isScope);

    /**
     * 根据id返回人员详情信息
     *
     * @param id
     * @return
     */
    WaybillUserDTO selectWaybillUser(Integer id);

    /**
     * 修改人员状态,根据人员id
     * 当前人员状态为0改为1,为1改为0
     * @param userId
     */
    int updateWaybillUserStatus(Integer userId);

    /**
     * 人员下拉列表框(根据企业id查询)
     * @param name 人员姓名
     * @param phone 手机号码
     * @param idCard 证件id
     * @param ownerIds 企业id
     * @param isScope 范围
     * @return
     */
    List<WayBillUserDictDto> selectDropList(String name, String phone, String idCard, Integer[] ownerIds, Boolean isScope);

    /**
     * 修改是调度员或是司机
     * @param userId
     * @return
     */
    int updateWaybillUserByloginType(Integer userId);


    /**
     * 查询从业资格证过期人员
     * @param userExpiredDTO
     * @param
     * @return
     */
    List<UserExpiredVO> ExpiredUser(UserExpiredDTO userExpiredDTO);

    /**
     * 根据时间段查询从业资格证过期人员
     * @param userExpiredDTO
     * @return
     */
    List<UserHistogramVO>  HistoryExpiredUser(UserExpiredDTO userExpiredDTO);

}
