package com.example.testing.step1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue{
    private BlockingQueue<Object> eventQueue = new LinkedBlockingQueue<>();

    public void addEvent(Object event) {
        try {
            eventQueue.put(event);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Object getNextEvent() throws InterruptedException {
        return eventQueue.take();
    }

}
