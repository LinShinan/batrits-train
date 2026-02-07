package com.batrits.service;


import com.batrits.pojo.PageResult;
import com.batrits.pojo.Student;
import com.batrits.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    Integer countByClazzId(Integer clazzId);

    PageResult<Student> page(StudentQueryParam params);

    void save(Student student);

    Student getStudentById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void updateViolation(Integer id, Integer score);
}
