package com.example.testing;


import io.javalin.Javalin;
import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.server.Server;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication {

    public static void main(String[] args) {

        // todo 2
        Javalin app = Javalin.create(
                javalinConfig -> {
                    javalinConfig.jetty.server(Server::new);
                    javalinConfig.staticFiles.add("/static");
                }).start(8080);

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
                String port = Integer.toString(app.port());
                String staticDir = app.cfg.staticFiles.getClass().descriptorString();
                ctx.result("Port: " + port + "\n" + "StaticDir: " + staticDir);
            } else {
                ctx.status(400).result("Invalid question parameter");
            }
        });

        // todo 4
        app.post("/api/savetext/{pt}", ctx -> {
            String filename = ctx.pathParam("pt");
            String text = ctx.body();
            String staticDirPath = "src/main/resources/static";
            try {
                File file = new File(staticDirPath, filename);
                FileUtils.writeStringToFile(file, text, "UTF-8");
                ctx.result("Save success: " + filename);
            } catch (IOException e) {
                ctx.status(500).result("Error saving text to file");
            }
        });
    }
}