package com.batrits.mapper;

import com.batrits.pojo.Student;
import com.batrits.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Select("select count(*) from student where clazz_id=#{clazzId}")
    Integer countByClazzId(Integer clazzId);

    List<Student> list(StudentQueryParam params);

    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time)" +
            "values" +
            "(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},now(),now())")
    void save(Student student);

    @Select(" select id,name,no,gender,phone,id_card,is_college," +
            "        address,degree, graduation_date, clazz_id," +
            "        violation_count,violation_score,create_time,update_time" +
            "        from student where id=#{id}")
    Student getStudentById(Integer id);

    /**
     * 更新学生信息
     * @param student 学生对象
     */
    void update(Student student);

    /**
     * 根据id删除学生信息
     * @param ids 学生id列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新学生违规次数和违规分数
     * @param id 学生id
     * @param score 违规分数
     */
    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score} " +
            "where id=#{id}")
    void updateViolation(Integer id, Integer score);

    @Select("select c.name cname, count(s.id) cnt " +
            "from clazz c " +
            "left join student s on c.id = s.clazz_id " +
            "group by c.name order by count(s.id) desc")
    List<Map<String,Object>> getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
