package org.ejemplo.exceptions;

import org.springframework.http.HttpStatus;

public class ProductException extends Exception {
    private HttpStatus statusCode;
    private String cause;

    public ProductException(HttpStatus status, String message, String cause) {
        super(message);
        this.cause = cause;
        this.statusCode = status;
    }

    public HttpStatus getStatus() {
        return statusCode;
    }
}
