package xyz.bfdwdd.nshguildmanageb.base.notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.request.CreateNotificationRequest;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.request.UpdateNotificationRequest;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.response.NotificationResponse;
import xyz.bfdwdd.nshguildmanageb.base.notification.entity.Notification;
import xyz.bfdwdd.nshguildmanageb.base.notification.exception.NotificationNotFoundException;
import xyz.bfdwdd.nshguildmanageb.base.notification.repository.NotificationRepository;
import xyz.bfdwdd.nshguildmanageb.base.notification.service.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(NotificationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .map(NotificationResponse::fromEntity)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + id));
    }

    @Override
    public NotificationResponse createNotification(CreateNotificationRequest request) {
        Notification notification = new Notification();
        notification.setTitle(request.getTitle());
        notification.setContent(request.getContent());
        notification.setType(request.getType());
        notification.setTargetId(request.getTargetId());
        notification.setSenderId(request.getSenderId());
        notification.setReceiverId(request.getReceiverId());
        notification.setReadStatus(false);

        return NotificationResponse.fromEntity(notificationRepository.save(notification));
    }

    @Override
    public NotificationResponse updateNotification(Long id, UpdateNotificationRequest request) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + id));

        if (request.getTitle() != null) notification.setTitle(request.getTitle());
        if (request.getContent() != null) notification.setContent(request.getContent());
        if (request.getType() != null) notification.setType(request.getType());
        if (request.getTargetId() != null) notification.setTargetId(request.getTargetId());
        if (request.getSenderId() != null) notification.setSenderId(request.getSenderId());
        if (request.getReceiverId() != null) notification.setReceiverId(request.getReceiverId());
        if (request.getReadStatus() != null) notification.setReadStatus(request.getReadStatus());

        return NotificationResponse.fromEntity(notificationRepository.save(notification));
    }

    @Override
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotificationNotFoundException("Notification not found with ID: " + id);
        }
        notificationRepository.deleteById(id);
    }

    @Override
    public List<NotificationResponse> getNotificationsByReceiverId(String receiverId) {
        return notificationRepository.findByReceiverId(receiverId).stream()
                .map(NotificationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponse> getUnreadNotificationsByReceiverId(String receiverId) {
        return notificationRepository.findByReceiverIdAndReadStatus(receiverId, false).stream()
                .map(NotificationResponse::fromEntity)
                .collect(Collectors.toList());
    }
}