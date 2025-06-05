package xyz.bfdwdd.nshguildmanageb.base.role.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.request.CreateRoleRequest;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.request.UpdateRoleRequest;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.response.RoleResponse;
import xyz.bfdwdd.nshguildmanageb.base.role.entity.Role;
import xyz.bfdwdd.nshguildmanageb.base.role.exception.RoleAlreadyExistsException;
import xyz.bfdwdd.nshguildmanageb.base.role.exception.RoleNotFoundException;
import xyz.bfdwdd.nshguildmanageb.base.role.repository.RoleRepository;
import xyz.bfdwdd.nshguildmanageb.base.role.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + id));
    }

    @Override
    public RoleResponse createRole(CreateRoleRequest request) {
        if (roleRepository.findByName(request.getName()).isPresent()) {
            throw new RoleAlreadyExistsException("Role already exists: " + request.getName());
        }

        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setHierarchy(request.getHierarchy());

        return toResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse updateRole(Long id, UpdateRoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + id));

        if (!role.getName().equals(request.getName()) &&
                roleRepository.findByName(request.getName()).isPresent()) {
            throw new RoleAlreadyExistsException("Role name already exists: " + request.getName());
        }

        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setHierarchy(request.getHierarchy());

        return toResponse(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("Role not found with ID: " + id);
        }
        roleRepository.deleteById(id);
    }

    private RoleResponse toResponse(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getHierarchy()
        );
    }
}