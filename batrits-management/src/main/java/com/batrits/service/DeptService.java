package com.batrits.service;


import com.batrits.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();

    void deleteById(int id);

    void add(Dept dept);

    void update(Dept dept);

    Dept getById(Integer deptId);
}
