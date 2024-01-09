package com.example.testing.step1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue {
    private BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    public void addEvent(Object event){
        queue.offer(event);
    }

    public Object getNextEvent() throws InterruptedException{
        return queue.take();
    }
}
