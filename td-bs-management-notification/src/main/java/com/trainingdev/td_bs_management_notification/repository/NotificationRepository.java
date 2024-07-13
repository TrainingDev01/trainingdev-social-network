package com.trainingdev.td_bs_management_notification.repository;

import com.trainingdev.td_bs_management_notification.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

    @Query("SELECT n FROM NotificationEntity n WHERE n.receptorUser.id = :receptorUserId")
    List<NotificationEntity> findNotificationsByReceptorUserId(Integer receptorUserId);

}
