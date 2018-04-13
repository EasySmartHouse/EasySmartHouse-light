package net.easysmarthouse.util;

import java.io.Serializable;

public class CustomError implements Serializable {

    private String errorMessage;

    public CustomError() {
    }

    public CustomError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
