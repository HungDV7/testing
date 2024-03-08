package com.example.testing.step1.yuhT;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class S3FileStorage implements FileStorage {


    private static final String MINIO_URL = "https://play.min.io";
    private static final String ACCESS_KEY = "LNkMMQSxopOXSRaHWTAE";
    private static final String SECRET_KEY = "vLas7TKZAmGfBIEfXs45ail4Od1wT1bAJFZgpLXG";
    private static final String BUCKET_NAME = "yuhtzz";

    private final MinioClient minioClient;

    public S3FileStorage() {
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
    public byte[] readFile(File file) throws IOException {
        return new byte[0];
    }
}
