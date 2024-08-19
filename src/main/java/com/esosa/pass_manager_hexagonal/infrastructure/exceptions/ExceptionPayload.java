package com.esosa.pass_manager_hexagonal.infrastructure.exceptions;

import java.time.LocalDateTime;

public class ExceptionPayload {

    private final String message;
    private final int status;
    private final LocalDateTime timestamp;

    public ExceptionPayload(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}