package com.example.testing.step1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EventQueue{
    private BlockingQueue<Object> eventQueue = new LinkedBlockingQueue<>(10);

//    ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,1, TimeUnit.SECONDS,eventQueue);
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
