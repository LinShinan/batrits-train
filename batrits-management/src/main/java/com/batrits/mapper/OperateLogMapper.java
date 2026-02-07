package com.batrits.mapper;

import com.batrits.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    @Insert("insert into operate_log(id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time)" +
            "values (#{id},#{operateEmpId},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insert(OperateLog operateLog);
}
