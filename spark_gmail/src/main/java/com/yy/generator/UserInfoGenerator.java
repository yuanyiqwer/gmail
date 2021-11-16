package com.yy.generator;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.*;

import com.alibaba.fastjson.JSON;
import com.yy.Bean.Person;
import com.yy.lock.Point;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.yy.utils.DateUtil.*;
import static com.yy.utils.FileUtil.*;

/** @ClassName UserInfoGenerator @Author yy @Description @Date 2021/11/8 10:45 @Version 1.0 */
@Slf4j
public class UserInfoGenerator extends Generator {
    @Override
    void start() {}

    @Override
    void stop() {}

    @Override
    void task() throws IOException {
        RandomAccessFile file = createFile("F:\\Desktop\\random.txt", "rw", 0);
        if (file == null) {
            return;
        }
        for (int i = 0; i < 10000; i++) {
            long length = file.length();
            file.seek(length);
            file.write((providerItem() + "\r\n").getBytes());
        }
    }

    String providerItem() {
        String[] names = new String[] {"1", "2", "xx", "zs", "ls", "ww", "wemz"};
        final Random random = new Random();
        int i = random.nextInt(10000);
        final Long ts = getTimestamp(Long.valueOf(i), (i > 3333 ? (-1) : 1));
        final Person person =
                new Person(names[random.nextInt(names.length)], UUID.randomUUID().toString(), ts);
        String str = JSON.toJSONString(person);
        return str;
    }

    Object o = new Object();
    HashSet<String> set = new HashSet<>();
    int t = 3;

    /** * 线程交替打印 */
    void task2() {
        synchronized (o) {
            for (int i = 0; i < 3; i++) {
                System.out.println("this is ==>\t" + Thread.currentThread().getName());

                try {

                    t = t - 1;
                    System.out.println(t);
                    if (t == 0) {

                        t = 3;
                        o.notifyAll();
                        continue;
                    }

                    o.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    final ReentrantLock lock = new ReentrantLock();
    final Condition c1 = lock.newCondition();
    final Condition c2 = lock.newCondition();
    final Condition c3 = lock.newCondition();

    int x =1;
    /** * 多线程顺序交替打印，ReentrantLock
     * 线程操作资源类
     * 先判断，在操作，最后通知
     * 防止虚假唤醒
     * */
    void task3() {

        lock.lock();

        try {
            for (int i = 0; i < 15; i++) {
//                System.out.println("===============================>\t" );
                if (Thread.currentThread().getName().equals("a")){
                    while (x!=1){
                        c1.await();
                    }
                    System.out.println("this is ====》\t"+Thread.currentThread().getName());
                    x=2;
                    c2.signal();
                }
                if (Thread.currentThread().getName().equals("b")){
                    while (x!=2){
                        c2.await();
                    }
                    System.out.println("this is ====》\t"+Thread.currentThread().getName());
                    x=3;
                    c3.signal();
                }
                if (Thread.currentThread().getName().equals("c")){
                    while (x!=3){
                        c3.await();
                    }
                    System.out.println("this is ====》\t"+Thread.currentThread().getName());
                    x=1;
                    c1.signal();



                }

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /***
     * 读写锁
     * StampedLock
     */
    void task4(){

    }


    public static void main(String[] args) {
        final UserInfoGenerator userInfoGenerator = new UserInfoGenerator();
        final Point point = new Point();

        new Thread(
                        () -> {
                            userInfoGenerator.task3();
                        },
                        "a")
                .start();
        new Thread(
                        () -> {
                            userInfoGenerator.task3();
                        },
                        "b")
                .start();
                new Thread(
                                () -> {
                                    userInfoGenerator.task3();
                                },
                                "c")
                        .start();
    }

}
