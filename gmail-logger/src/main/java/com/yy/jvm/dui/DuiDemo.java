package com.yy.jvm.dui;

import com.yy.jvm.stack.Cat;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName DuiDemo
 * @Author yy
 * @Description
 * @Date 2021/10/13 14:31
 * @Version 1.0
 **/
public class DuiDemo {
    public void say(){
         Cat cat = new Cat();
    }

    public static void main(String[] args) {
        System.out.println("start...");
        try {
            TimeUnit.SECONDS.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
