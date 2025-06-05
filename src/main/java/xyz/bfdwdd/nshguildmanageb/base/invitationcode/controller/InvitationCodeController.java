package xyz.bfdwdd.nshguildmanageb.base.invitationcode.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.request.GenerateCodeRequest;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.response.InvitationCodeResponse;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.service.InvitationCodeService;

import java.util.Map;

@RestController
@RequestMapping("/api/invitations")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class InvitationCodeController {

    private final InvitationCodeService invitationCodeService;

    @Operation(summary = "生成邀请码", description = "Generate a new invitation code")
    @PostMapping("/generate")
    public ResponseEntity<InvitationCodeResponse> generateCode(@RequestBody GenerateCodeRequest request) {
        return ResponseEntity.status(201).body(invitationCodeService.generateCode(request));
    }

    @Operation(summary = "使用邀请码", description = "Use an invitation code")
    @GetMapping("/use/{code}")
    public ResponseEntity<Map<String, Boolean>> useCode(@Parameter(description = "邀请码") @PathVariable String code) {
        boolean result = invitationCodeService.useCode(code);
        return ResponseEntity.ok(Map.of("success", result));
    }

    @Operation(summary = "获取邀请码信息", description = "Get invitation code details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功找到邀请码"),
            @ApiResponse(responseCode = "404", description = "未找到邀请码", content = @Content)
    })
    @GetMapping("/{code}")
    public ResponseEntity<InvitationCodeResponse> getCodeInfo(@PathVariable String code) {
        // 可扩展为返回查询码对应的信息
        return ResponseEntity.notFound().build();
    }
}