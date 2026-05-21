package com.batrits.service.impl;

import com.batrits.mapper.EmpExprMapper;
import com.batrits.mapper.EmpMapper;
import com.batrits.pojo.*;
import com.batrits.service.EmpLogService;
import com.batrits.service.EmpService;
import com.batrits.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;


    //----原始方法-----
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total=empMapper.getCount();
//        //如果每页5
//        //索引
//        // 第1页：0-4 0-pageSize-1
//        // 第2页：5-9 pageSize-2*pageSize-1
//        // 第3页：10-14
//        Integer start=(page-1)*pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        return new PageResult<Emp>(total,rows);
//    }

    //----使用PageHelper分页插件-----
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end){
//        PageHelper.startPage(page,pageSize);
//        List<Emp> list = empMapper.list(name,gender,begin,end);
//        Page<Emp> p=(Page<Emp>) list;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam params){
        PageHelper.startPage(params.getPage(),params.getPageSize());
        List<Emp> list=empMapper.list(params);
        Page<Emp> p=(Page<Emp>) list;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

//    @Override
//    public void save(Emp emp) {
//        //插入员工基本信息
//        empMapper.insert(emp);
//
//        //插入员工工作经历
//        List<EmpExpr> exprList = emp.getExprList();
//        if(exprList!=null && !exprList.isEmpty()){
//            for(EmpExpr empExpr:exprList){
//                empExpr.setEmpId(emp.getId());
//                empExprMapper.insertExpr(empExpr);
//            }
//        }
//
//    }

    @Transactional
    @Override
    public void save(Emp emp){
        try {
            //插入员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //插入员工工作经历
            List<EmpExpr> empExprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(empExprList)){
                empExprList.forEach(empExpr->empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(empExprList);
            }
        } finally {
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }
    }


    @Transactional(rollbackFor={Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return;
        }
        empMapper.deleteBatch(ids);
        empExprMapper.deleteBatch(ids);
    }

    @Override
    public Emp getEmpInfoById(Integer id) {
        //method1
//        Emp empInfo=empMapper.getBaseInfoById(id);
//        List<EmpExpr> empExpr=empExprMapper.getEmpExprByEmpId(id);
//        empInfo.setExprList(empExpr);
        
        //method2
        return empMapper.getEmpInfoById(id);
    }

    @Transactional
    @Override
    public void update(Emp emp) {
        //修改基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        //修改工作经历
        //先删后增
        empExprMapper.deleteBatch(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        //如果有工作经历，才进行插入
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr->empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> getAllEmps(){
        return empMapper.list(null);
    }

    @Override
    public Integer countByDeptId(Integer deptId) {
        return empMapper.countByDeptId(deptId);
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp info = empMapper.selectByUsernameAndPassword(emp);

        if(info!=null){
            log.info("登录成功，员工信息{}", info);
            Map<String,Object> claims =new HashMap<>();
            claims.put("id",info.getId());
            claims.put("username",info.getUsername());
            String token = JwtUtils.generateToken(claims);
            return new LoginInfo(info.getId(),info.getUsername(),info.getName(),token);
        }
        return null;
    }


}
