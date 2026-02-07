package com.batrits.service.impl;

import com.batrits.exception.DataDependencyException;
import com.batrits.mapper.DeptMapper;
import com.batrits.mapper.EmpMapper;
import com.batrits.pojo.Dept;
import com.batrits.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }



    @Transactional
    @Override
    public void deleteById(int id) {
        Integer count = empMapper.countByDeptId(id);
        if(count!=null && count>0){
            throw new DataDependencyException("该部门下有员工，不能删除");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        LocalDateTime present= LocalDateTime.now();
        dept.setCreateTime(present);
        dept.setUpdateTime(present);
        deptMapper.add(dept);
    }

    @Override
    public void update(Dept dept) {
        LocalDateTime present=LocalDateTime.now();
        dept.setUpdateTime(present);
        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer deptId) {
        return deptMapper.getById(deptId);
    }
}
