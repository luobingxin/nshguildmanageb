package xyz.bfdwdd.nshguildmanageb.base.user.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private String username;
    private String password;
    private String email;
    private Boolean enabled;
}