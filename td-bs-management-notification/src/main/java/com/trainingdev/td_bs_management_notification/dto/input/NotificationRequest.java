package com.trainingdev.td_bs_management_notification.dto.input;

import com.trainingdev.td_bs_management_notification.enums.NotificationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    @NotNull(message = "Sender user ID cannot be null")
    private Integer senderUserId;

    @NotNull(message = "Receptor user ID cannot be null")
    private Integer receptorUserId;

    @NotNull(message = "Invalid notification type")
    private NotificationTypeEnum type;

    @NotBlank(message = "Notification description cannot be empty")
    private String description;
}
