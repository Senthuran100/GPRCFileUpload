package com.senthuran.demo.exception;

import com.senthuran.demo.dto.Error;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<Error> errorList;

    public BadRequestException(String message, List<Error> errorList) {
        super(message);
        this.errorList = errorList;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }
}
