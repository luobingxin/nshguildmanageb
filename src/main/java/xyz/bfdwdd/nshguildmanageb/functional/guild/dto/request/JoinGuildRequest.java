package xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinGuildRequest {

    private String userId;
    private String invitationCode;

    public Long getGuildId() {
        return null;
    }
}