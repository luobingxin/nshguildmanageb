package xyz.bfdwdd.nshguildmanageb.functional.guild.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private String ownerId;
    private Integer maxMembers;
    private String status;
    private List<GuildMemberResponse> members;
}