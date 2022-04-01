package com.igevin.concurrent.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
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
        int loop = 5;
        executeConcurrentTask(runnable, 0L, loop);
    }

    public void executeConcurrentTask(Runnable runnable, long interval) {
        int loop = 5;
        executeConcurrentTask(runnable, interval, loop);
    }

    public void getPerformance(int concurrent, String method, Runnable runnable) {
        try {
            long cost = getConcurrentTaskRunningTime(runnable, concurrent);
            System.out.printf("%s: %s 并发，花费时间 %s ms\n", method, concurrent, cost);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getConcurrentTaskRunningTime(Runnable runnable, int loop) throws InterruptedException {
        long before = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(loop);
        executeConcurrentTaskCountDown(runnable, loop, latch);
        latch.await();
        long after = System.currentTimeMillis();
        return after - before;
    }
    private void executeConcurrentTask(Runnable runnable, long interval, int loop) {
        for (int i = 0; i < loop; i++) {
            executorService.submit(runnable);
            if (interval > 0) {
                sleep(interval);
            }
        }
    }
    private void executeConcurrentTaskCountDown(Runnable runnable, int loop, CountDownLatch latch) {
        for (int i = 0; i < loop; i++) {
            executorService.execute(() -> {
                runnable.run();
                latch.countDown();
            });
        }
    }
}
