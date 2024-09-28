package com.trainingdev.td_bs_management_notification.dto.input;

import com.trainingdev.td_bs_management_notification.enums.NotificationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDetail {

    private Integer id;

    private User senderUser;

    private User receptorUser;

    private NotificationTypeEnum type;

    private String description;

    private LocalDateTime creationDate;

}
