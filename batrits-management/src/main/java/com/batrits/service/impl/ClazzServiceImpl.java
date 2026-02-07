package com.batrits.service.impl;

import com.batrits.exception.DataDependencyException;
import com.batrits.mapper.ClazzMapper;
import com.batrits.pojo.Clazz;
import com.batrits.pojo.ClazzQueryParam;
import com.batrits.pojo.PageResult;
import com.batrits.service.ClazzService;
import com.batrits.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentService studentService;
    @Override
    public PageResult<Clazz> page(ClazzQueryParam params) {

        List<Clazz> allClazzs = clazzMapper.page(params);

        LocalDate present = LocalDate.now();
        for(Clazz clazz:allClazzs){
           if(present.isAfter(clazz.getEndDate())){
               clazz.setStatus("已结束");
           }else if(present.isBefore(clazz.getBeginDate())){
               clazz.setStatus("未开班");
           }else{
               clazz.setStatus("在读中");
           }
        }

        Long total = clazzMapper.getTotal(params);
        return new PageResult<>(total,allClazzs);
    }

    @Override
    public void save(Clazz clazz) {
        clazzMapper.save(clazz);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        return clazzMapper.getClazzById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        if(id==null||id<=0){
            throw new IllegalArgumentException("id不能为空而为正整数");
        }
        Integer count = studentService.countByClazzId(id);
        if(count>0){
            throw new DataDependencyException("该班级下有学生，不能删除");
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> list() {
        return clazzMapper.list();
    }
}
