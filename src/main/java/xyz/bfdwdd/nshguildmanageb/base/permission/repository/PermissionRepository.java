package xyz.bfdwdd.nshguildmanageb.base.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.base.permission.entity.Permission;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}