package com.batrits.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate end;

    public Integer getStart(){
        return (page-1)*pageSize;
    }
}
