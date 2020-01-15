package com.ame.challenge.planet.exception;

import org.springframework.http.HttpStatus;

public class SwapiException extends Exception {

    private HttpStatus httpStatus;

    public SwapiException() {
        super();
    }

    public SwapiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public SwapiException(HttpStatus httpStatus, Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public SwapiException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
