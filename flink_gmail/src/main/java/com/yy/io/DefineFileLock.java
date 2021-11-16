package com.yy.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName DefineFileLock
 * @Author yy
 * @Description
 * @Date 2021/9/15 17:42
 * @Version 1.0
 **/
public class DefineFileLock {

    public static void main(String[] args) throws IOException {
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10,
                50,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5)
        );
        System.out.println(1);


//        FileLock lock = channel.tryLock(0,Long.MAX_VALUE,true);
        for (int i = 0; i < 1; i++) {
            pool.execute(() -> {

                for (int i1 = 0; i1 < Integer.MAX_VALUE; i1++) {


                    try {
                        LockSupport.park();
                        // TODO: 2021/9/16 出现filelock失效是应为gc回收导致lock失效
                        FileChannel channel = new FileInputStream(new File("F:\\Desktop\\data.txt")).getChannel();
                        FileLock lock = channel.lock(0, Long.MAX_VALUE, true);
                        System.out.println(lock.isShared());
//                    System.out.println(lock==null);
                        ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);
                        int read = channel.read(buffer);
                        System.out.println("size\t:" + read);
                        System.out.println(new String(buffer.array()));
                        buffer.clear();
                        TimeUnit.SECONDS.sleep(1);
//                    lock.release();
//                    return;
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            });
        }


    }

}
