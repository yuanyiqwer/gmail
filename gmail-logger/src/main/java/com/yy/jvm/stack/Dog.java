package com.yy.jvm.stack;

/**
 * @ClassName Dog
 * @Author yy
 * @Description
 * @Date 2021/10/12 14:13
 * @Version 1.0
 **/
public class Dog extends Animal implements Action {
    public Dog() {
        System.out.println("我是狗");
    }

    @Override
    public void say() {
        System.out.println("汪汪汪");
        ;
    }

    @Override
    public void execAction() {
        System.out.println("狗吃屎");
    }
}
