package com.trainingdev.td_bs_management_notification.mapper;

import com.trainingdev.td_bs_management_notification.dto.input.NotificationDetail;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationRequest;
import com.trainingdev.td_bs_management_notification.entities.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "senderUser", ignore = true)
    @Mapping(target = "receptorUser", ignore = true)
    NotificationEntity notificationRequestToNotificationEntity(NotificationRequest notificationRequest);

    NotificationDetail notificationEntityToNotificationDetail(NotificationEntity notificationEntity);

}
