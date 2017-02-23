package com.healthcare.product.service.domain;

/**
 * Created by wichon on 2/13/17.
 */
public class ExceptionResponse {
    private int code;
    private String exception;
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExceptionResponse(int code, String exception, String description) {
        this.code = code;
        this.exception = exception;
        this.description = description;
    }
}
