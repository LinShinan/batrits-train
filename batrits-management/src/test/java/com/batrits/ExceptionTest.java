package com.batrits;

import com.batrits.exception.DataDependencyException;

public class ExceptionTest {

    public static void main(String[] args){
        throw new DataDependencyException("班级下有学生");
    }
}
