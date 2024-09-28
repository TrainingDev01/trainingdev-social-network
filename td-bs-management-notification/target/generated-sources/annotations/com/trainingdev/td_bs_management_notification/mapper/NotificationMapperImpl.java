package com.trainingdev.td_bs_management_notification.mapper;

import com.trainingdev.td_bs_management_notification.dto.input.NotificationDetail;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationRequest;
import com.trainingdev.td_bs_management_notification.dto.input.User;
import com.trainingdev.td_bs_management_notification.entities.NotificationEntity;
import com.trainingdev.td_bs_management_notification.entities.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-28T00:21:29-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationEntity notificationRequestToNotificationEntity(NotificationRequest notificationRequest) {
        if ( notificationRequest == null ) {
            return null;
        }

        NotificationEntity notificationEntity = new NotificationEntity();

        notificationEntity.setDescription( notificationRequest.getDescription() );
        notificationEntity.setType( notificationRequest.getType() );

        return notificationEntity;
    }

    @Override
    public NotificationDetail notificationEntityToNotificationDetail(NotificationEntity notificationEntity) {
        if ( notificationEntity == null ) {
            return null;
        }

        NotificationDetail notificationDetail = new NotificationDetail();

        notificationDetail.setId( notificationEntity.getId() );
        notificationDetail.setSenderUser( userEntityToUser( notificationEntity.getSenderUser() ) );
        notificationDetail.setReceptorUser( userEntityToUser( notificationEntity.getReceptorUser() ) );
        notificationDetail.setType( notificationEntity.getType() );
        notificationDetail.setDescription( notificationEntity.getDescription() );
        notificationDetail.setCreationDate( notificationEntity.getCreationDate() );

        return notificationDetail;
    }

    protected User userEntityToUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userEntity.getId() );
        user.name( userEntity.getName() );
        user.profilePhoto( userEntity.getProfilePhoto() );

        return user.build();
    }
}
