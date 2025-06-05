package xyz.bfdwdd.nshguildmanageb.base.permission.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private Long id;
    private String name;
    private String description;
}