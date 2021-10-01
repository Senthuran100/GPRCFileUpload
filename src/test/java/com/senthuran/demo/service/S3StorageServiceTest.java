package com.senthuran.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senthuran.demo.model.File;
import com.senthuran.demo.repository.FileRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class S3StorageServiceTest {

    @Autowired
    private S3StorageService s3StorageService;

    @MockBean
    private S3StorageService s3StorageSer;

    @MockBean
    private FileRepo fileRepo;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Test
    public void saveFile() {
        String fileName = System.currentTimeMillis() + "_" + outputMultiPartFile().getOriginalFilename();
        File file=new File();
        file.setFilename(outputMultiPartFile().getOriginalFilename());
        file.setFilenameS3(fileName);

        Mockito.when(s3StorageSer.uploadFileToS3(outputMultiPartFile(),fileName)).thenReturn(true);
        Mockito.when(fileRepo.save(file)).thenReturn(file);

        File savedFile =  fileRepo.save(file);
        assertEquals(savedFile.getFilename(),"fileThatDoesNotExists.txt");
    }

    private static MultipartFile outputMultiPartFile(){
        MultipartFile fichier = new MockMultipartFile("fileThatDoesNotExists.txt",
                "fileThatDoesNotExists.txt",
                "text/plain",
                "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
        return  fichier;
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void deleteFileinS3Test() {
        String fileName="sample.png";
        Mockito.when(s3StorageSer.deleteFileinS3(fileName)).thenReturn("Success");
        assertEquals(s3StorageSer.deleteFileinS3(fileName),"Success");
    }
}