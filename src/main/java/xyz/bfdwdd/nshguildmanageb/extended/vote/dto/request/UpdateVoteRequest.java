package xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVoteRequest {

    private String title;
    private String description;
    private LocalDateTime endTime;
}