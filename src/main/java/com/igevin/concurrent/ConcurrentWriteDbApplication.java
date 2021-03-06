package com.igevin.concurrent;

import com.igevin.concurrent.task.ConcurrentInsertTasks;
import com.igevin.concurrent.task.ConcurrentUpdateTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ConcurrentWriteDbApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConcurrentWriteDbApplication.class, args);
        ConcurrentInsertTasks concurrentInsertTasks = context.getBean(ConcurrentInsertTasks.class);
//        concurrentInsertTasks.executeConcurrentAddByLock();
//        concurrentInsertTasks.executeConcurrentAddByTransaction();
//        concurrentInsertTasks.executeConcurrentAddByTransactionWithLock();
        ConcurrentUpdateTasks concurrentUpdateTasks = context.getBean(ConcurrentUpdateTasks.class);
//        concurrentUpdateTasks.executeConcurrentAddAtomically();;
//        concurrentUpdateTasks.executeConcurrentAddByLock();;
//        concurrentUpdateTasks.executeConcurrentAddByTransaction();;
//        concurrentUpdateTasks.executeConcurrentAddOptimistically();;
//        concurrentUpdateTasks.executeConcurrentAddOptimisticallyWithRetry();
        int concurrent = 1000;
//        getInsertPerformance(concurrent, concurrentInsertTasks);
        getUpdatePerformance(concurrent, concurrentUpdateTasks);
    }

    private static void getInsertPerformance(int concurrent, ConcurrentInsertTasks bean) {
        bean.getPerformances(concurrent);
    }

    private static void getUpdatePerformance(int concurrent, ConcurrentUpdateTasks bean) {
        bean.getPerformances(concurrent);
    }

    @Bean
    public ExecutorService getDefaultFixedThreadPool() {
        return Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors() + 1);
    }

}
