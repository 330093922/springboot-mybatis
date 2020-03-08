package com.thundersdata.backend.basic.dto;

import com.thundersdata.backend.basic.model.AuthResource;
import lombok.Data;

import java.util.List;

@Data
public class AuthResourceDTO extends AuthResource {

    /**
     * 子节点
     */
    private List<AuthResourceDTO> children;

    /**
     * 是否有权限
     */
    private Boolean isTrue;

    /**
     * 角色id
     */
    private Integer roleId;

}
