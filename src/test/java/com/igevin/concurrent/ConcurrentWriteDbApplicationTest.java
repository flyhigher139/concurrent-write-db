package com.igevin.concurrent;

import com.igevin.concurrent.writedb.dao.ConcurrentEntity;
import com.igevin.concurrent.writedb.dao.DataMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConcurrentWriteDbApplicationTest {
    @Autowired
    private DataMapper dataMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddConcurrentEntity() {
        ConcurrentEntity entity = new ConcurrentEntity("CON_0000");
        Integer result = dataMapper.insertConcurrentEntity(entity);
        Assertions.assertEquals(result, 1);
    }
}
