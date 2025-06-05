package xyz.bfdwdd.nshguildmanageb.base.permission.service;

import xyz.bfdwdd.nshguildmanageb.base.permission.dto.request.CreatePermissionRequest;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.request.UpdatePermissionRequest;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getAllPermissions();
    PermissionResponse getPermissionById(Long id);
    PermissionResponse createPermission(CreatePermissionRequest request);
    PermissionResponse updatePermission(Long id, UpdatePermissionRequest request);
    void deletePermission(Long id);
}