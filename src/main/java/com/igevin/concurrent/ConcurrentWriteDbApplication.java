package com.igevin.concurrent;

import com.igevin.concurrent.task.ConcurrentInsertTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConcurrentWriteDbApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConcurrentWriteDbApplication.class, args);
        ConcurrentInsertTasks concurrentInsertTasks = context.getBean(ConcurrentInsertTasks.class);
        concurrentInsertTasks.executeConcurrentAddByLock();
//        concurrentInsertTasks.executeConcurrentAddByTransaction();
//        concurrentInsertTasks.executeConcurrentAddByTransactionWithLock();
    }

}
