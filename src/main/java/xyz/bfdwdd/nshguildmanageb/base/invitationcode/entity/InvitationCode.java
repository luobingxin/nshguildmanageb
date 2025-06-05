package xyz.bfdwdd.nshguildmanageb.base.invitationcode.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "invitation_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCode {
    @Id
    private String id; // UUID 主键

    @Column(name = "code", nullable = false, unique = true)
    private String code; // 加密邀请码

    @Column(name = "query_code", nullable = false, unique = true)
    private String queryCode; // 查询码

    @Column(name = "creator_id", nullable = false)
    private String creatorId; // 创建者ID

    @Column(name = "guild_id")
    private Long guildId; // 关联帮会ID（可为空）

    @Column(name = "max_uses")
    private Integer maxUses; // 最大使用次数

    @Column(name = "current_uses")
    private Integer currentUses = 0; // 当前已使用次数

    @Column(name = "expires_at")
    private LocalDateTime expiresAt; // 过期时间

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}