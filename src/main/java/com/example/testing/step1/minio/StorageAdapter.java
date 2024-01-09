package com.example.testing.step1.minio;

import java.io.IOException;
import java.io.InputStream;

public interface StorageAdapter {
    void uploadFile(String key, InputStream content, long size, String contentType) throws IOException;
}
