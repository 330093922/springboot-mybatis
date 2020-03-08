package com.thundersdata.backend.basic.service.impl;


import com.thundersdata.backend.basic.dao.AuthRoleDataMapper;
import com.thundersdata.backend.basic.dao.AuthRoleUserMapper;
import com.thundersdata.backend.basic.dao.WaybillOwnerMapper;
import com.thundersdata.backend.basic.dao.WaybillUserMapper;
import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.dto.UserExpiredDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDTO;
import com.thundersdata.backend.basic.dto.WaybillOwnerDetailsDTO;
import com.thundersdata.backend.basic.model.*;
import com.thundersdata.backend.basic.service.WaybillOwnerService;
import com.thundersdata.backend.basic.vo.EnterpriseLlistVo;
import com.thundersdata.backend.basic.vo.UserExpiredVO;
import com.thundersdata.backend.basic.vo.WaybillOwnerVo;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname WaybillOwnerServiceImpl
 * @Description TODO
 * @Date 2019/12/6 10:25
 * @Created wrc
 */
@Service
public class WaybillOwnerServiceImpl implements WaybillOwnerService {

    @Autowired
    private WaybillOwnerMapper waybillOwnerMapper;
    @Autowired
    private WaybillUserMapper waybillUserMapper;
    @Autowired
    private AuthRoleUserMapper authRoleUserMapper;
    @Autowired
    private AuthRoleDataMapper authRoleDataMapper;



    /**
     * 根据企业id查询企业详情
     *
     * @param id
     * @return
     */
    @Override
    public WaybillOwnerDetailsDTO selectByPrimaryKey(String id) {
        Assert.notNull(id, "参数id不能为空！");
        return waybillOwnerMapper.selectByPrimaryKey(id);
    }

