package com.example.testing.step1;

import java.io.File;
import java.io.InputStream;

public class FileReadEvent {
    private File file;
    public FileReadEvent(File file) {
        this.file = file;
    }

    public FileReadEvent() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
