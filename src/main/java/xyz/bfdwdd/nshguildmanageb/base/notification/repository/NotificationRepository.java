package xyz.bfdwdd.nshguildmanageb.base.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.base.notification.entity.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiverId(String receiverId);
    List<Notification> findBySenderId(String senderId);
    List<Notification> findByType(String type);

    List<Notification> findByReceiverIdAndReadStatus(String receiverId, Boolean readStatus);
}