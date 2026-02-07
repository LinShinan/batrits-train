package com.batrits;

public class ThreadLocalTest {
    private static final ThreadLocal<String> local=new ThreadLocal<>();

    public static void main(String[] args){
        local.set("hello");

        new Thread(new Runnable(){
            @Override
            public void run(){
                local.set("hahaha");
                System.out.println(Thread.currentThread().getName()+":"+local.get());
            }
        }).start();

        System.out.println(Thread.currentThread().getName()+":"+local.get());
        local.remove();

        System.out.println(Thread.currentThread().getName()+":"+local.get());
    }
}
