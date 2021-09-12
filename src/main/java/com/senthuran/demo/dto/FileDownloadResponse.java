package com.senthuran.demo.dto;

public class FileDownloadResponse {

    private byte[] data;
    private String fileName;

    public FileDownloadResponse(byte[] data, String fileName) {
        this.data = data;
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
