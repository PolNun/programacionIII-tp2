package org.ejemplo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class UserRegistrationException extends RuntimeException {
    private HttpStatus statusCode;
    private String cause;

    public UserRegistrationException(HttpStatus status, String message, String cause) {
        super(message);
        this.cause = cause;
        this.statusCode = status;
    }

    public int getStatusCode() {
        return statusCode.value();
    }
}