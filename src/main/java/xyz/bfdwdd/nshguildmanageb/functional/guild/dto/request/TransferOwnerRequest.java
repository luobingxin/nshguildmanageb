package xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferOwnerRequest {

    private String currentOwnerId;
    private String newOwnerId;

    public Long getGuildId() {
        return null;
    }
}