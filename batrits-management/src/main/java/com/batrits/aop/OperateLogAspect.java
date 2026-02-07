package com.batrits.aop;

import com.batrits.mapper.OperateLogMapper;
import com.batrits.pojo.OperateLog;
import com.batrits.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class OperateLogAspect {

    private final OperateLogMapper operateLogMapper;

    @Autowired
    public OperateLogAspect(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }


    @Around("@annotation(com.batrits.anno.OperateLog)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();

        OperateLog opLog = new OperateLog();
        opLog.setOperateEmpId(CurrentHolder.getCurrentId());
        opLog.setOperateTime(LocalDateTime.now());
        opLog.setClassName(pjp.getTarget().getClass().getName());
        opLog.setMethodName(pjp.getSignature().getName());
        opLog.setMethodParams(Arrays.toString(pjp.getArgs()));
        opLog.setReturnValue(result!=null?result.toString():"void");
        opLog.setCostTime(endTime-startTime);

        log.info("记录操作日志：{}",opLog);
        operateLogMapper.insert(opLog);
        return result;
    }



}
