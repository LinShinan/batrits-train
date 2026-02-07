package com.batrits.controller;


import com.batrits.pojo.Emp;
import com.batrits.pojo.EmpQueryParam;
import com.batrits.pojo.PageResult;
import com.batrits.pojo.Result;
import com.batrits.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

//    @GetMapping
//    public Result page(@RequestParam(defaultValue="1") Integer page,
//                       @RequestParam(defaultValue="10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate begin,
//                       @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end){
//        log.info("分页查询第{}页，每页{}条,{},{},{},{}",page,pageSize,name,gender,begin,end);
//
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result page(EmpQueryParam params){
        log.info("分页查询第{}页，每页{}条,{},{},{},{}",params);

        PageResult<Emp> pageResult = empService.page(params);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        empService.save(emp);
        log.info("新增员工:{}",emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Integer> ids){
        empService.delete(ids);
        log.info("删除员工:{}",ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getEmpInfoById(@PathVariable("id") Integer id){
        Emp emp=empService.getEmpInfoById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAllEmps(){
        List<Emp> list=empService.getAllEmps();
        return Result.success(list);
    }
}
