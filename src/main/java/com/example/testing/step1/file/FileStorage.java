package com.example.testing.step1.file;

import com.example.testing.step1.minio.StorageAdapter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static com.example.testing.step1.HelloApplication.staticDir;

public class FileStorage implements StorageAdapter {

    @Override
    public void uploadFile(String key, InputStream content, long size, String contentType) throws IOException {

        String targetFilePath = staticDir + File.separator + key;
        File targetFile = new File(targetFilePath);
        FileUtils.copyInputStreamToFile(content,targetFile);
    }

}
