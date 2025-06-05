package xyz.bfdwdd.nshguildmanageb.functional.guild.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import xyz.bfdwdd.nshguildmanageb.functional.constant.enums.RoleHierarchy;

import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.ForeignKey;

import java.time.LocalDateTime;

@Entity
@Table(name = "guild_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleHierarchy role = RoleHierarchy.MEMBER;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "guild_id", foreignKey = @ForeignKey(name = "FK_GUILD_MEMBERS_GUILDS"))
    private Guild guild;
}