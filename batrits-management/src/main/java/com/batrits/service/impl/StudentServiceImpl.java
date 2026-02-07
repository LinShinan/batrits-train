package com.batrits.service.impl;

import com.batrits.mapper.StudentMapper;
import com.batrits.pojo.PageResult;
import com.batrits.pojo.Student;
import com.batrits.pojo.StudentQueryParam;
import com.batrits.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Integer countByClazzId(Integer clazzId) {
        return studentMapper.countByClazzId(clazzId);
    }

    @Override
    public PageResult<Student> page(StudentQueryParam params) {
        PageHelper.startPage(params.getPage(),params.getPageSize());
        List<Student> list=studentMapper.list(params);
        Page<Student> page=(Page<Student>) list;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void save(Student student) {
        studentMapper.save(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.deleteBatch(ids);
    }

    @Transactional
    @Override
    public void updateViolation(Integer id, Integer score) {
       studentMapper.updateViolation(id,score);
    }
}
