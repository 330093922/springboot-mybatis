package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.date.DateUtil;
import com.thundersdata.backend.basic.dao.AuthRoleMapper;
import com.thundersdata.backend.basic.dao.WaybillUserMapper;
import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.model.AuthRole;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.service.AuthRoleService;
import com.thundersdata.backend.basic.service.AuthService;
import com.thundersdata.backend.basic.utils.OrderUtil;
import com.thundersdata.backend.basic.vo.AuthRoleVO;
import com.thundersdata.backend.common.utils.ArrayUtils;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_MS_PATTERN;

/**
 * 用户角色服务层实现类
 * @author wanghaibo
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleMapper authRoleMapper;

    /**
     * 新增用户角色信息
     * @param authRoleVO
     * @return
     */
    @Override
    public int insertAuthRole(AuthRoleVO authRoleVO) {
        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(authRoleVO, authRole);
        int i = authRoleMapper.insertSelective(authRole);
        Assert.isTrue(i > 0, "添加用户角色失败");

        return i;
    }

    /**
     * 修改用户角色信息
     * @param authRoleVO
     * @return
     */
    @Override
    public int updateAuthRole(AuthRoleVO authRoleVO) {
        AuthRole authRole = authRoleMapper.selectByPrimaryKey(authRoleVO.getId());
        Assert.notNull(authRole, "用户角色不存在!");
        BeanUtils.copyProperties(authRoleVO, authRole);
        authRole.setUpdatedAt(new Date());
        int i = authRoleMapper.updateByPrimaryKeySelective(authRole);
        Assert.isTrue(i > 0, "修改用户角色失败");

        return i;
    }

    /**
     * 根据主键查询用户角色信息
     * @param id
     * @return
     */
    @Override
    public AuthRoleDTO selectAuthRoleVO(Integer id) {
        AuthRole authRole = authRoleMapper.selectByPrimaryKey(id);
        Assert.notNull(authRole, "用户角色不存在!");
        AuthRoleDTO authRoleDTO = new AuthRoleDTO();
        BeanUtils.copyProperties(authRole, authRoleDTO);

        return authRoleDTO;
    }

    /**
     * 删除用户角色信息
     * @param id
     * @return
     */
    @Override
    public int deleteAuthRole(Integer id) {
        AuthRole authRole = authRoleMapper.selectByPrimaryKey(id);
        Assert.notNull(authRole, "用户角色不存在!");
        Assert.isTrue(0 != authRole.getIsAdmin(), "用户角色不允许删除");
        AuthRole authRoleForUpdate = new AuthRole();
        authRoleForUpdate.setId(id);
        authRoleForUpdate.setDeleted((byte) 1);
        int i = authRoleMapper.updateByPrimaryKeySelective(authRoleForUpdate);

        return i;
    }

    /**
     * 批量删除用户角色
     * @param ids
     * @return
     */
    @Override
    public int deleteAuthRoleBatch(String ids) {
        Integer[] idArray = ArrayUtils.strToArray(ids, ",");
        int i = authRoleMapper.deleteAuthRoleBatch("(" + ids + ")");

        return i;
    }

    /**
     * 分页查询用户角色信息
     * @param page
     * @param pageSize
     * @param roleName
     * @param description
     * @return
     */
    @Override
    public Pagination<AuthRoleDTO> selectAuthRole(Integer page, Integer pageSize, String roleName, String description) {
        PageParam pageparam = PageUtils.init(page, pageSize);
        int total = authRoleMapper.countAuthRole(OrderUtil.getLikeStr(roleName), OrderUtil.getLikeStr(description));
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }
        List<AuthRoleDTO> list = authRoleMapper.selectAuthRole(pageparam, OrderUtil.getLikeStr(roleName), OrderUtil.getLikeStr(description));

        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, list);
    }


}
