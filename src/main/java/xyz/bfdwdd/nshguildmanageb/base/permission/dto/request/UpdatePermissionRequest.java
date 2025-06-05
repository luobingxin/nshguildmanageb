package xyz.bfdwdd.nshguildmanageb.base.permission.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePermissionRequest {
    private String name;
    private String description;
}