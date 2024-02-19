package com.example.testing.step1;

import com.example.testing.step1.minio.StorageAdapter;

import java.io.IOException;

public class EventProcessor implements Runnable {
    private EventQueue eventQueue;
    private FileService fileService;

    private StorageAdapter storageAdapter;

    public EventProcessor(EventQueue eventQueue, FileService fileService) {
        this.eventQueue = eventQueue;
        this.fileService = fileService;
    }
    public synchronized void setStorageAdapter(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
        fileService.setStorageAdapter(storageAdapter);
    }


    @Override
    public void run() {
        System.out.println("EventProcessor thread is running...");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Object event = eventQueue.getNextEvent();
                if (event instanceof FileUploadEvent) {
                    handleFileUploadEvent((FileUploadEvent) event);
                } else if (event instanceof FileReadEvent) {
                    handleFileReadEvent((FileReadEvent) event);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("EventProcessor thread interrupted. Exiting...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleFileUploadEvent(FileUploadEvent fileUploadEvent) throws IOException {
        fileService.uploadFile(fileUploadEvent.getFilename(), fileUploadEvent.getContent(),
                fileUploadEvent.getSize(), fileUploadEvent.getContentType());
    }

    private void handleFileReadEvent(FileReadEvent fileReadEvent) throws IOException {
        String fileContent = fileService.readFile(fileReadEvent.getFile());
    }


}
