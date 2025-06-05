package xyz.bfdwdd.nshguildmanageb.base.permission.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.request.CreatePermissionRequest;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.request.UpdatePermissionRequest;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.response.PermissionResponse;
import xyz.bfdwdd.nshguildmanageb.base.permission.entity.Permission;
import xyz.bfdwdd.nshguildmanageb.base.permission.exception.PermissionAlreadyExistsException;
import xyz.bfdwdd.nshguildmanageb.base.permission.exception.PermissionNotFoundException;
import xyz.bfdwdd.nshguildmanageb.base.permission.repository.PermissionRepository;
import xyz.bfdwdd.nshguildmanageb.base.permission.service.PermissionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionResponse getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new PermissionNotFoundException("ConversionRate not found with ID: " + id));
    }

    @Override
    public PermissionResponse createPermission(CreatePermissionRequest request) {
        if (permissionRepository.findByName(request.getName()).isPresent()) {
            throw new PermissionAlreadyExistsException("ConversionRate already exists: " + request.getName());
        }

        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setDescription(request.getDescription());

        return toResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse updatePermission(Long id, UpdatePermissionRequest request) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException("ConversionRate not found with ID: " + id));

        if (!permission.getName().equals(request.getName()) &&
                permissionRepository.findByName(request.getName()).isPresent()) {
            throw new PermissionAlreadyExistsException("ConversionRate name already exists: " + request.getName());
        }

        permission.setName(request.getName());
        permission.setDescription(request.getDescription());

        return toResponse(permissionRepository.save(permission));
    }

    @Override
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new PermissionNotFoundException("ConversionRate not found with ID: " + id);
        }
        permissionRepository.deleteById(id);
    }

    private PermissionResponse toResponse(Permission permission) {
        return new PermissionResponse(
                permission.getId(),
                permission.getName(),
                permission.getDescription()
        );
    }
}