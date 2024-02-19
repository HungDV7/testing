package com.example.testing.step1.minio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface StorageAdapter {
    void uploadFile(String fileName, InputStream content, long size, String contentType) throws IOException;

    String readFile(File file) throws IOException;
}
