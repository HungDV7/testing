package com.example.testing.step1.minio;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.IOException;
import java.io.InputStream;

public class MinioStorage implements StorageAdapter {

    private static final String MINIO_URL = "https://play.min.io";
    private static final String ACCESS_KEY = "YSEM8mJBOy02GTjCUxfV";
    private static final String SECRET_KEY = "0VdjFtQ3UO9tpIM9EV7EG31irdPS7v9aJl6SorXW";
    private static final String BUCKET_NAME = "testers";

    private final MinioClient minioClient;

    public MinioStorage() {
        this.minioClient = MinioClient.builder()
                .endpoint(MINIO_URL)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }

    @Override
    public void uploadFile(String key, InputStream content, long size, String contentType) throws IOException {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(key)
                    .stream(content, size, -1)
                    .contentType(contentType)
                    .build());
        } catch (Exception e) {
            throw new IOException("Error uploading file to MinIO", e);
        }
    }
}
