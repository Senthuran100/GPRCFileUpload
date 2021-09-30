package com.senthuran.demo.dto;

import com.senthuran.demo.model.File;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FileResponse {
    private String status;
    private File file;

    public FileResponse(String status, File file) {
        this.status = status;
        this.file = file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
