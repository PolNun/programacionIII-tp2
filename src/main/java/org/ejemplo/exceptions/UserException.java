package org.ejemplo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserException extends Exception {
    private HttpStatus statusCode;
    private String cause;

    public UserException(HttpStatus status, String message, String cause) {
        super(message);
        this.cause = cause;
        this.statusCode = status;
    }
}
