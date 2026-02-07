package com.batrits.service;

import com.batrits.pojo.Emp;
import com.batrits.pojo.EmpQueryParam;
import com.batrits.pojo.LoginInfo;
import com.batrits.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam params);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getEmpInfoById(Integer id);

    void update(Emp emp);




    List<Emp> getAllEmps();

    Integer countByDeptId(Integer deptId);

    LoginInfo login(Emp emp);
}
