package xyz.bfdwdd.nshguildmanageb.base.user.service;

import xyz.bfdwdd.nshguildmanageb.base.user.dto.request.RegisterRequest;
import xyz.bfdwdd.nshguildmanageb.base.user.dto.request.UpdateUserRequest;
import xyz.bfdwdd.nshguildmanageb.base.user.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(String id);
    UserResponse register(RegisterRequest request);
    UserResponse updateUser(String id, UpdateUserRequest request);
    void deleteUser(String id);
}