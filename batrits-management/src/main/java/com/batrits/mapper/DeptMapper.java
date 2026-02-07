package com.batrits.mapper;

import com.batrits.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //method1 手动映射
//    @Results({
//            @Result(column="create_time", property="createTime"),
//            @Result(column="update_time", property="updateTime")
//    })

    //method2 起别名
//    @Select("select id, name, create_time as createTime, update_time as updateTime from dept order by update_time desc")
    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    List<Dept> findAll();


    @Delete("delete from dept where id=#{id}")
    void deleteById(int id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id=#{deptId}")
    Dept getById(Integer deptId);
}
