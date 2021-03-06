package com.igevin.concurrent.task;

import com.igevin.concurrent.writedb.operationservice.EntityInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@DependsOn({"entityInsertion", "helper"})
public class ConcurrentInsertTasks {
    @Autowired
    private EntityInsertion entityInsertion;
    @Autowired
    private Helper helper;

    public void executeConcurrentAddByLock() {
        helper.executeConcurrentTask(entityInsertion::addEntityByLock2);
    }

    public void executeConcurrentAddByTransaction() {
        helper.executeConcurrentTask(entityInsertion::addEntityByTransaction, 100L);
    }

    public void executeConcurrentAddByTransactionWithLock() {
        helper.executeConcurrentTask(entityInsertion::addEntityByTransactionWithLock);
    }

    public void getPerformances(int concurrent) {
        helper.getPerformance(concurrent, "executeConcurrentAddByLock1", entityInsertion::addEntityByLock);
        helper.getPerformance(concurrent, "executeConcurrentAddByLock2", entityInsertion::addEntityByLock2);
        helper.getPerformance(concurrent, "executeConcurrentAddByTransactionWithLock", entityInsertion::addEntityByTransactionWithLock);
    }

}
