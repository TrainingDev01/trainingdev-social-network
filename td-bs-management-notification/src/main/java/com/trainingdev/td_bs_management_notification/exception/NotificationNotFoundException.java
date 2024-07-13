package com.trainingdev.td_bs_management_notification.exception;

import lombok.Getter;

@Getter
public class NotificationNotFoundException extends RuntimeException {

    private String code;

    public NotificationNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
