package xyz.bfdwdd.nshguildmanageb.base.role.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.bfdwdd.nshguildmanageb.base.role.dto.request.CreateRoleRequest;
import xyz.bfdwdd.nshguildmanageb.base.role.service.RoleService;

@Configuration
@RequiredArgsConstructor
public class RoleConfig implements CommandLineRunner {

    private final RoleService roleService;

    @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            createRoleIfNotExists("ADMIN", "系统最高管理员，拥有全部权限", 5);
            createRoleIfNotExists("SYSTEM_ADMIN", "管理服务功能、设置超级管理员", 4);
            createRoleIfNotExists("GUILD_SUPER_ADMIN", "创建和解散帮会，默认为帮主", 3);
            createRoleIfNotExists("GUILD_ADMIN", "管理帮会成员和邀请", 2);
            createRoleIfNotExists("MEMBER", "加入或退出帮会", 1);
        };
    }

    private void createRoleIfNotExists(String name, String description, int hierarchy) {
        try {
            roleService.createRole(new CreateRoleRequest(name, description, hierarchy));
        } catch (Exception ignored) {
            // 已存在则忽略
        }
    }

    @Override
    public void run(String... args) {
        // 初始化逻辑已在 initRoles 中执行
    }
}