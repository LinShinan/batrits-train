package com.batrits.controller;

import com.batrits.anno.OperateLog;
import com.batrits.pojo.Clazz;
import com.batrits.pojo.ClazzQueryParam;
import com.batrits.pojo.PageResult;
import com.batrits.pojo.Result;
import com.batrits.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @GetMapping
    public Result page( ClazzQueryParam params){
        log.info("分页查询{},{},{},{},{}",params);
        PageResult<Clazz> result = clazzService.page(params);
        return Result.success(result);
    }


    @OperateLog
    @PostMapping
    public Result save(@RequestBody Clazz clazz){

        clazzService.save(clazz);
        log.info("新增员工:{}",clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable("id") Integer id){
        Clazz clazz = clazzService.getClazzById(id);
        return Result.success(clazz);
    }

    @OperateLog
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级信息:{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @OperateLog
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id){
        log.info("删除id为{}的班级",id);
        clazzService.deleteById(id);
        return Result.success();
    }

   @GetMapping("/list")
    public Result getAllClazzs(){
       List<Clazz> allClazzs =clazzService.list();
       return Result.success(allClazzs);
   }

}
