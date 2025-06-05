package xyz.bfdwdd.nshguildmanageb.base.permission.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.bfdwdd.nshguildmanageb.base.permission.dto.request.CreatePermissionRequest;
import xyz.bfdwdd.nshguildmanageb.base.permission.service.PermissionService;

@Configuration
@RequiredArgsConstructor
public class PermissionConfig implements CommandLineRunner {

    private final PermissionService permissionService;

    @Bean
    public CommandLineRunner initPermissions() {
        return args -> {
            createPermissionIfNotExists("ADMIN", "系统最高管理员，拥有全部权限");
            createPermissionIfNotExists("SYSTEM_ADMIN", "管理服务功能、设置超级管理员");
            createPermissionIfNotExists("GUILD_SUPER_ADMIN", "创建和解散帮会，默认为帮主");
            createPermissionIfNotExists("GUILD_ADMIN", "管理帮会成员和邀请");
            createPermissionIfNotExists("MEMBER", "加入或退出帮会");
        };
    }

    private void createPermissionIfNotExists(String name, String description) {
        try {
            permissionService.createPermission(new CreatePermissionRequest(name, description));
        } catch (Exception ignored) {
            // 已存在则忽略
        }
    }

    @Override
    public void run(String... args) {
        // 初始化逻辑已在 initPermissions 中执行
    }
}