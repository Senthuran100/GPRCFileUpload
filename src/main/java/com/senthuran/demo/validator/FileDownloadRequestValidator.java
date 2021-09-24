package com.senthuran.demo.validator;

import com.senthuran.demo.dto.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileDownloadRequestValidator {

    public List<Error> validateDownloadRequest(int fileId) {
        List<Error> errorList = new ArrayList<>();
        if (String.valueOf(fileId)==null) {
            Error error = new Error("fileId", "FileId is empty");
            errorList.add(error);
        }
        return errorList;
    }
}
