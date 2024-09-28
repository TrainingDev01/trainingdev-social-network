package com.trainingdev.td_bs_management_notification.controller;

import com.trainingdev.td_bs_management_notification.dto.input.NotificationDetail;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationRequest;
import com.trainingdev.td_bs_management_notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notification")
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationDetail createNotification(@RequestBody @Valid NotificationRequest notificationRequest) {
        return notificationService.createNotification(notificationRequest);
    }


    @GetMapping("/notification/{receptorUserId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationDetail> getAllNotifications(@PathVariable(name = "receptorUserId", required = true)
                                                        Integer receptorUserId) {
        return notificationService.getAllNotifications(receptorUserId);
    }
}
