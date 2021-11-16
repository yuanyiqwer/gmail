package com.yy.lock;

import com.google.common.base.Function;

import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName TEST
 * @Author yy
 * @Description
 * @Date 2021/11/9 14:11
 * @Version 1.0
 **/
public class TEST implements Runnable {
    @Override
    public void run() {

    }

    public static void main(String[] args) throws IOException {

        Arrays.stream(args).forEach(String::length);
//        final Function<String, Integer> stringIntegerFunction = String::length;
//        final Function<String, Integer> function = Integer::valueOf;
//        System.out.println();
        new Thread(TEST::new);
        final Point point = new Point();
        //        new Thread(()->{
        //            for (int i = 0; i <10;i++){
        //                point.move(1,2);
        //            }
        //
        //        }).start();
        //        new Thread(()->{
        //            for (int i = 0; i <10;i++){
        //                point.move(1,2);
        //            }
        //        }).start();

        new Thread(
                        () -> {
                            for (int i = 0; i < 10; i++) {
                                point.moveIfAtOrigin(1,1);
                            }
                        })
                .start();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        point.moveIfAtOrigin(1,1);
                    }
                }).start();
        System.out.println(point.getX());
        System.out.println(point.getY());

        while (true){
            final int read = System.in.read();
            System.out.println(read);
        }


    }
}
