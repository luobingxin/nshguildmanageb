package xyz.bfdwdd.nshguildmanageb.base.role.service;

import xyz.bfdwdd.nshguildmanageb.base.role.dto.request.CreateRoleRequest;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.request.UpdateRoleRequest;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse getRoleById(Long id);
    RoleResponse createRole(CreateRoleRequest request);
    RoleResponse updateRole(Long id, UpdateRoleRequest request);
    void deleteRole(Long id);
}