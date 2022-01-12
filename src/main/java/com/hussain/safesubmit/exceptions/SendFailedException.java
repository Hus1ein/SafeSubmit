package com.hussain.safesubmit.exceptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class SendFailedException extends Exception {

    private final String message;
    private final String timestamp;

    public SendFailedException(String message) {
        this.message = message;
        this.timestamp = (new Date()).toString();
    }

}
