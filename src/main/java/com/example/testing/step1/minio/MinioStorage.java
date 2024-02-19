package com.example.testing.step1.minio;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MinioStorage implements StorageAdapter {

    private static final String MINIO_URL = "https://play.min.io";
    private static final String ACCESS_KEY = "5Wrc3dCFafokppbQwqm9";
    private static final String SECRET_KEY = "GCfPvEIjDdBALTliaFpE8XP9Wtpy1nUNzhIiOlq7";
    private static final String BUCKET_NAME = "testings3";

    private final MinioClient minioClient;

    public MinioStorage() {
        this.minioClient = MinioClient.builder()
                .endpoint(MINIO_URL)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }


    @Override
    public void uploadFile(String fileName, InputStream content, long size, String contentType) throws IOException {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .stream(content, size, -1)
                    .contentType(contentType)
                    .build());
        } catch (Exception e) {
            throw new IOException("Error uploading file to MinIO", e);
        }
    }

    @Override
    public String readFile(File file) throws IOException {
        return null;
    }
}
