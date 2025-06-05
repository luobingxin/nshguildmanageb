package xyz.bfdwdd.nshguildmanageb.base.role.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private Integer hierarchy;
}