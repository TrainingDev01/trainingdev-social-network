package com.trainingdev.td_bs_management_notification.service.impl;

import com.trainingdev.td_bs_management_notification.dto.input.NotificationDetail;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationRequest;
import com.trainingdev.td_bs_management_notification.entities.NotificationEntity;
import com.trainingdev.td_bs_management_notification.entities.UserEntity;
import com.trainingdev.td_bs_management_notification.exception.NotificationNotFoundException;
import com.trainingdev.td_bs_management_notification.mapper.NotificationMapper;
import com.trainingdev.td_bs_management_notification.repository.NotificationRepository;
import com.trainingdev.td_bs_management_notification.repository.UserRepository;
import com.trainingdev.td_bs_management_notification.utils.UtilData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationMapper notificationMapper;

    @InjectMocks
    private NotificationServiceImpl notificationService;


    @Test
    public void createNotification_when_return_success() {
        NotificationRequest request = UtilData.createNotificationRequest();
        UserEntity userEntity = UtilData.createUserEntity();
        NotificationEntity notificationEntity = UtilData.createNotificationEntity();
        NotificationDetail expectedDetail = UtilData.createNotificationDetail();

        when(userRepository.findById(request.getSenderUserId())).thenReturn(Optional.ofNullable(userEntity));
        when(userRepository.findById(request.getReceptorUserId())).thenReturn(Optional.ofNullable(userEntity));
        when(notificationMapper.notificationRequestToNotificationEntity(request)).thenReturn(notificationEntity);
        when(notificationRepository.save(any(NotificationEntity.class))).thenReturn(notificationEntity);
        when(notificationMapper.notificationEntityToNotificationDetail(any(NotificationEntity.class))).thenReturn(expectedDetail);

        NotificationDetail result = notificationService.createNotification(request);
        assertEquals(expectedDetail, result);
    }

    @Test
    public void createNotification_when_return_internal_error() {
        NotificationRequest request = UtilData.createNotificationRequest();
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        NotificationNotFoundException exception = assertThrows(NotificationNotFoundException.class, () -> {
            notificationService.createNotification(request);
        });
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }


    @Test
    public void getAllNotifications_when_return_success() {
        NotificationEntity entity = UtilData.createNotificationEntity();
        NotificationDetail detail = UtilData.createNotificationDetail();

        when(notificationRepository.findNotificationsByReceptorUserId(anyInt())).thenReturn(List.of(entity));
        when(notificationMapper.notificationEntityToNotificationDetail(any(NotificationEntity.class))).thenReturn(detail);

        List<NotificationDetail> result = notificationService.getAllNotifications(anyInt());

        assertEquals(1, result.size());
        assertEquals(detail, result.get(0));
    }

    @Test
    public void getAllNotifications_when_return_not_found_exception() {
        when(notificationRepository.findNotificationsByReceptorUserId(anyInt())).thenReturn(Collections.emptyList());

        NotificationNotFoundException exception = assertThrows(NotificationNotFoundException.class, () -> {
            notificationService.getAllNotifications(anyInt());
        });
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }

}