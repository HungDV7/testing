package com.example.testing.step1.yuhT;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface FileStorage {
    void uploadFile(String fileName, InputStream content, long size, String contentType) throws IOException;
    byte[] readFile(File file) throws IOException;
}
