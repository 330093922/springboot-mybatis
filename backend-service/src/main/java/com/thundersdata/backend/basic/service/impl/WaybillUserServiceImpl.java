package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.WaybillUserMapper;
import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WayBillUserDictDto;
import com.thundersdata.backend.basic.dto.WaybillUserDTO;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.service.WaybillUserService;
import com.thundersdata.backend.basic.utils.OrderUtil;
import com.thundersdata.backend.basic.utils.TimeProcessing;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.UserHistogramVO;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

/**
 * 人员信息
 */
@Service
public class WaybillUserServiceImpl implements WaybillUserService {
    @Autowired
    private WaybillUserMapper waybillUserMapper;

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
    @Override
    public Pagination<WaybillUserDTO> selectAllWaybillUser(String name,
                                                           String idCard, String phone,
                                                           Integer page, Integer pageSize,
                                                           Integer[] ownerIds, Boolean isScope) {

        PageParam pageparam = PageUtils.init(page, pageSize);

        int total = waybillUserMapper.countWaybillUser(OrderUtil.getLikeStr(name), OrderUtil.getLikeStr(idCard), OrderUtil.getLikeStr(phone), ownerIds, isScope);

        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }

        List<WaybillUserDTO> waybillUserList = waybillUserMapper.selectAllWaybillUser(OrderUtil.getLikeStr(name), OrderUtil.getLikeStr(idCard), OrderUtil.getLikeStr(phone), pageparam.getLimit(), pageparam.getOffset(), ownerIds, isScope);

        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, waybillUserList);

    }

    /**
     * 人员下拉列表框
     * @param name 姓名
     * @param phone 手机号
     * @param idCard 证件号
     * @return
     */
    @Override
    public List<WayBillUserDictDto> selectAllList(String name,String phone,String idCard) {
        return waybillUserMapper.selectAllList(name,phone,idCard);
    }

    /**
     * 添加人员信息
     *
     * @param waybillUser
     * @return
     */
    @Override
    public int insertWaybillUser(WaybillUser waybillUser) {
        Assert.notNull(waybillUser.getUsername(), "登录账号不能为空！");
        Assert.notNull(waybillUser.getPassword(), "登录密码不能为空！");
        isNullWaybillUser(waybillUser);
        String phone = waybillUserMapper.selectByPhone(waybillUser.getPhone());
        Assert.isNull(phone,"手机号码重复");
        String username = waybillUserMapper.selectByUsername(waybillUser.getUsername());
        Assert.isNull(username,"用户名称重复");
        String password = DigestUtils.md5DigestAsHex(waybillUser.getPassword().getBytes());
        waybillUser.setPassword(password);
        return waybillUserMapper.insertSelective(waybillUser);
    }

    /**
     * 修改人员信息
     * @param waybillUser 人员实体类
     * @param ownerIds 企业id
     * @param isScope 范围
     * @return
     */
    @Override
    public int updateWaybillUser(WaybillUser waybillUser,Integer[] ownerIds, Boolean isScope) {
        if(waybillUser.getName()!=null){
            isNullWaybillUser(waybillUser);
            String phone = waybillUserMapper.selectByPhone(waybillUser.getPhone());
            if(phone != null && (Integer.parseInt(phone) != waybillUser.getId())){
                Assert.isNull(phone,"手机号码重复");
            }
            String username = waybillUserMapper.selectByUsername(waybillUser.getUsername());
            if(username != null && (Integer.parseInt(username) != waybillUser.getId())){
                Assert.isNull(phone,"用户名称重复");
            }
        }
        return waybillUserMapper.updateUser(waybillUser,ownerIds,isScope);
    }


    /**
     * 删除人员信息
     * @param waybillUserList 人员id集合
     * @param ownerIds 企业id
     * @param isScope 范围
     * @return
     */
    @Override
    public int deleteWaybillUser(List<WaybillUser> waybillUserList,Integer[] ownerIds, Boolean isScope) {
        return waybillUserMapper.deleteWaybillUser(waybillUserList,ownerIds,isScope);
    }

    /**
     * 根据id返回人员详情信息
     *
     * @param id
     * @return
     */
    @Override
    public WaybillUserDTO selectWaybillUser(Integer id) {
        return waybillUserMapper.selectWaybillUser(id);
    }

    /**
     * 修改人员状态,根据人员id
     * 当前人员状态为0改为1,为1改为0
     * @param userId
     */
    @Override
    public int updateWaybillUserStatus(Integer userId) {
        int result = waybillUserMapper.updateWaybillUserStatus(userId);
        Assert.isTrue(result > 0, "人员ID不存在");

        return result;
    }

    /**
     * 人员下拉列表框(根据企业id查询)
     * @param name 人员姓名
     * @param phone 手机号码
     * @param idCard 证件id
     * @param ownerIds 企业id
     * @param isScope 范围
     * @return
     */
    @Override
    public List<WayBillUserDictDto> selectDropList(String name, String phone, String idCard, Integer[] ownerIds, Boolean isScope) {
        return waybillUserMapper.selectDropList(name, phone, idCard, ownerIds, isScope);
    }
    /**
     * 判断必填内容不能为null
     * @param waybillUser
     * @return
     */
    private String isNullWaybillUser(WaybillUser waybillUser){
        return   Assert.notNull(waybillUser.getName(), "用户姓名不能为空！")+
        Assert.notNull(waybillUser.getPhone(), "电话号码不能为空！")+
        Assert.notNull(waybillUser.getLoginType(), "请选择是否为调度员！");
    }

    /**
     * 修改人员是否为调度员,根据人员id
     * @param userId
     */
    @Override
    public int updateWaybillUserByloginType(Integer userId) {
        int result = waybillUserMapper.updateWaybillUserByloginType(userId);

        return result;
    }

    /**
     * 查询从业资格证过期人员
     *
     * @param userExpiredDTO
     * @return
     */
    @Override
    public List<UserExpiredVO> ExpiredUser(UserExpiredDTO userExpiredDTO) {
        LocalDate localDate = LocalDate.now();
        return waybillUserMapper.ExpiredUser(userExpiredDTO,localDate+"");
    }

    /**
     * 根据时间段查询从业资格证过期人员
     *
     * @param userExpiredDTO
     * @return
     */
    @Override
    public List<UserHistogramVO>  HistoryExpiredUser(UserExpiredDTO userExpiredDTO) {
        List<UserHistogramVO> userHistogramVOS =  waybillUserMapper.HistoryExpiredUser(userExpiredDTO);
        List<UserHistogramVO> userHistogramVOS1 = new ArrayList<>();
        try {
            //取得所有时间
            List<String> stringLists =  TimeProcessing.findDates(userExpiredDTO.getStartDate(),userExpiredDTO.getEndDate());
            for(int i=0;i<stringLists.size();i++){
                UserHistogramVO userHistogramVO = new UserHistogramVO().setEmployedEndDate(stringLists.get(i));
                userHistogramVOS1.add(userHistogramVO);
            }
            for(int j=0; j<userHistogramVOS1.size();j++){
                for(UserHistogramVO userHistogramVO:userHistogramVOS){
                    if(userHistogramVOS1.get(j).getEmployedEndDate().equals(userHistogramVO.getEmployedEndDate())){
                        userHistogramVOS1.get(j).setCount(userHistogramVO.getCount());
                    }
                }
            }
            //把null的次数替换成 0
            for(int k=0; k<userHistogramVOS1.size();k++){
               if(userHistogramVOS1.get(k).getCount()==null){
                   userHistogramVOS1.get(k).setCount("0");
               }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userHistogramVOS1;
    }
}
