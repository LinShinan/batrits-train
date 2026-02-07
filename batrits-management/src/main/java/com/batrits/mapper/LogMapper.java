package com.batrits.mapper;

import com.batrits.pojo.LogQueryParam;
import com.batrits.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select o.id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time,emp.name as operate_emp_name  " +
            " from operate_log o left join emp on operate_emp_id = emp.id order by operate_time desc")
    List<OperateLog> list();
}
