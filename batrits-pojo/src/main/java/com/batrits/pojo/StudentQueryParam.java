package com.batrits.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private String name;
    private Integer degree;//1.初中 2.高中 3.大专 4.本科 5.硕士 6.博士
    private Integer clazzId;
    private Integer page=1;
    private Integer pageSize=10;

}
