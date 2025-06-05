package xyz.bfdwdd.nshguildmanageb.base.authentication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.base.authentication.dto.request.LoginRequest;
import xyz.bfdwdd.nshguildmanageb.base.authentication.dto.response.LoginResponse;
import xyz.bfdwdd.nshguildmanageb.base.authentication.service.CustomUserDetailsService;
import xyz.bfdwdd.nshguildmanageb.base.authentication.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    @Operation(summary = "用户登录", description = "Authenticate a user and get JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "认证失败", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String token = jwtUtils.generateJwtToken(
                authentication.getName(), // userId
                ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername());

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setExpiresIn(jwtUtils.getJwtExpirationMs());
        response.setRefreshToken(""); // 可扩展为支持刷新 Token 的逻辑

        return ResponseEntity.ok(response);
    }
}