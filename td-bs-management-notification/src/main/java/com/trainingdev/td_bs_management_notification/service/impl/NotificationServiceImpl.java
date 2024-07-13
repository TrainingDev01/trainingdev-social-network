package com.trainingdev.td_bs_management_notification.service.impl;

import com.trainingdev.td_bs_management_notification.dto.input.NotificationDetail;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationRequest;
import com.trainingdev.td_bs_management_notification.entities.NotificationEntity;
import com.trainingdev.td_bs_management_notification.entities.UserEntity;
import com.trainingdev.td_bs_management_notification.exception.NotificationNotFoundException;
import com.trainingdev.td_bs_management_notification.mapper.NotificationMapper;
import com.trainingdev.td_bs_management_notification.repository.NotificationRepository;
import com.trainingdev.td_bs_management_notification.repository.UserRepository;
import com.trainingdev.td_bs_management_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;

    private final NotificationMapper notificationMapper;

    @Value("${properties.messages.error.notifications-not-found}")
    private String notificationsNotFoundError;

    @Value("${properties.messages.error.sender-not-found}")
    private String senderNotExistError;

    @Value("${properties.messages.error.receptor-not-found}")
    private String receptorNotExistError;

    @Override
    public NotificationDetail createNotification(NotificationRequest notificationRequest) {
        UserEntity senderUser = userRepository.findById(notificationRequest.getSenderUserId())
                .orElseThrow(() -> new NotificationNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), senderNotExistError));
        UserEntity receptorUser = userRepository.findById(notificationRequest.getReceptorUserId())
                .orElseThrow(() -> new NotificationNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), receptorNotExistError));
        NotificationEntity notificationEntity = notificationMapper.notificationRequestToNotificationEntity(notificationRequest);
        notificationEntity.setSenderUser(senderUser);
        notificationEntity.setReceptorUser(receptorUser);
        NotificationEntity notificationEntitySaved = notificationRepository.save(notificationEntity);
        return notificationMapper.notificationEntityToNotificationDetail(notificationEntitySaved);
    }

    @Override
    public List<NotificationDetail> getAllNotifications(Integer receptorUserId) {
        List<NotificationEntity> notifications = notificationRepository.findNotificationsByReceptorUserId(receptorUserId);
        if (notifications.isEmpty()) {
            throw new NotificationNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), notificationsNotFoundError);
        }
        return notifications.stream()
                .map(notification -> notificationMapper.notificationEntityToNotificationDetail(notification))
                .toList();
    }
}
