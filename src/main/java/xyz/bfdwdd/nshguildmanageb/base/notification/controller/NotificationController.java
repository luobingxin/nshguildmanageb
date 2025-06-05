package xyz.bfdwdd.nshguildmanageb.base.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.request.CreateNotificationRequest;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.request.UpdateNotificationRequest;
import xyz.bfdwdd.nshguildmanageb.base.notification.dto.response.NotificationResponse;
import xyz.bfdwdd.nshguildmanageb.base.notification.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "获取所有通知", description = "Get all notifications")
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @Operation(summary = "根据ID获取通知", description = "Get a notification by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "找到通知"),
            @ApiResponse(responseCode = "404", description = "未找到通知", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @Operation(summary = "创建通知", description = "Create a new notification")
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody CreateNotificationRequest request) {
        return ResponseEntity.status(201).body(notificationService.createNotification(request));
    }

    @Operation(summary = "更新通知", description = "Update an existing notification")
    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse> updateNotification(@PathVariable Long id, @RequestBody UpdateNotificationRequest request) {
        return ResponseEntity.ok(notificationService.updateNotification(id, request));
    }

    @Operation(summary = "删除通知", description = "Delete a notification by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "根据接收者ID获取通知", description = "Get all notifications for a specific user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByReceiverId(userId));
    }

    @Operation(summary = "获取用户未读通知", description = "Get all unread notifications for a specific user")
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResponse>> getUnreadNotificationsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotificationsByReceiverId(userId));
    }
}