package xyz.bfdwdd.nshguildmanageb.base.permission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.request.CreatePermissionRequest;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.request.UpdatePermissionRequest;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.response.PermissionResponse;
import xyz.bfdwdd.nshguildmanageb.base.permission.entity.Permission;
import xyz.bfdwdd.nshguildmanageb.base.permission.exception.PermissionNotFoundException;
import xyz.bfdwdd.nshguildmanageb.base.permission.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @Operation(summary = "获取所有权限", description = "Get all permissions")
    @GetMapping
    public ResponseEntity<List<PermissionResponse>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @Operation(summary = "根据ID获取权限", description = "Get a permission by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "找到权限"),
            @ApiResponse(responseCode = "404", description = "未找到权限", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponse> getPermissionById(@Parameter(description = "权限的ID") @PathVariable Long id) throws PermissionNotFoundException {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @Operation(summary = "创建新权限", description = "Create a new permission")
    @PostMapping
    public ResponseEntity<PermissionResponse> createPermission(@RequestBody CreatePermissionRequest request) {
        return ResponseEntity.status(201).body(permissionService.createPermission(request));
    }

    @Operation(summary = "更新权限信息", description = "Update an existing permission")
    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponse> updatePermission(@PathVariable Long id, @RequestBody UpdatePermissionRequest request) {
        return ResponseEntity.ok(permissionService.updatePermission(id, request));
    }

    @Operation(summary = "删除权限", description = "Delete a permission by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

    private PermissionResponse toResponse(Permission permission) {
        return new PermissionResponse(
                permission.getId(),
                permission.getName(),
                permission.getDescription()
        );
    }
}