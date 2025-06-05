package xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGuildRequest {

    private String name;
    private String description;
    private String ownerId;
    private Integer maxMembers;
}