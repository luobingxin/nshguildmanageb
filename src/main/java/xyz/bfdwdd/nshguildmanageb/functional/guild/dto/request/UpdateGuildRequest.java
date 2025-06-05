package xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGuildRequest {

    private String name;
    private String description;
    private Integer maxMembers;
}