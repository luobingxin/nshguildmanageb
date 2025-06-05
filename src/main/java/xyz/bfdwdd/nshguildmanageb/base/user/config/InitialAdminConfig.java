package xyz.bfdwdd.nshguildmanageb.base.user.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import xyz.bfdwdd.nshguildmanageb.base.user.dto.request.RegisterRequest;
import xyz.bfdwdd.nshguildmanageb.base.user.service.UserService;

@Configuration
@RequiredArgsConstructor
public class InitialAdminConfig {

    private final UserService userService;

    @PostConstruct
    public void initAdminUser() {
        RegisterRequest adminRequest = new RegisterRequest();
        adminRequest.setUsername("admin");
        adminRequest.setPassword("admin123");
        adminRequest.setEmail("admin@example.com");

        try {
            userService.register(adminRequest);
        } catch (Exception ignored) {
            // 管理员已存在则忽略
        }

        RegisterRequest sysAdminRequest = new RegisterRequest();
        sysAdminRequest.setUsername("sysadmin");
        sysAdminRequest.setPassword("sysadmin123");
        sysAdminRequest.setEmail("sysadmin@example.com");

        try {
            userService.register(sysAdminRequest);
        } catch (Exception ignored) {
            // 系统管理员已存在则忽略
        }
    }
}