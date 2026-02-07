package com.batrits.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;//主键id
    private String name;
    private String no;//学号
    private Integer gender;//性别 1.男 2.女
    private String phone;//手机号
    private String idCard;//身份证号码
    private Integer isCollege;//是否来自院校
    private String address;//联系地址
    private Integer degree;
    private LocalDate graduationDate; //毕业时间
    private Integer clazzId;//班级id
    private Integer violationCount;//违纪次数
    private Integer violationScore;//违纪积分
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    private String clazzName;//班级名称
}
