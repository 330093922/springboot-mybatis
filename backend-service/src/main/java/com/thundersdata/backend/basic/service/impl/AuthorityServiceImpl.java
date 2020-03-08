package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.*;
import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.dto.AuthRoleDataDTO;
import com.thundersdata.backend.basic.dto.PermissionDataDTO;
import com.thundersdata.backend.basic.model.AuthResource;
import com.thundersdata.backend.basic.model.AuthRole;
import com.thundersdata.backend.basic.service.AuthorityService;
import com.thundersdata.backend.basic.vo.RoleAddDataVO;
import com.thundersdata.backend.basic.vo.RoleAddMenuVO;
import com.thundersdata.backend.basic.vo.UserAddRoleVO;
import com.thundersdata.backend.common.model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.thundersdata.backend.common.constant.SymbolConstants.ONE;
import static com.thundersdata.backend.common.constant.SymbolConstants.ZERO;
import static com.thundersdata.backend.common.model.ResultCode.*;

/**
 * @author paris
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    private static Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @Autowired
    private AuthRoleDataMapper authRoleDataMapper;

    @Autowired
    private AuthRoleMapper authRoleMapper;

    @Autowired
    private AuthRoleResourceMapper authRoleResourceMapper;

    @Autowired
    private AuthRoleUserMapper authRoleUserMapper;

    @Autowired
    private AuthResourceMapper authResourceMapper;

    /**
     * 查询角色菜单树
     *
     * @param roleId
     * @return
     */
    @Override
    public List<AuthResourceDTO> getRoleMenu(Integer roleId) {

        Assert.notNull(roleId, "角色不能为空！");
        // 菜单列表
        List<AuthResourceDTO> authResourceList = authRoleResourceMapper.getMenuList();
        // 权限列表
        List<Integer> resourceList = authRoleResourceMapper.selectByRole(roleId);
        List<AuthResourceDTO> treeList = new ArrayList<>();

        for (AuthResourceDTO authResourceDTO : authResourceList) {
            authResourceDTO.setIsTrue(resourceList.contains(authResourceDTO.getId()));
            authResourceDTO.setRoleId(roleId);
            if (ZERO.equals(authResourceDTO.getParentId())) {
                convert2Tree(authResourceList, authResourceDTO);
                treeList.add(authResourceDTO);
            }
        }

        return treeList;
    }

    /**
     * s
     * 组装树形菜单
     *
     * @param authResourceList
     * @param parentNode
     * @return
     */
    private void convert2Tree(List<AuthResourceDTO> authResourceList,
                              AuthResourceDTO parentNode) {

        parentNode.setChildren(new LinkedList<>());

        for (AuthResourceDTO authResourceDTO : authResourceList) {
            if (authResourceDTO.getParentId().equals(parentNode.getId())) {
                parentNode.getChildren().add(authResourceDTO);

                convert2Tree(authResourceList, authResourceDTO);
            }
        }

    }

    /**
     * 角色绑定菜单权限
     */
    @Override
    public Integer roleAddMenu(RoleAddMenuVO roleAddMenuVO) {

        Assert.notNull(roleAddMenuVO.getRoleId(), "角色id不能为空");
        Assert.notNull(roleAddMenuVO.getMenuList(), "请选择菜单权限");

        authRoleResourceMapper.deleteByRole(roleAddMenuVO.getRoleId());

        return authRoleResourceMapper.replaceIntoByRole(roleAddMenuVO.getRoleId(), roleAddMenuVO.getMenuList());
    }

    /**
     * 查询角色数据范围
     *
     * @param roleId
     * @return
     */
    @Override
    public List<AuthRoleDataDTO> getRoleData(Integer roleId, Integer dataType) {
        Assert.notNull(roleId, "角色id不能为空");
        Assert.notNull(dataType, "数据类型不能为空！");

        List<AuthRoleDataDTO> authRoleDataList = new ArrayList<>();

        AuthRole authRole = authRoleMapper.selectByPrimaryKey(roleId);
        Assert.notNull(authRole, "角色不存在!");
        if (!ZERO.equals(authRole.getScope())) {
            AuthRoleDataDTO authRoleDataDTO = new AuthRoleDataDTO();
            authRoleDataDTO.setIsScope(false);
            authRoleDataList.add(authRoleDataDTO);
        } else {
            authRoleDataList = authRoleDataMapper.selectByRole(roleId, dataType);
        }

        return authRoleDataList;
    }

    /**
     * 角色绑定数据权限
     */
    @Override
    public Integer roleAddData(RoleAddDataVO roleAddDataVO) {
        Assert.notNull(roleAddDataVO.getRoleId(), "角色id不能为空");
        Assert.notNull(roleAddDataVO.getDataList(), "数据列表不能为空");
        Assert.notNull(roleAddDataVO.getScope(), "数据范围不能为空");
        Assert.notNull(roleAddDataVO.getDataType(), "请选择数据类型");

        authRoleMapper.updateScope(roleAddDataVO.getRoleId(), roleAddDataVO.getScope());
        authRoleDataMapper.deleteByRole(roleAddDataVO.getRoleId());

        if (!ONE.equals(roleAddDataVO.getScope())) {
            return authRoleDataMapper.replaceIntoByRole(roleAddDataVO.getRoleId(), roleAddDataVO.getDataType(), roleAddDataVO.getDataList());
        }

        return ZERO;
    }

    /**
     * 用户绑定角色
     */
    @Override
    public Integer userAddRole(UserAddRoleVO userAddRoleVO) {
        Assert.notNull(userAddRoleVO.getPersonId(), "要更新权限的用户不能为空！");
        Assert.notNull(userAddRoleVO.getDataList(), "请选择角色！");
        authRoleUserMapper.deleteByUser(userAddRoleVO.getPersonId());

        return authRoleUserMapper.replaceIntoByUser(userAddRoleVO.getPersonId(), userAddRoleVO.getDataList());
    }

    /**
     * 查询用户所有角色
     *
     * @param personId 人员id
     * @return
     */
    @Override
    public List<AuthRoleDTO> getUserRole(Integer personId) {

        return authRoleUserMapper.selectByUser(personId);
    }

    /**
     * 查询用户所权限
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<AuthResourceDTO> getUserRoleDetail(Integer userId) {

        List<AuthResourceDTO> authResourceList = authRoleUserMapper.selectResourceByUser(userId);
        Map<Integer, AuthResourceDTO> resourceMap = getResourceMap();

        List<Integer> idList = new ArrayList<>();

        for (AuthResourceDTO authResourceDTO : authResourceList) {
            if (authResourceDTO != null) {
                idList.addAll(getResourceIdList(authResourceDTO, resourceMap));
            }
        }

        authResourceList = authResourceMapper.selectByIdList(idList);
        List<AuthResourceDTO> treeList = new ArrayList<>();
        for (AuthResourceDTO authResourceDTO : authResourceList) {
            authResourceDTO.setIsTrue(true);
            if (ZERO.equals(authResourceDTO.getParentId())) {
                convert2Tree(authResourceList, authResourceDTO);
                treeList.add(authResourceDTO);
            }
        }

        return treeList;
    }


    /**
     * 递归父节点
     *
     * @param authResourceDTO
     * @param resourceMap
     * @return
     */
    private List<Integer> getResourceIdList(AuthResourceDTO authResourceDTO, Map<Integer, AuthResourceDTO> resourceMap) {
        List<Integer> resourceList = new ArrayList<>();
        resourceList.add(authResourceDTO.getId());
        if (!ZERO.equals(authResourceDTO.getParentId())) {
            resourceList.add(authResourceDTO.getParentId());
            resourceList.addAll(getResourceIdList(resourceMap.get(authResourceDTO.getParentId()), resourceMap));
        }

        return resourceList;
    }


    /**
     * 构造资源map
     *
     * @return
     */
    private Map<Integer, AuthResourceDTO> getResourceMap() {

        Map<Integer, AuthResourceDTO> resourceMap = new HashMap<>();
        List<AuthResourceDTO> authResourceList = authResourceMapper.selectByMap();

        for (AuthResourceDTO authResourceDTO : authResourceList) {
            resourceMap.put(authResourceDTO.getId(), authResourceDTO);
        }

        return resourceMap;
    }

    /**
     * 获取接口权限
     *
     * @param apiUrl 接口url
     * @param userId 用户id
     */
    @Override
    public ResponseData getPermissionByUrl(String apiUrl,
                                           Integer userId) {
        if (userId.toString().isEmpty()) {
            return new ResponseData(PARAMETER_MISSING_ERROR, null, "用户错误", true);
        } else if (apiUrl.isEmpty()) {
            return new ResponseData(PARAMETER_MISSING_ERROR, null, "请求地址错误", true);
        }

        List<AuthResource> permissionResources;
        permissionResources = authRoleResourceMapper.getPermissionByUrlWithValue(userId, apiUrl);

        if (CollectionUtils.isEmpty(permissionResources)) {
            logger.error(String.format("用户:%s --- 请求地址:%s --- 抱歉，您暂无访问权限！", userId, apiUrl));
            return new ResponseData(NOT_AUTHORIZED, null, "抱歉，您暂无访问权限！", true);
        }

        return new ResponseData(SUCCESS, true, "权限通过", true);
    }

    /**
     * 获取数据范围
     *
     * @param userId 用户id
     */
    @Override
    public PermissionDataDTO getPermissionData(Integer userId) {
        Assert.notNull(userId, "用户错误");

        PermissionDataDTO permissionDataDTO = new PermissionDataDTO();
        permissionDataDTO.setDataList(new ArrayList<>());
        List<AuthRoleDTO> userRoleList = getUserRole(userId);
        List<Integer> roleIds = new ArrayList<>();

        for (AuthRoleDTO authRole : userRoleList) {
            roleIds.add(authRole.getId());
            if (ONE.equals(authRole.getScope())) {
                permissionDataDTO.setIsScope(false);
            }
        }

        boolean isUni = false;
        if (!roleIds.isEmpty()) {
            isUni = true;
        }

        if (permissionDataDTO.getIsScope()) {
            permissionDataDTO.setDataList(authRoleDataMapper.selectByUser(isUni, userId, roleIds));
        }

        return permissionDataDTO;
    }
}
