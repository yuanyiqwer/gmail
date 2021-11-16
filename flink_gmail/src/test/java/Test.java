import sun.util.resources.cldr.zh.TimeZoneNames_zh;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName Test
 * @Author yy
 * @Description
 * @Date 2021/9/17 15:47
 * @Version 1.0
 **/

public class Test {

    public static void say() {
        System.out.println("t start");
        //park可以中断但是不会有中断异常
        LockSupport.park();
        System.out.println("t over");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ArrayList<Future<String>> futures = new ArrayList<>();
        final FutureTask<String> task1 = new FutureTask<>(() -> {

            TimeUnit.SECONDS.sleep(3);
            System.out.println("task1");
            return "task1";
        });
        final FutureTask<String> task2 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("task2");
            return "task2";
        });
        futures.add(task2);
        futures.add(task1);

        new Thread(task1).start();
        new Thread(task2).start();

        for (Future<String> future : futures) {
            System.out.println(future.get());
            System.out.println(1);
        }



    }

}
