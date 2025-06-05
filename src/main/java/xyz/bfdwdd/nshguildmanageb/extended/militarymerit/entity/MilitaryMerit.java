package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "military_merits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MilitaryMerit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "guild_id", nullable = false)
    private Long guildId;

    @Column(name = "merit_points", nullable = false)
    private Integer meritPoints = 0;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated = LocalDateTime.now();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}