package com.batrits.mapper;

import com.batrits.pojo.Emp;
import com.batrits.pojo.EmpExpr;
import com.batrits.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    //------原始方法-----
//    @Select("select count(*) from emp left join dept on emp.dept_id=dept.id")
//    Long getCount();
//
//    @Select("select emp.*,dept.name as deptName from emp left join dept on emp.dept_id=dept.id" +
//            " order by emp.update_time desc limit #{start},#{pageSize}")
//    List<Emp> list(@Param("start")Integer start, @Param("pageSize") Integer pageSize);


    //----PageHelper 方法-----
//    @Select("select emp.*,dept.name as deptName from emp left join dept on emp.dept_id=dept.id" +
//            " order by emp.update_time desc")
//    List<Emp> list();

//        @Select("select emp.*,dept.name as deptName from emp left join dept on emp.dept_id=dept.id" +
//                "where e.name like concat('%',#{name},'%') and e.gender=#{gender} and e.entry_date between '#{begin}' and '#{end}'" +
//            " order by emp.update_time desc")
//        List<Emp> list(@Param("name")String name, @Param("gender")Integer gender,
//                       @Param("begin") LocalDate begin, @Param("end")LocalDate end);

    /**
     * 根据查询参数查询员工列表
     * @param params
     * @return
     */
    List<Emp> list(EmpQueryParam params);

    @Options(useGeneratedKeys=true,keyProperty="id")
    @Insert("insert into emp( username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values(#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void deleteBatch(List<Integer> ids);


//    @Select("select id,username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from emp where id=#{id}")
//    Emp getBaseInfoById(Integer id);
    /**
     * 根据员工id查询员工信息
     * @param id
     * @return
     */
    Emp getEmpInfoById(Integer id);

    void update(Emp emp);
    /**
     * 查询员工的职位与对应人数
     * 注意返回的其实是
     * // 每一行是一个 Map，包含两个键值对：
     * Map<String, Object> row = {
     *     "jobName": "班主任",  // String 类型
     *     "num": 5            // Integer 类型
     * }
     *
     * @return
     */
    List<Map<String,Object>> getEmpJobData();

    /**
     * 查询员工的性别与对应人数
     * @return
     */
    List<Map<String,Object>> getEmpGenderData();


    @Select("select count(*) from emp group by dept_id having dept_id=#{deptId}")
    Integer countByDeptId(Integer deptId);


    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
