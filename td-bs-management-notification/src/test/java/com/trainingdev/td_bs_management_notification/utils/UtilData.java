package com.trainingdev.td_bs_management_notification.utils;

import com.trainingdev.td_bs_management_notification.dto.input.NotificationDetail;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationRequest;
import com.trainingdev.td_bs_management_notification.dto.input.User;
import com.trainingdev.td_bs_management_notification.entities.NotificationEntity;
import com.trainingdev.td_bs_management_notification.entities.UserEntity;
import com.trainingdev.td_bs_management_notification.enums.NotificationTypeEnum;

import java.time.LocalDateTime;

public class UtilData {

    public static NotificationRequest createNotificationRequest() {
        NotificationRequest request = new NotificationRequest();
        request.setSenderUserId(1);
        request.setReceptorUserId(2);
        request.setType(NotificationTypeEnum.COMMENT);
        request.setDescription("Test notification");
        return request;
    }

    public static NotificationDetail createNotificationDetail() {
        User senderUser = User.builder().id(1).name("Oscar Fuentes").profilePhoto("https://trainingdev-s3/...").build();
        User receptorUser = User.builder().id(2).name("Santiago Lozano").profilePhoto("https://trainingdev-s3/...").build();
        NotificationDetail notificationDetail  = new NotificationDetail();
        notificationDetail.setId(1);
        notificationDetail.setSenderUser(senderUser);
        notificationDetail.setReceptorUser(receptorUser);
        notificationDetail.setType(NotificationTypeEnum.COMMENT);
        notificationDetail.setDescription("Test notification");
        notificationDetail.setCreationDate(LocalDateTime.now());
        return notificationDetail;
    }

    public static NotificationEntity createNotificationEntity() {
        UserEntity senderUser = UserEntity.builder().id(1).name("Oscar Fuentes").profilePhoto("https://trainingdev-s3/...").build();
        UserEntity receptorUser = UserEntity.builder().id(2).name("Santiago Lozano").profilePhoto("https://trainingdev-s3/...").build();
        NotificationEntity notification = new NotificationEntity();
        notification.setSenderUser(senderUser);
        notification.setReceptorUser(receptorUser);
        notification.setType(NotificationTypeEnum.MESSAGE);
        notification.setDescription("Test notification");
        notification.setCreationDate(LocalDateTime.now());
        notification.setModificationDate(LocalDateTime.now());
        return  notification;
    }

    public static UserEntity createUserEntity() {
        return UserEntity.builder().id(1).name("Oscar Fuentes").profilePhoto("https://trainingdev-s3/...").build();
    }




}
