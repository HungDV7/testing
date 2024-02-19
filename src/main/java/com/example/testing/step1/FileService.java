package com.example.testing.step1;

import com.example.testing.step1.file.FileStorage;
import com.example.testing.step1.minio.MinioStorage;
import com.example.testing.step1.minio.StorageAdapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileService {
    private StorageAdapter storageAdapter;

    public FileService() {
    }

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }
    public void uploadFile(String key, InputStream content, long size, String contentType) throws IOException{
        storageAdapter.uploadFile(key,content,size,contentType);
    }

    public void setStorageAdapter(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    public String readFile(File file) throws IOException {
        return storageAdapter.readFile(file);
    }

}
