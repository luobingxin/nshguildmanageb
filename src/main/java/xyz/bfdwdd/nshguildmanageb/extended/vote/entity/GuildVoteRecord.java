package xyz.bfdwdd.nshguildmanageb.extended.vote.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "VoteModuleVoteRecord")
@Table(name = "guild_vote_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildVoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vote_id", nullable = false)
    private Long voteId;

    @Column(name = "voter_id", nullable = false, length = 36)
    private String voterId;

    @Column(name = "approved", nullable = false)
    private Boolean approved;

    @Column(name = "voted_at")
    private LocalDateTime votedAt = LocalDateTime.now();
}