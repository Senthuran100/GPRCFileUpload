package com.senthuran.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senthuran.demo.dto.ApiResponse;
import com.senthuran.demo.dto.Error;
import com.senthuran.demo.dto.FileDeleteResponse;
import com.senthuran.demo.dto.FileResponse;
import com.senthuran.demo.service.S3StorageService;
import com.senthuran.demo.validator.FileCreateRequestValidator;
import com.senthuran.demo.validator.FileDownloadRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FileUploadController.class, secure = false)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private S3StorageService s3StorageService;

    @MockBean
    private FileCreateRequestValidator fileCreateRequestValidator;

    @MockBean
    private FileDownloadRequestValidator fileDownloadRequestValidator;

    @Test
    public void checkStatus() throws Exception {
        ApiResponse apiResponse = new ApiResponse(200, "No data", null);
        String inputJson = this.mapToJson(apiResponse);
        String URI = "/files/checkStatus";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String ouputJson = response.getContentAsString();

        assertEquals(ouputJson,inputJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    public void testDeleteFile() throws Exception {
        int fileId=1;

        List<Error> errorList=new ArrayList();

        Mockito.when(fileDownloadRequestValidator.validateDownloadRequest(fileId)).thenReturn(errorList);
        FileDeleteResponse fileResponse=new FileDeleteResponse();
        fileResponse.setStatus("Success");
        fileResponse.setFileName("1633009886476_Tumbnail1.png");
        String inputJson = this.mapToJson(fileResponse);
        String URI = "/files/delete/{fileId}";

        Mockito.when(s3StorageService.deleteFile(fileId)).thenReturn(fileResponse);

        RequestBuilder requestBuilder=MockMvcRequestBuilders
                .delete(URI,fileId);

        MvcResult result= mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response= result.getResponse();

        String outputJson = response.getContentAsString();
        assertEquals(outputJson,inputJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