    /**
     * 企业列表
     *
     * @param enterpriseLlistVO
     * @return
     */
    @Override
    public Pagination<WaybillOwnerDetailsDTO> selectWaybillOwnerLike(EnterpriseLlistVo enterpriseLlistVO) {
        // 校验分页参数，赋默认值
        PageParam pageparam = PageUtils.init(enterpriseLlistVO.getPage(),enterpriseLlistVO.getPageSize());
        int total = waybillOwnerMapper.getCountByCondition(enterpriseLlistVO);
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }
        enterpriseLlistVO.setPageSize(pageparam.getLimit());
        enterpriseLlistVO.setPage(pageparam.getOffset());
        List<WaybillOwnerDetailsDTO> vehicleList = waybillOwnerMapper.selectWaybillOwnerLike(enterpriseLlistVO);

        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, vehicleList);
    }

    /**
     * 添加企业信息
     *
     * @param record
     * @return
     */
    @Override
    public int insert(WaybillOwner record) {
        return waybillOwnerMapper.insert(record);
    }

    /**
     * 企业下拉框
     *
     * @param ownerName 企业名称
     * @return
     */
    @Override
    public List<WaybillOwnerDTO> dropdown(String ownerName) {
        return waybillOwnerMapper.dropdown(ownerName);
    }

    /**
     * 删除企业信息
     *
     * @param WaybillOwner
     * @return
     */
    @Override
    public int deleteId(List<WaybillOwner> WaybillOwner, Boolean isScope, Integer[] ownerIds) {
        return waybillOwnerMapper.deleteId(WaybillOwner, isScope, ownerIds);
    }

    /**
     * 添加企业
     *
     * @param waybillOwnerVo
     * @return
     */
    @Override
    @Transactional
    public int insertSelective(WaybillOwnerVo waybillOwnerVo) {
        Assert.notNull(waybillOwnerVo.getOwnerName(), "参数ownerName不能为空！");
        Assert.notNull(waybillOwnerVo.getIcbcCode(), "参数icbcCode不能为空！");
        Assert.notNull(waybillOwnerVo.getCreditCode(), "参数creditCode不能为空！");
        Assert.notNull(waybillOwnerVo.getContact(), "参数contact不能为空！");
        Assert.notNull(waybillOwnerVo.getContactPhone(),"参数contactPhone不能为空！");
        Assert.notNull(waybillOwnerVo.getCorpName(),"参数corpName不能为空！");
        Assert.notNull(waybillOwnerVo.getCorpPhone(),"参数corpPhone不能为空！");
        Assert.notNull(waybillOwnerVo.getLon(),"参数lon不能为空！");
        Assert.notNull(waybillOwnerVo.getLat(),"参数lat不能为空！");
        Assert.notNull(waybillOwnerVo.getPersonId(),"参数PersonId不能为空！");
        WaybillUser waybillUser = new WaybillUser();
        waybillUser.setUsername(waybillOwnerVo.getContactPhone());
        String phone = waybillUserMapper.selectByPhone(waybillOwnerVo.getContactPhone());
        String username = waybillUserMapper.selectByUsername(waybillOwnerVo.getContactPhone());
        Assert.isNull(phone,"手机号码重复");
        Assert.isNull(username,"用户名称重复");
        //获取当前用户所有角色
        List<AuthRoleDTO> authRoleLists = authRoleUserMapper.selectByUser(waybillOwnerVo.getPersonId());
        //新增企业数据
        waybillOwnerMapper.insertSelective(waybillOwnerVo);
        String ownerId = waybillOwnerVo.getId();
        waybillUser.setOwnerId(ownerId);
        waybillUser.setPassword( DigestUtils.md5DigestAsHex("123456".getBytes()));
        waybillUser.setLoginType(1);
        waybillUser.setPhone(waybillOwnerVo.getContactPhone());
        waybillUser.setName(waybillOwnerVo.getContact());
        List<AuthRoleData> authRoles = new ArrayList<AuthRoleData>();
        for(AuthRoleDTO authRoleList:authRoleLists){
            AuthRoleData authRoleData = new AuthRoleData();
            authRoleData.setRoleId(authRoleList.getRoleId());
            authRoleData.setDataId(Integer.valueOf(ownerId));
            authRoleData.setDataType(0);
            authRoles.add(authRoleData);
        }
        //批量插入用户角色id和企业id
        authRoleDataMapper.BatchInsertion(authRoles);
        //企业id绑定用户
        waybillUserMapper.EnterpriseUuser(waybillUser);
        AuthRoleUser authRoleUser = new AuthRoleUser();
        authRoleUser.setRoleId(24);
        authRoleUser.setUserId(waybillUser.getId());
        return  authRoleUserMapper.insertSelective(authRoleUser);

    }

    /**
     * 修改企业信息
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(WaybillOwnerVo record) {
        return waybillOwnerMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 批量修改企业审批状态
     *
     * @param waybillAreas
     * @return
     */
    @Override
    public int batchModifyStatus(List<WaybillArea> waybillAreas) {
        return waybillOwnerMapper.batchModifyStatus(waybillAreas);
    }

    /**
     * 数据范围判断下拉框
     *
     * @param ownerName
     * @param isScope
     * @param ownerIds
     * @return
     */
    @Override
    public WaybillOwnerDTO dataDropDown(String ownerName, Boolean isScope, Integer[] ownerIds) {
        return waybillOwnerMapper.dataDropDown(ownerName,isScope,ownerIds);
    }

    /**
     * 查询经营许可证过期企业
     *
     * @param userExpiredDTO
     * @return
     */
    @Override
    public List<UserExpiredVO> ExpiredOwner(UserExpiredDTO userExpiredDTO) {
        LocalDate localDate = LocalDate.now();
        return waybillOwnerMapper.ExpiredOwner(userExpiredDTO,localDate+"");
    }

    /**
     * 根据时间段查询经营许可证过期企业
     *
     * @param userExpiredDTO
     * @return
     */
    @Override
    public List<UserExpiredVO> HistoryExpiredOwner(UserExpiredDTO userExpiredDTO) {
        return waybillOwnerMapper.HistoryExpiredOwner(userExpiredDTO);
    }

    /**
     * 根据多个企业id查询企业详细信息
     * @param ownerIds
     * @return
     */
    @Override
    public List<WaybillOwnerDetailsDTO> selectByOwerIds(List<Integer> ownerIds) {
        return waybillOwnerMapper.selectByOwerIds(ownerIds);
    }
}
