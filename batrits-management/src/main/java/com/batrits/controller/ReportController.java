package com.batrits.controller;

import com.batrits.pojo.JobOption;
import com.batrits.pojo.Result;
import com.batrits.pojo.StudentCountOption;
import com.batrits.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


@RequestMapping("/report")
@RestController
public class ReportController {

    private static final Logger log=LoggerFactory.getLogger(ReportController.class);
    @Autowired
    private ReportService reportService;


    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("查询员工的职位与对应人数");
        JobOption jobOption =reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("查询员工的性别与对应人数");
        List<Map<String,Object>> empGenderData=reportService.getEmpGenderData();
        return Result.success(empGenderData);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("查询每个班级的人数");
        StudentCountOption clazzStuCnt=reportService.getStudentCountData();
        return Result.success(clazzStuCnt);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        List<Map<String,Object>> studentDegreeData=reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);
    }
}
