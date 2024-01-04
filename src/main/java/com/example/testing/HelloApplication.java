package com.example.testing;


import io.javalin.Javalin;
import io.javalin.http.UploadedFile;
import io.javalin.http.staticfiles.Location;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        // todo 4
        app.post("/api/savetext/{pt}", ctx -> {
            String filename = ctx.pathParam("pt");
            String text = ctx.body();
            try {
                File file = new File(staticDir, filename);
                FileUtils.writeStringToFile(file, text, "UTF-8");
                ctx.result("Save success: " + filename);
            } catch (IOException e) {
                ctx.status(500).result("Error save");
            }
        });


        //todo 5
        app.post("/api/savefile/{pt}", ctx -> {
            List<UploadedFile> uploadedFiles = ctx.uploadedFiles("pt");
            for (UploadedFile uploadedFile : uploadedFiles) {
                try (InputStream inputStream = uploadedFile.content()) {
                    File localFile = new File(staticDir, uploadedFile.filename());
                    FileUtils.copyInputStreamToFile(inputStream, localFile);
                    ctx.result("Save success");
                } catch (IOException e) {
                    ctx.status(500).result("Error save");
                }
            }
        });
    }
}