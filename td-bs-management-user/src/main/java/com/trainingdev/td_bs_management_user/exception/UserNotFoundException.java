package com.trainingdev.td_bs_management_user.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private String code;

    public UserNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
