package com.igevin.concurrent;

import com.igevin.concurrent.task.ConcurrentInsertTasks;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ConcurrentInsertTaskTest {
    @Autowired
    private ConcurrentInsertTasks concurrentInsertTasks;

    @Test
    public void testExecuteConcurrentAddByLock() {
        log.info("start to test...");
        concurrentInsertTasks.executeConcurrentAddByLock();
    }

    @Test
    public void testExecuteConcurrentAddByTransaction(){
        concurrentInsertTasks.executeConcurrentAddByTransaction();
    }

    @Test
    public void testExecuteConcurrentAddByTransactionWithLock() {
        concurrentInsertTasks.executeConcurrentAddByTransactionWithLock();
    }

}
