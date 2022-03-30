package com.igevin.concurrent.task;

import com.igevin.concurrent.writedb.operationservice.EntityInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ConcurrentInsertTasks {
    @Autowired
    private EntityInsertion entityInsertion;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void executeConcurrentAddByLock() {
        int loop = 5;
        for (int i = 0; i < loop; i++) {
            executorService.submit(entityInsertion::addEntityByLock);
        }
    }

    public void executeConcurrentAddByTransaction() {
        int loop = 5;
        for (int i = 0; i < loop; i++) {
            executorService.submit(entityInsertion::addEntityByTransaction);
            sleep(100);
        }
    }

    public void executeConcurrentAddByTransactionWithLock() {
        int loop = 5;
        for (int i = 0; i < loop; i++) {
            executorService.submit(entityInsertion::addEntityByTransactionWithLock);
        }
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
