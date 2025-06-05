package xyz.bfdwdd.nshguildmanageb.base.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.base.user.dto.request.RegisterRequest;
import xyz.bfdwdd.nshguildmanageb.base.user.dto.request.UpdateUserRequest;
import xyz.bfdwdd.nshguildmanageb.base.user.dto.response.UserResponse;
import xyz.bfdwdd.nshguildmanageb.base.user.exception.UserNotFoundException;
import xyz.bfdwdd.nshguildmanageb.base.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "获取所有用户", description = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "根据ID获取用户", description = "Get a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "找到用户"),
            @ApiResponse(responseCode = "404", description = "未找到用户", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@Parameter(description = "用户的ID") @PathVariable String id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "用户注册", description = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(201).body(userService.register(request));
    }

    @Operation(summary = "更新用户信息", description = "Update an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @Operation(summary = "删除用户", description = "Delete a user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}