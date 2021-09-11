package com.senthuran.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "fileInfo")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filename;

    private String filenameS3;

    public File(){

    }
    public File( String filename, String filenameS3) {
        this.filename = filename;
        this.filenameS3 = filenameS3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilenameS3() {
        return filenameS3;
    }

    public void setFilenameS3(String filenameS3) {
        this.filenameS3 = filenameS3;
    }
}
