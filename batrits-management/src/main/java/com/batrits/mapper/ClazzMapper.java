package com.batrits.mapper;

import com.batrits.pojo.Clazz;
import com.batrits.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 获取所有班级
     * @return 所有班级的列表
     */

    List<Clazz> page(ClazzQueryParam params);

    /**
     * 获取班级的总数量
     * @param params 班级查询参数
     * @return 班级的总数量
     */
    Long getTotal(ClazzQueryParam params);
    /**
     * 保存班级信息
     * @param clazz 班级对象
     */

    @Options(useGeneratedKeys=true,keyProperty="id")
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},now(),now())")
    void save(Clazz clazz);
    /**
     * 根据班级id查询班级信息
     * @param id 班级id
     * @return 班级对象
     */
    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz where id=#{id};")
    Clazz getClazzById(Integer id);

    void update(Clazz clazz);
    /**
     * 根据班级id删除班级信息
     * @param id 班级id
     */
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz")
    List<Clazz> list();
}
