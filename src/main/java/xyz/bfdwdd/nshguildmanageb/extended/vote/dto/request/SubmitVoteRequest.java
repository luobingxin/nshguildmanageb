package xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitVoteRequest {

    private Long voteId;
    private String userId;
    private Boolean approved;
}