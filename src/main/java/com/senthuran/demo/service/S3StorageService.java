package com.senthuran.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.senthuran.demo.dto.FileResponse;
import com.senthuran.demo.repository.FileRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class S3StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    FileRepo fileRepo;

    private boolean uploadFileToS3(MultipartFile multipartFile,String fileName) {
        try {
            File fileObj = convertMultiPartFileToFile(multipartFile);
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            fileObj.delete();
            return true;
        } catch (Exception e){
            log.error("File Upload Exception"+e);
        }
        return false;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public FileResponse saveFile(MultipartFile file){
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        boolean result = uploadFileToS3(file,fileName);
        if(result){
            com.senthuran.demo.model.File fileObj = fileRepo.save(new com.senthuran.demo.model.File(file.getOriginalFilename(),fileName));
            return new FileResponse("Success",fileObj);
        }
        return new FileResponse("Failure",new com.senthuran.demo.model.File());

    }

}