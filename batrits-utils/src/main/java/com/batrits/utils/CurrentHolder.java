package com.batrits.utils;

public class CurrentHolder {
    private static final ThreadLocal<Integer> local=new ThreadLocal<>();

    public static void setCurrentId(Integer id){
        local.set(id);
    }

    public static Integer getCurrentId(){
        return local.get();
    }

    public static void remove(){
        local.remove();
    }
}
