package xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.response;

import lombok.*;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.entity.InvitationCode;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCodeResponse {
    private String code;
    private String queryCode;
    private String creatorId;
    private Long guildId;
    private Integer maxUses;
    private Integer currentUses;
    private String expiresAt;
    private String createdAt;
    private String updatedAt;

    public static InvitationCodeResponse fromEntity(InvitationCode code) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new InvitationCodeResponse(
                code.getCode(),
                code.getQueryCode(),
                code.getCreatorId(),
                code.getGuildId(),
                code.getMaxUses(),
                code.getCurrentUses(),
                code.getExpiresAt().format(formatter),
                code.getCreatedAt().format(formatter),
                code.getUpdatedAt().format(formatter)
        );
    }
}