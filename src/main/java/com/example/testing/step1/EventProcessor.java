package com.example.testing.step1;

public class EventProcessor implements Runnable {
    private EventQueue eventQueue;

    public EventProcessor(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void run() {
        System.out.println("EventProcessor thread is running...");
        while (true) {
            try {
                Object event = eventQueue.getNextEvent();
                if (event instanceof FileSaveEvent) {
                    FileSaveEvent fileSaveEvent = (FileSaveEvent) event;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
