package com.batrits.service.impl;

import com.batrits.mapper.EmpMapper;
import com.batrits.mapper.StudentMapper;
import com.batrits.pojo.JobOption;
import com.batrits.pojo.StudentCountOption;
import com.batrits.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportServiceImpl implements ReportService {
    
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> empJobData = empMapper.getEmpJobData();
        List<Object> jobNames = empJobData.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> nums = empJobData.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobNames,nums);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }

    @Override
    public StudentCountOption getStudentCountData() {
        List<Map<String, Object>> studentCountData = studentMapper.getStudentCountData();
        if(!CollectionUtils.isEmpty(studentCountData)){
            List<Object> cnames = studentCountData.stream().map(dataMap -> dataMap.get("cname")).toList();
            List<Object> cnts = studentCountData.stream().map(dataMap -> dataMap.get("cnt")).toList();
            return new StudentCountOption(cnames,cnts);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String,Object>> studentDegreeData =studentMapper.getStudentDegreeData();
        return studentDegreeData;
    }


}
