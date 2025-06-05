package xyz.bfdwdd.nshguildmanageb.base.log.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action", nullable = false)
    private String action; // 操作类型，如 "user_login", "guild_create"

    @Column(name = "user_id")
    private String userId; // 操作用户ID

    @Column(name = "guild_id")
    private Long guildId; // 关联帮会ID（可为空）

    @Column(name = "ip_address")
    private String ipAddress; // IP地址

    @Column(name = "description")
    private String description; // 描述信息

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp; // 时间戳

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}