package xyz.bfdwdd.nshguildmanageb.functional.guild.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildMemberResponse {

    private String userId;
    private LocalDateTime joinedAt;
    private String role;
}