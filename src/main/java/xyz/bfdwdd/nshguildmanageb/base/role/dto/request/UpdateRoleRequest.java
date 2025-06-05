package xyz.bfdwdd.nshguildmanageb.base.role.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleRequest {
    private String name;
    private String description;
    private Integer hierarchy;
}