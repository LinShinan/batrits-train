package com.batrits.service;

import com.batrits.pojo.LogQueryParam;
import com.batrits.pojo.OperateLog;
import com.batrits.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(LogQueryParam param);
}
