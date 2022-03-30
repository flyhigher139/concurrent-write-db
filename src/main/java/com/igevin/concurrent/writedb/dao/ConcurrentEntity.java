package com.igevin.concurrent.writedb.dao;

import lombok.Data;

import java.util.Date;

@Data
public class ConcurrentEntity {
    private Integer id;
    private String code;
    private Date createTime;
    private Date updateTime;

    public ConcurrentEntity(String code) {
        this.code = code;
        createTime = new Date();
        updateTime = new Date();
    }
}
