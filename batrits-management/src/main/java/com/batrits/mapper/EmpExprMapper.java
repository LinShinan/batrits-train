package com.batrits.mapper;

import com.batrits.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpExprMapper {
//    @Insert("insert into emp_expr(emp_id, begin, end, company, job)" +
//            "values(#{empId},#{begin},#{end},#{company},#{job});")
//    void insertExpr(EmpExpr empExpr);

    void insertBatch( @Param("empExprList") List<EmpExpr> empExprList);


    void deleteBatch(List<Integer> ids);

//    @Select("select id,emp_id,begin,end,company,job from emp_expr where emp_id=#{id}")
//    List<EmpExpr> getEmpExprByEmpId(Integer id);
}
