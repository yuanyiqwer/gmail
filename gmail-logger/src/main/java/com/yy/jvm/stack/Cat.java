package com.yy.jvm.stack;

/**
 * @ClassName Cat
 * @Author yy
 * @Description
 * @Date 2021/10/12 14:13
 * @Version 1.0
 **/
public class Cat extends Animal implements Action{
    public Cat(){
        System.out.println("我是喵");
    }
    @Override
    public void say() {
        System.out.println("喵喵喵");;
    }

    @Override
    public void execAction() {
        System.out.println("喵吃老鼠");
    }
}
