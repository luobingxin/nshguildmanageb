package xyz.bfdwdd.nshguildmanageb.base.permission.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermissionRequest {
    private String name;
    private String description;
}