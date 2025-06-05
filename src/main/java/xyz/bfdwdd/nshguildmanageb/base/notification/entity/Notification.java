package xyz.bfdwdd.nshguildmanageb.base.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; // 通知标题

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content; // 通知内容

    @Column(name = "type", nullable = false)
    private String type; // 通知类型：SYSTEM, GUILD_ANNOUNCEMENT, PRIVATE_MESSAGE 等

    @Column(name = "target_id") // 目标ID，如 guild_id 或 user_id
    private String targetId;

    @Column(name = "sender_id", nullable = false) // 发送者ID
    private String senderId;

    @Column(name = "receiver_id", nullable = false) // 接收者ID
    private String receiverId;

    @Column(name = "read_status", nullable = false)
    private boolean readStatus; // 是否已读

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp; // 发送时间

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}