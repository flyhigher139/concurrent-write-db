package com.igevin.concurrent.task;

import com.igevin.concurrent.writedb.operationservice.EntityUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;


@Component
@DependsOn({"entityUpdate", "helper"})
public class ConcurrentUpdateTasks {
    @Autowired
    private EntityUpdate entityUpdate;
    @Autowired
    private Helper helper;
    @Autowired
    private ExecutorService executorService;

    private final int id = 1;

    public void executeConcurrentAddAtomically() {
        helper.executeConcurrentTask(() -> entityUpdate.increaseVisitCountAtomically(id));
    }

    public void executeConcurrentAddByLock() {
        helper.executeConcurrentTask(() -> entityUpdate.increaseVisitCountByLock(id));
    }

    public void executeConcurrentAddByTransaction() {
        helper.executeConcurrentTask(() -> entityUpdate.increaseVisitCountByTransaction(id));
    }

    public void executeConcurrentAddOptimistically() {
        helper.executeConcurrentTask(() -> entityUpdate.increaseVisitCountOptimistically(id));
    }

    public void executeConcurrentAddOptimisticallyWithRetry() {
        helper.executeConcurrentTask(() -> entityUpdate.increaseVisitCountOptimisticallyWithRetry(id));
    }

    public void getPerformances(int concurrent) {
//        helper.getPerformance(concurrent, "executeConcurrentAddAtomically",
//                () -> entityUpdate.increaseVisitCountAtomically(id));
//        helper.getPerformance(concurrent, "executeConcurrentAddByLock",
//                () -> entityUpdate.increaseVisitCountByLock(id));
//        helper.getPerformance(concurrent, "executeConcurrentAddByTransaction",
//                () -> entityUpdate.increaseVisitCountByTransaction(id));
        helper.getPerformance(concurrent, "executeConcurrentAddOptimisticallyWithRetry",
                () -> entityUpdate.increaseVisitCountOptimisticallyWithRetry(id));
    }


}
