package com.batrits.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer id;
    private String name;
    private String room;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer masterId;// 班主任id
    private Integer subject;//1.java 2.前端 3.大数据 4.Python 5.Go  6.嵌入式
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String masterName;
    private String status;
}
