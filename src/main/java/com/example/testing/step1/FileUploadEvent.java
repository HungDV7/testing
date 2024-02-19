package com.example.testing.step1;

import java.io.InputStream;

public class FileUploadEvent {
    private String filename;
    private InputStream content;
    private long size;
    private String contentType;


    public FileUploadEvent() {
    }

    public FileUploadEvent(String filename, InputStream content, long size, String contentType) {
        this.filename = filename;
        this.content = content;
        this.size = size;
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
