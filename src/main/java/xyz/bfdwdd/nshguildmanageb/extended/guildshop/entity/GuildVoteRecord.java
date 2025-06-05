package xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "GuildShopVoteRecord")
@Table(name = "guild_vote_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildVoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vote_id", nullable = false)
    private String voteId;

    @Column(name = "voter_id", nullable = false)
    private String voterId;

    @Column(name = "approved", nullable = false)
    private Boolean approved;

    @Column(name = "vote_time", nullable = false)
    private LocalDateTime voteTime;
}