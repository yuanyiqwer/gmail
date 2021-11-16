package com.yy.jvm.stack;

import org.jetbrains.annotations.NotNull;

/**
 * @ClassName Animal
 * @Author yy
 * @Description
 * @Date 2021/10/12 13:41
 * @Version 1.0
 **/
public class Animal {

    public Animal(){
        System.out.println("我是一只动物");
    }
    public  void  say(){
        System.out.println("呜呜呜呜......");
    }
    public void  AnimalSay(@NotNull Animal animal) {
        animal.say();
    }
    public void  AnimalAction(@NotNull Action action) {
        action.execAction();
    }
    public static void main(String[] args) {
        final Animal test = new Animal();
        test.AnimalSay(new Cat());
        test.AnimalSay(new Dog());
        test.AnimalAction(null);
        test.AnimalAction(new Dog());

    }
}



