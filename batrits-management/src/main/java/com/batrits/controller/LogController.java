package com.batrits.controller;


import com.batrits.pojo.LogQueryParam;
import com.batrits.pojo.OperateLog;
import com.batrits.pojo.PageResult;
import com.batrits.pojo.Result;
import com.batrits.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;
    @GetMapping("/page")
    public Result page(LogQueryParam param){
        PageResult<OperateLog> result = logService.page(param);
        return Result.success(result);
    }

}
