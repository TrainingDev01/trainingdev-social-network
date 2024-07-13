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

    @NotNull(message = "Sender user ID cannot be null")
    private User senderUser;

    @NotNull(message = "Receptor user ID cannot be null")
    private User receptorUser;

    @NotNull(message = "type cannot be null")
    private NotificationTypeEnum type;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private LocalDateTime creationDate;

}
