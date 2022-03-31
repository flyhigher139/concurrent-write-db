package com.igevin.concurrent.writedb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("SELECT * FROM concurrent_visit\n" +
            "WHERE id = #{id}")
    ConcurrentVisit getConcurrentVisitObject(int id);

    @Select("SELECT * FROM concurrent_visit\n" +
            "WHERE id = #{id}\n" +
            "FOR UPDATE;")
    ConcurrentVisit getConcurrentVisitObjectWithLock(int id);

    @Update("UPDATE concurrent_visit\n" +
            "SET visit = visit + 1, update_time = NOW()\n" +
            "WHERE id = #{id};")
    Integer increaseConcurrentVisitAtomic(int id);

    @Update("UPDATE concurrent_visit\n" +
            "SET visit = #{visit}, update_time = #{updateTime}\n" +
            "WHERE id = #{id};")
    Integer increaseConcurrentVisit(ConcurrentVisit concurrentVisit);

    @Update("UPDATE concurrent_visit\n" +
            "SET visit = #{visit}, update_time = #{updateTime}, version = #{version} + 1\n" +
            "WHERE id = #{id} and version = #{version};")
    Integer increaseConcurrentVisitOptimistically(ConcurrentVisit concurrentVisit);
}
