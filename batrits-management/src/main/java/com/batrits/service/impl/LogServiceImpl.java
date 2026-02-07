package com.batrits.service.impl;

import com.batrits.mapper.LogMapper;
import com.batrits.pojo.LogQueryParam;
import com.batrits.pojo.OperateLog;
import com.batrits.pojo.PageResult;
import com.batrits.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public PageResult<OperateLog> page(LogQueryParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        List<OperateLog> list = logMapper.list();
        Page<OperateLog> p = (Page<OperateLog>)list;
        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
