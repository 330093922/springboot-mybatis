package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WayBillUserDictDto;
import com.thundersdata.backend.basic.dto.WaybillUserDTO;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.UserHistogramVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WaybillUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillUser record);

    int insertSelective(WaybillUser record);

    WaybillUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillUser record);

    int updateByPrimaryKeyWithBLOBs(WaybillUser record);

    int updateByPrimaryKey(WaybillUser record);

    /**
     * 查询人员总数
     *
     * @param name
     * @param idCard
     * @param phone
     * @param ownerIds 企业id
     * @param isScope  范围
     * @return
     */
    int countWaybillUser(@Param("name") String name,
                         @Param("idCard") String idCard,
                         @Param("phone") String phone,
                         @Param("ownerIds") Integer[] ownerIds,
                         @Param("isScope") Boolean isScope);

    /**
     * 查询人员信息列表
     *
     * @param name     姓名
     * @param idCard   证件号
     * @param phone    手机号
     * @param limit    页码
     * @param offset   数量
     * @param ownerIds 企业id
     * @param isScope  范围
     * @return
     */
    List<WaybillUserDTO> selectAllWaybillUser(@Param("name") String name,
                                              @Param("idCard") String idCard,
                                              @Param("phone") String phone,
                                              @Param("limit") Integer limit,
                                              @Param("offset") Integer offset,
                                              @Param("ownerIds") Integer[] ownerIds,
                                              @Param("isScope") Boolean isScope);

    /**
     * 删除人员信息
     *
     * @param waybillUserList  人员id集合
     * @param ownerIds 企业id
     * @param isScope  范围
     * @return
     */
    int deleteWaybillUser(@Param("waybillUserList") List<WaybillUser> waybillUserList, @Param("ownerIds") Integer[] ownerIds, @Param("isScope") Boolean isScope);

    /**
     * 查询人员下拉列表框
     *
     * @param name   人员姓名
     * @param phone  手机号
     * @param idCard 证件号
     * @return
     */
    List<WayBillUserDictDto> selectAllList(@Param("name") String name, @Param("phone") String phone, @Param("idCard") String idCard);

    WaybillUser getWaybillUserByPassword(Map<String, String> param);

    WaybillUser getWaybillUserByToken(String token);

    /**
     * reset密码
     *
     * @param personList
     * @return
     */
    Integer resetPassword(@Param("personList") List<Integer> personList);

    /**
     * 修改司机和押运人状态
     *
     * @param waybillUser
     * @return
     */
    int stateModification(WaybillUser waybillUser);

    /**
     * 根据id查询人员信息
     *
     * @param id
     * @return
     */
    WaybillUserDTO selectWaybillUser(Integer id);

    /**
     * 修改人员状态,根据人员id
     * 当前人员状态为0改为1,为1改为0
     *
     * @param id
     */
    int updateWaybillUserStatus(Integer id);


    int updateStates(@Param("pilotId") Integer pilotId, @Param("supercargoId") Integer supercargoId);

    /**
     * 企业id绑定用户
     *
     * @return
     */
    int EnterpriseUuser(WaybillUser waybillUser);

    /**
     * 查询是否有重复手机号
     *
     * @param phone 手机号
     * @return
     */
    String selectByPhone(String phone);

    /**
     * 修改人员信息
     *
     * @param waybillUser 人员实体类
     * @param ownerIds    企业id
     * @param isScope     范围
     * @return
     */
    int updateUser(@Param("waybillUser") WaybillUser waybillUser, @Param("ownerIds") Integer[] ownerIds, @Param("isScope") Boolean isScope);


    /**
     * 人员下拉列表框(根据企业id查询)
     *
     * @param name   姓名
     * @param phone  手机号
     * @param idCard 证件号
     * @return
     */
    List<WayBillUserDictDto> selectDropList(@Param("name") String name, @Param("phone") String phone, @Param("idCard") String idCard, @Param("ownerIds") Integer[] ownerIds, @Param("isScope") Boolean isScope);

    /**
     * 修改为调度员或是司机
     * @param userId
     * @return
     */
    int updateWaybillUserByloginType(Integer userId);


    /**
     * 查询从业资格证过期人员
     * @param userExpiredDTO
     * @param newTime
     * @return
     */
    List<UserExpiredVO> ExpiredUser(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO,@Param("newTime") String newTime);

    /**
 * 根据时间段查询从业资格证过期人员
 * @param userExpiredDTO
 * @return
 */
List<UserHistogramVO> HistoryExpiredUser(@Param("userExpiredDTO") UserExpiredDTO userExpiredDTO);
    /**
     * 查询是否有重复账户
     *
     * @param username 账户
     * @return
     */
    String selectByUsername(String username);
}