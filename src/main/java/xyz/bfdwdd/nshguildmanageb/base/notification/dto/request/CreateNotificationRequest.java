package xyz.bfdwdd.nshguildmanageb.base.notification.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationRequest {
    private String title;
    private String content;
    private String type;
    private String targetId;
    private String senderId;
    private String receiverId;
}