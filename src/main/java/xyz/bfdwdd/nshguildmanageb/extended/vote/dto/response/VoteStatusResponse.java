package xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteStatusResponse {

    private Long voteId;
    private String actionType;
    private Integer currentVotes;
    private Integer requiredVotes;
    private String status;
    private String createdAt;
}