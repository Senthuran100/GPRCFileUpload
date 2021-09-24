package com.senthuran.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.senthuran.demo.dto.FileDeleteResponse;
import com.senthuran.demo.dto.FileDownloadResponse;
import com.senthuran.demo.dto.FileResponse;
import com.senthuran.demo.repository.FileRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class S3StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    FileRepo fileRepo;

    private boolean uploadFileToS3(MultipartFile multipartFile, String fileName) {
        try {
            File fileObj = convertMultiPartFileToFile(multipartFile);
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            fileObj.delete();
            return true;
        } catch (Exception e) {
            log.error("File Upload Exception" + e);
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

    public String deleteFileinS3(String fileName) {
        try {
            amazonS3.deleteObject(bucketName, fileName);
            return "Success";
        } catch (Exception e) {
            log.error("Error in deleting the file" + e);
        }
        return "Failure";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch(FileNotFoundException exp){
            log.error("Error file not found",exp);
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public FileResponse saveFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        boolean result = uploadFileToS3(file, fileName);
        if (result) {
            com.senthuran.demo.model.File fileObj = fileRepo.save(new com.senthuran.demo.model.File(file.getOriginalFilename(), fileName));
            return new FileResponse("Success", fileObj);
        }
        return new FileResponse("Failure", new com.senthuran.demo.model.File());
    }

    public FileDownloadResponse getFile(int id) {
        Optional<com.senthuran.demo.model.File> file = fileRepo.findById(id);
        String fileName = "";
        if (file.isPresent()) {
            com.senthuran.demo.model.File fileObj = file.get();
            fileName = fileObj.getFilenameS3();
            log.error("File" + downloadFile(fileName));
            return new FileDownloadResponse(downloadFile(fileName), fileName);
        }
        return new FileDownloadResponse(new byte[0], fileName);
    }

    public FileDeleteResponse deleteFile(int id) {
        Optional<com.senthuran.demo.model.File> file = fileRepo.findById(id);
        String fileName = "";
        if (file.isPresent()) {
            com.senthuran.demo.model.File fileObj = file.get();
            fileName = fileObj.getFilenameS3();
            String s3Response =  deleteFileinS3(fileName);
            if(s3Response.equals("Success")) {
                return new FileDeleteResponse("Success", fileName);
            } else {
                return new FileDeleteResponse("Failure to Delete the file from S3",fileName);
            }
        }
        return new FileDeleteResponse("Failure", "Failed to retrieve filename");
    }


}