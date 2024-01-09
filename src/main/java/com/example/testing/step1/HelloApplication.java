package com.example.testing.step1;


import com.example.testing.step1.file.FileStorage;
import com.example.testing.step1.minio.MinioStorage;
import com.example.testing.step1.minio.StorageAdapter;
import io.javalin.Javalin;
import io.javalin.http.UploadedFile;
import io.javalin.http.staticfiles.Location;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelloApplication {


    public static final String staticDir = System.getProperty("static.dir", "src/main/resources/static");

    public static void main(String[] args) {

        EventQueue eventQueue = new EventQueue();

        MinioStorage minioFileStorage = new MinioStorage();


        EventProcessor eventProcessor = new EventProcessor(eventQueue);
        Thread eventThread = new Thread(eventProcessor);
        System.out.println("Main thread: Starting thread");
        eventThread.start();

        int port = Integer.parseInt(System.getProperty("server.port", "8080"));

        // todo 2
        Javalin app = Javalin.create(
                javalinConfig -> {
                    javalinConfig.staticFiles.add(staticDir, Location.EXTERNAL);
                }).start(port);

        app.get("/", ctx -> ctx.result("Hell World!!!"));
        System.out.println("Server port: " + app.port());


        //todo 3
        app.get("/api/", ctx -> {
            String question = ctx.queryParam("question");
            if ("date".equals(question)) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                ctx.result("Current time: " + formattedDateTime);
            } else if ("config".equals(question)) {
                String porT = Integer.toString(app.port());
                ctx.result("Port: " + porT + "\n" + "StaticDir: " + staticDir);
            } else {
                ctx.status(400).result("Invalid question parameter");
            }
        });


        // todo 4-5
        app.post("/api/savefile/{pt}", ctx -> {
            String dynamicDirectoryName = ctx.pathParam("pt");
            List<UploadedFile> uploadedFiles = ctx.uploadedFiles("files");
            String storageType = ctx.queryParam("fs");
            StorageAdapter storageAdapter;

            try {
                String targetDirectoryPath = staticDir + File.separator + dynamicDirectoryName;
                File targetDirectory = new File(targetDirectoryPath);

                if (!targetDirectory.exists()) {
                    targetDirectory.mkdirs();
                }

                System.out.println("Target Directory: " + targetDirectory);

                if (uploadedFiles != null && !uploadedFiles.isEmpty()) {
                    for (UploadedFile uploadedFile : uploadedFiles) {
                        try (InputStream inputStream = uploadedFile.content()) {
                            String targetFilePath = targetDirectoryPath + File.separator + uploadedFile.filename();


                            if("s3".equalsIgnoreCase(storageType)){
                                storageAdapter = new MinioStorage();
                            } else if ("file".equalsIgnoreCase(storageType)) {
                                storageAdapter = new FileStorage();
                            }else {
                                throw new IllegalAccessException("Not storage success");
                            }

                            storageAdapter.uploadFile(targetFilePath,inputStream,inputStream.available(),uploadedFile.contentType());

                            FileSaveEvent fileSaveEvent = new FileSaveEvent();
                            fileSaveEvent.setTargetDirectoryPath(targetDirectoryPath);
                            fileSaveEvent.setFilename(uploadedFile.filename());

                            eventQueue.addEvent(fileSaveEvent);
                            ctx.result("Event added to queue: " + fileSaveEvent);

//                            ctx.result("Save success: " + targetFile);
                            System.out.println("File saved: " + targetFilePath);
                        } catch (IOException e) {
                            e.printStackTrace();
                            ctx.status(500).result("Save error for files");
                        }
                    }
                } else {
                    String text = ctx.body();
                    String targetFilePath = targetDirectoryPath + File.separator + System.currentTimeMillis() + "_text.txt";
                    File targetFile = new File(targetFilePath);
                    FileUtils.writeStringToFile(targetFile, text, "UTF-8");

                    ctx.result("Save success: " + targetFile);
                    System.out.println("File saved: " + targetFilePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Save error");
            }
        });


        //todo 6 Api lay ra thong tin ben trong file
        app.get("/api/savetext/{subfolder}/{filename}", ctx -> {
            try {
                String subfolder = ctx.pathParam("subfolder");
                String filename = ctx.pathParam("filename");
                String filePath = staticDir + File.separator + subfolder + File.separator + filename;

                File file = new File(filePath);
                if (file.exists()) {
                    String text = FileUtils.readFileToString(file, "UTF-8");
                    ctx.result("Content of file " + filename + ":\n" + text);
                } else {
                    ctx.status(404).result("File not found: " + filename);
                }
            } catch (IOException e) {
                e.printStackTrace();
                ctx.status(500).result("Error retrieving text");
            }
        });



        // todo 6: Api lay ra danh sach file
        app.get("/api/savetexts/{folderName}", ctx -> {
            try {
                String folderName = ctx.pathParam("folderName");
                File targetDirectory = new File(staticDir, folderName);

                if (targetDirectory.exists() && targetDirectory.isDirectory()) {
                    File[] files = targetDirectory.listFiles();
                    if (files != null) {
                        List<String> fileNames = Arrays.stream(files)
                                .filter(File::isFile)
                                .map(File::getName)
                                .collect(Collectors.toList());

                        ctx.json(fileNames);
                    } else {
                        ctx.status(404).result("No files found in the specified folder.");
                    }
                } else {
                    ctx.status(404).result("Specified folder not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Error retrieving file list.");
            }
        });



    }
}