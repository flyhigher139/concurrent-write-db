package com.igevin.concurrent.writedb.operationservice;

import com.igevin.concurrent.writedb.dao.ConcurrentEntity;
import com.igevin.concurrent.writedb.dao.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntityInsertion {
    @Autowired
    private DataMapper dataMapper;


    public Integer addEntityByLock() {
        synchronized (this) {
            return addEntity();
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Integer addEntityByTransaction() {
        return addEntity();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer addEntityByTransactionWithLock() {
        return addEntity(true);
    }

    private Integer addEntity() {
        return addEntity(false);
    }

    private Integer addEntity(boolean dbWriteLock) {
        ConcurrentEntity entity = dbWriteLock ? dataMapper.getLatestConcurrentEntityWithWriteLock() : dataMapper.getLatestConcurrentEntity();
        int nextNumber = entity == null ? 1 : getNextNumberByCode(entity.getCode());
        String code = String.format("CON_%04d", nextNumber);
        return dataMapper.insertConcurrentEntity(new ConcurrentEntity(code));
    }

    private int getNextNumberByCode(String code) {
        int index = code.lastIndexOf("_");
        String number = code.substring(index + 1);
        return Integer.parseInt(number) + 1;
    }

}
