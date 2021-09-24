package com.senthuran.demo.validator;

import com.senthuran.demo.dto.Error;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileCreateRequestValidator {

    public List<Error> validateFileCreateRequest(MultipartFile file) {
        List<Error> errorList=new ArrayList<>();

        if (file.isEmpty()) {
            Error error = new Error("file", "file is Empty");
            errorList.add(error);
        }
        return errorList;
    }

}
