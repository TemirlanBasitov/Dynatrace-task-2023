package com.dynatrace.exception;

import java.time.LocalDate;
import java.util.Date;

public class ErrorDetails {
    private String message;
    private String path;


    public ErrorDetails(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
