package xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDetailResponse {

    private Long voteId;
    private Long guildId;
    private String title;
    private String description;
    private String voteType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer requiredApprovals;
    private String status;
}