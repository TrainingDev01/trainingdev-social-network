package com.trainingdev.td_bs_management_user.exception;


import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {

    private String code;

    public ConflictException(String code, String message) {
        super(message);
        this.code = code;
    }
}
