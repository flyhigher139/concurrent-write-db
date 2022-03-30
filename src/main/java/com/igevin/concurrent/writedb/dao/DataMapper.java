package com.igevin.concurrent.writedb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataMapper {
    @Insert("INSERT INTO concurrent_entity (code, create_time, update_time)\n" +
            "VALUES (#{code}, #{createTime}, #{updateTime});")
    Integer insertConcurrentEntity(ConcurrentEntity concurrentEntity);


    @Select("SELECT * FROM concurrent_entity\n" +
            "ORDER BY code DESC\n" +
            "LIMIT 1;")
    ConcurrentEntity getLatestConcurrentEntity();
    @Select("SELECT * FROM concurrent_entity\n" +
            "ORDER BY code DESC\n" +
            "LIMIT 1\n" +
            "FOR UPDATE;")
    ConcurrentEntity getLatestConcurrentEntityWithWriteLock();
}
