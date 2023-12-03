package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.member.dto.NotificationDto.NotificationInfoHan;
import com.example.medicheckbackend.service.FCMNotificationService;
import com.google.cloud.storage.NotificationInfo;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FCMNotificationApiController {

    private final FCMNotificationService fcmNotificationService;

    @Operation(summary = "알림 보내기")
    @PostMapping("/notification")
    public ResponseEntity<String> sendNotificationByToken() throws FirebaseMessagingException {
        return ResponseEntity.ok(fcmNotificationService.sendNotificationByToken());}

    @Operation(summary = "경수한테 알림 보내기")
    @PostMapping("/notification/han")
    public ResponseEntity<String> sendTemp(@RequestBody NotificationInfoHan notificationInfoHan) throws FirebaseMessagingException {
        return ResponseEntity.ok(fcmNotificationService.sendTemp(notificationInfoHan));
    }
}
