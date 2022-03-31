package com.igevin.concurrent.writedb.operationservice;

import com.igevin.concurrent.task.Helper;
import com.igevin.concurrent.writedb.dao.ConcurrentVisit;
import com.igevin.concurrent.writedb.dao.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntityUpdate {
    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private Helper helper;

    public Integer increaseVisitCountAtomically(int id) {
        return dataMapper.increaseConcurrentVisitAtomic(id);
    }

    public Integer increaseVisitCountByLock(int id) {
        synchronized (this) {
            return increaseVisitCount(id);
        }
    }

    @Transactional()
    public Integer increaseVisitCountByTransaction(int id) {
        return increaseVisitCount(id, true);
    }

    public Integer increaseVisitCountOptimistically(int id) {
        ConcurrentVisit concurrentVisit = dataMapper.getConcurrentVisitObject(id);
        return dataMapper.increaseConcurrentVisitOptimistically(concurrentVisit.increaseVisit().updateUpdateTime());
    }

    public Integer increaseVisitCountOptimisticallyWithRetry(int id) {
        int result = 0;
        int maxRetry = 5;
        long interval = 20L;

        for (int i = 0; i < maxRetry; i++) {
            result = increaseVisitCountOptimistically(id);
            if (result > 0) {
                break;
            }
            interval = interval + i * 50;
            helper.sleep(interval);

        }
        return result;
    }

    private Integer increaseVisitCount(int id) {
        return increaseVisitCount(id, false);
    }

    private Integer increaseVisitCount(int id, boolean withLock) {
        ConcurrentVisit concurrentVisit = withLock ? dataMapper.getConcurrentVisitObjectWithLock(id)
                : dataMapper.getConcurrentVisitObject(id);
        return dataMapper.increaseConcurrentVisit(concurrentVisit.increaseVisit().updateUpdateTime());
    }
}
