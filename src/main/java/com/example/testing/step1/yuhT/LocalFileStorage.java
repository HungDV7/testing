package com.example.testing.step1.yuhT;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.example.testing.step1.HelloApplication.staticDir;


public class LocalFileStorage implements FileStorage {
    @Override
    public void uploadFile(String fileName, InputStream content, long size, String contentType) throws IOException {
        try {
            Path uploadPath = Paths.get(staticDir);

            if (!uploadPath.toFile().exists()) {
                uploadPath.toFile().mkdirs();
            }

            // Xác định đường dẫn đầy đủ cho file mới
            Path filePath = uploadPath.resolve(fileName);

            // Mở Output Stream để ghi dữ liệu từ InputStream vào file
            try (OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = content.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to upload file to local", e);
        }
    }

    @Override
    public byte[] readFile(File file) throws IOException {
        try {
            Path filePath = Paths.get(file.getAbsolutePath());
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new IOException("Failed to read file from local", e);
        }
    }
}
