package com.senthuran.demo.controllers;

import com.senthuran.demo.dto.Error;
import com.senthuran.demo.dto.FileDeleteResponse;
import com.senthuran.demo.dto.FileDownloadResponse;
import com.senthuran.demo.dto.FileResponse;
import com.senthuran.demo.exception.BadRequestException;
import com.senthuran.demo.service.S3StorageService;
import com.senthuran.demo.validator.FileCreateRequestValidator;
import com.senthuran.demo.validator.FileDownloadRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("files")
public class FileUploadController {

    @Autowired
    private S3StorageService s3StorageService;

    @Autowired
    private FileCreateRequestValidator fileCreateRequestValidator;

    @Autowired
    private FileDownloadRequestValidator fileDownloadRequestValidator;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        List<Error> errorList = fileCreateRequestValidator.validateFileCreateRequest(file);
        if (errorList.size() > 0) {
            throw new BadRequestException("File Request is Invalid", errorList);
        }
        return new ResponseEntity<>(s3StorageService.saveFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int fileId) {
        List<Error> errorList=fileDownloadRequestValidator.validateDownloadRequest(fileId);
        if(errorList.size()>0){
            throw new BadRequestException("File Download Request is Invalid",errorList);
        }
        FileDownloadResponse fileDownloadResponse = s3StorageService.getFile(fileId);
        ByteArrayResource resource = new ByteArrayResource(fileDownloadResponse.getData());
        return ResponseEntity
                .ok().contentLength(fileDownloadResponse.getData().length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileDownloadResponse.getFileName() + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<FileDeleteResponse> deleteFile(@PathVariable int fileId) {
        return new ResponseEntity<>(s3StorageService.deleteFile(fileId), HttpStatus.OK);
    }
}
