package com.igevin.concurrent.writedb.dao;

import lombok.Data;

import java.util.Date;

@Data
public class ConcurrentVisit {
    private Integer id;
    private String resourceKey;
    private int visit;
    private int version;
    private Date createTime;
    private Date updateTime;

    public ConcurrentVisit increaseVisit() {
        visit++;
        return this;
    }

    public ConcurrentVisit updateUpdateTime() {
        updateTime = new Date();
        return this;
    }
}
