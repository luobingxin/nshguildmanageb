package xyz.bfdwdd.nshguildmanageb.base.user.dto.response;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private Set<String> roles;
    private boolean enabled;
    private String createdAt;
    private String updatedAt;
}