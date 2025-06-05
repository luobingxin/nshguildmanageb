package xyz.bfdwdd.nshguildmanageb.base.role.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.request.CreateRoleRequest;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.request.UpdateRoleRequest;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.response.RoleResponse;
import xyz.bfdwdd.nshguildmanageb.base.role.exception.RoleNotFoundException;
import xyz.bfdwdd.nshguildmanageb.base.role.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "获取所有角色", description = "Get all roles")
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @Operation(summary = "根据ID获取角色", description = "Get a role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "找到角色"),
            @ApiResponse(responseCode = "404", description = "未找到角色", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@Parameter(description = "角色的ID") @PathVariable Long id) throws RoleNotFoundException {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @Operation(summary = "创建新角色", description = "Create a new role")
    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody CreateRoleRequest request) {
        return ResponseEntity.status(201).body(roleService.createRole(request));
    }

    @Operation(summary = "更新角色信息", description = "Update an existing role")
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id, @RequestBody UpdateRoleRequest request) {
        return ResponseEntity.ok(roleService.updateRole(id, request));
    }

    @Operation(summary = "删除角色", description = "Delete a role by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}