package com.yy.jvm.dui;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test
 * @Author yy
 * @Description
 * @Date 2021/10/14 10:00
 * @Version 1.0
 **/
public class Test {
    final byte[] bytes = new byte[(int) (Math.random()* 10 * 1024)];


    public static void oom() {

        ArrayList<Test> lst = new ArrayList<>();
        for (;;){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lst.add(new Test());
        }


    }

    public static void main(String[] args) {
//        double initMemory = Runtime.getRuntime().totalMemory();
//        double maxMemory = Runtime.getRuntime().maxMemory();
//        System.out.println("init\t" + (initMemory / 1024 / 1024));
//        System.out.println("max\t" + (maxMemory / 1024 / 1024));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        oom();

    }
}
