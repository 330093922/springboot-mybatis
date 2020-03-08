package com.thundersdata.backend.basic.dto;

import com.thundersdata.backend.basic.model.AuthResource;
import lombok.Data;

import java.util.List;

@Data
public class AuthRoleDetailDTO extends AuthResource {

    /**
     * 子节点
     */
    private List<AuthResourceDTO> children;
}
