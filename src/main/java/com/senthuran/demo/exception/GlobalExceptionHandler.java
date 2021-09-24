package com.senthuran.demo.exception;

import com.senthuran.demo.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleException(Exception exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError("Something went wromg");
        apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleFileNotFoundException(FileNotFoundException fileNotFoundException) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(601);
        apiResponse.setError("File Not found. Please input the file");
        apiResponse.setData("File Not Found");
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body(apiResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleBadRequestException(BadRequestException badRequestException) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(400);
        apiResponse.setData(badRequestException.getErrorList());
        apiResponse.setError(badRequestException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}
