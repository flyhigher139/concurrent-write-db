package com.igevin.concurrent;

import com.igevin.concurrent.task.ConcurrentUpdateTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConcurrentUpdateTaskTest {
    @Autowired
    private ConcurrentUpdateTasks concurrentUpdateTasks;
}
