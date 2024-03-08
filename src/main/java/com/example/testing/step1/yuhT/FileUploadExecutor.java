package com.example.testing.step1.yuhT;

import java.util.concurrent.*;

public class FileUploadExecutor {
    public static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final long KEEP_ALIVE_TIME = 5000;

    private static final BlockingQueue<Runnable> QUEUE = new LinkedBlockingQueue<>();

    public static Executor createThreadPoolExecutor() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                QUEUE
        );
    }
}
