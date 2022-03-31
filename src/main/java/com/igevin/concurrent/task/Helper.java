package com.igevin.concurrent.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class Helper {
    @Autowired
    private ExecutorService executorService;

    public void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeConcurrentTask(Runnable runnable) {
        executeConcurrentTask(runnable, 100L);
    }

    public void executeConcurrentTask(Runnable runnable, long interval) {
        int loop = 5;
        for (int i = 0; i < loop; i++) {
            executorService.submit(runnable);
            if (interval > 0) {
                sleep(interval);
            }
        }
    }
}
