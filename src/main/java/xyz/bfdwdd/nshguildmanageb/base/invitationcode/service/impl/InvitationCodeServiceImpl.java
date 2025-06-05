package xyz.bfdwdd.nshguildmanageb.base.invitationcode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.request.GenerateCodeRequest;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.response.InvitationCodeResponse;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.entity.InvitationCode;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.exception.InvalidInvitationCodeException;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.exception.InvitationCodeAlreadyUsedException;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.exception.InvitationCodeExpiredException;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.exception.*;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.repository.InvitationCodeRepository;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.config.CodeGeneratorConfig;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.service.InvitationCodeService;
import xyz.bfdwdd.nshguildmanageb.base.user.entity.User;
import xyz.bfdwdd.nshguildmanageb.base.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class InvitationCodeServiceImpl implements InvitationCodeService {

    private final InvitationCodeRepository invitationCodeRepository;
    private final UserRepository userRepository;
    private final CodeGeneratorConfig codeGeneratorConfig;

    @Override
    public InvitationCodeResponse generateCode(GenerateCodeRequest request) {
        // 权限验证
        User creator = userRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        validatePermission(creator, request);

        // 生成邀请码和查询码
        String baseCode = codeGeneratorConfig.generateBaseCode();
        String encodedCode = Base64.getUrlEncoder().encodeToString(baseCode.getBytes());
        String queryCode = codeGeneratorConfig.generateQueryCode();

        // 创建邀请码对象
        InvitationCode code = new InvitationCode();
        code.setCode(encodedCode);
        code.setQueryCode(queryCode);
        code.setCreatorId(request.getCreatorId());
        code.setGuildId(request.getGuildId());
        code.setMaxUses(request.getMaxUses());
        code.setExpiresAt(LocalDateTime.now().plusHours(request.getDurationHours()));

        InvitationCode savedCode = invitationCodeRepository.save(code);
        return InvitationCodeResponse.fromEntity(savedCode);
    }

    @Override
    public boolean useCode(String codeStr) {
        InvitationCode code = invitationCodeRepository.findByCode(codeStr)
                .orElseThrow(() -> new InvalidInvitationCodeException("Invalid invitation code"));

        if (code.getCurrentUses() >= code.getMaxUses()) {
            throw new InvitationCodeAlreadyUsedException("Code has reached maximum uses");
        }

        if (code.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new InvitationCodeExpiredException("Code has expired");
        }

        code.setCurrentUses(code.getCurrentUses() + 1);
        invitationCodeRepository.save(code);
        return true;
    }

    private void validatePermission(User user, GenerateCodeRequest request) {
        // 示例：根据用户角色设置最大允许生成人数
        long allowedMaxUses = 5; // 默认值

        if (hasRole(user, "ADMIN")) {
            allowedMaxUses = 999999; // 开发者无上限
        } else if (hasRole(user, "SYSTEM_ADMIN")) {
            allowedMaxUses = 100000; // 系统管理员
        } else if (hasRole(user, "GUILD_SUPER_ADMIN")) {
            allowedMaxUses = 30; // 超级管理员
        } else if (hasRole(user, "GUILD_LEADER")) {
            allowedMaxUses = 15; // 帮主
        } else if (hasRole(user, "GUILD_ADMIN")) {
            allowedMaxUses = 5; // 帮会管理员
        }

        if (request.getMaxUses() > allowedMaxUses) {
            throw new RuntimeException("Exceeds your maximum allowed uses per code: " + allowedMaxUses);
        }
    }

    private boolean hasRole(User user, String roleName) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }
}