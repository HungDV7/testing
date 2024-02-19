package com.example.testing.step1.file;

import com.example.testing.step1.minio.StorageAdapter;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.testing.step1.HelloApplication.staticDir;

public class FileStorage implements StorageAdapter {

    @Override
    public void uploadFile(String fileName, InputStream content, long size, String contentType) throws IOException {
        String targetFilePath = staticDir + File.separator + fileName;
        File targetFile = new File(targetFilePath);
        FileUtils.copyInputStreamToFile(content, targetFile);
    }

    @Override
    public String readFile(File file) throws IOException {
        String fileContent = FileUtils.readFileToString(file, "UTF-8");
        return fileContent;
    }


}
