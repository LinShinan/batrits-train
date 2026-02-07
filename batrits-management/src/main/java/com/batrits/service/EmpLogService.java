package com.batrits.service;

import com.batrits.pojo.EmpLog;

public interface EmpLogService {

    /**
     * 插入员工操作日志
     * @param empLog
     */
    void insertLog(EmpLog empLog);
}
