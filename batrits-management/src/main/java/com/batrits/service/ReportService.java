package com.batrits.service;

import com.batrits.pojo.JobOption;
import com.batrits.pojo.StudentCountOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 查询员工的职位与对应人数
     * @return
     */
    JobOption getEmpJobData();
    /**
     * 查询员工的性别与对应人数
     * @return
     */
    List<Map<String,Object>> getEmpGenderData();

    StudentCountOption getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
