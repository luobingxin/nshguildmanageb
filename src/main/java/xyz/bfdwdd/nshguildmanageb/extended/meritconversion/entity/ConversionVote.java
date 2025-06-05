package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_vote")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionVote {

    @Id
    private String id; // UUID 主键

    @Column(name = "initiator_id", nullable = false)
    private String initiatorId;

    @Column(name = "guild_id", nullable = false)
    private String guildId;

    @Column(name = "status", nullable = false)
    private String status; // PENDING, APPROVED, REJECTED

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}