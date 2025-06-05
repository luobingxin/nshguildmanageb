package xyz.bfdwdd.nshguildmanageb.base.notification.service;

import xyz.bfdwdd.nshguildmanageb.base.notification.dto.request.CreateNotificationRequest;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.request.UpdateNotificationRequest;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {
    List<NotificationResponse> getAllNotifications();
    NotificationResponse getNotificationById(Long id);
    NotificationResponse createNotification(CreateNotificationRequest request);
    NotificationResponse updateNotification(Long id, UpdateNotificationRequest request);
    void deleteNotification(Long id);
    List<NotificationResponse> getNotificationsByReceiverId(String receiverId);
    List<NotificationResponse> getUnreadNotificationsByReceiverId(String receiverId);
}