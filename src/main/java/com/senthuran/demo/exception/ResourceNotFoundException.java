package com.senthuran.demo.exception;

import com.senthuran.demo.dto.Error;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException{
    private List<Error> errorList;

    public ResourceNotFoundException(String s, List<Error> errorList) {
        super(s);
        this.errorList = errorList;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }
}
