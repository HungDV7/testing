package com.example.testing.step1;


import io.javalin.Javalin;
import io.javalin.http.UploadedFile;
import io.javalin.http.staticfiles.Location;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HelloApplication {

    public static void main(String[] args) {

        int port = Integer.parseInt(System.getProperty("server.port", "8080"));
        String staticDir = System.getProperty("static.dir", "src/main/resources/static");

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
                            String targetFilePath = targetDirectoryPath + File.separator +  uploadedFile.filename();
                            File targetFile = new File(targetFilePath);
                            FileUtils.copyInputStreamToFile(inputStream, targetFile);

                            ctx.result("Save success: " + targetFile);
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


    }
}