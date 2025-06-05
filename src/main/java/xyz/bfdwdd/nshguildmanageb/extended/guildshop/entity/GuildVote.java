package xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "GuildShopGuildVote")
@Table(name = "guild_vote")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildVote {

    @Id
    private String id;

    @Column(name = "initiator_id", nullable = false)
    private String initiatorId;

    @Column(name = "action_type", nullable = false)
    private String actionType;

    @Column(name = "target_id", nullable = false)
    private String targetId;

    @Column(name = "guild_id", nullable = false)
    private String guildId;

    @Column(name = "status", nullable = false)
    private String status; // PENDING, APPROVED, REJECTED

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}