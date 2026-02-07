package com.batrits.controller;

import com.batrits.pojo.PageResult;
import com.batrits.pojo.Result;
import com.batrits.pojo.Student;
import com.batrits.pojo.StudentQueryParam;
import com.batrits.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

//    private final Logger log= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result page(StudentQueryParam params){
        log.info("分页查询学生信息:{}",params);
        PageResult<Student> pageResult=studentService.page(params);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("保存学生信息:{}",student);
        studentService.save(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable("id") Integer id){
        log.info("查询id为{}的学生信息",id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新学生信息:{}",student);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable("ids") List<Integer> ids){
        log.info("删除id为{}的学生信息",ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable("id") Integer id,
                                  @PathVariable("score") Integer score){
        log.info("更新id为{}的学生违规分数+{}",id,score);
        studentService.updateViolation(id,score);
        return Result.success();

    }
}
