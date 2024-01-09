package com.example.testing.step1;

public class FileSaveEvent {
    private String targetDirectoryPath;
    private String filename;

    public FileSaveEvent() {
    }

    public String getTargetDirectoryPath() {
        return targetDirectoryPath;
    }

    public void setTargetDirectoryPath(String targetDirectoryPath) {
        this.targetDirectoryPath = targetDirectoryPath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
