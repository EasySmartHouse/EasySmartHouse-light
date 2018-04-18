package net.easysmarthouse.util;

import java.io.Serializable;

public class CustomError implements Serializable {

    private ErrorType errorCode;
    private String errorMessage;

    public CustomError() {
    }

    public CustomError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CustomError(ErrorType errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorType getErrorCode() {
        return errorCode;
    }
}
