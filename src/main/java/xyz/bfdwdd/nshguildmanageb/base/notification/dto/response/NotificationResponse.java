package xyz.bfdwdd.nshguildmanageb.base.notification.dto.response;

import lombok.*;
import xyz.bfdwdd.nshguildmanageb.base.notification.entity.Notification;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String title;
    private String content;
    private String type;
    private String targetId;
    private String senderId;
    private String receiverId;
    private Boolean readStatus;
    private String timestamp;

    public static NotificationResponse fromEntity(Notification notification) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new NotificationResponse(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getType(),
                notification.getTargetId(),
                notification.getSenderId(),
                notification.getReceiverId(),
                notification.isReadStatus(),
                notification.getTimestamp().format(formatter)
        );
    }
}