package com.yy;

import com.yy.task.RunSparkTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/** Hello world! */
@Slf4j
@SpringBootApplication
public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       new SpringApplicationBuilder(App.class)
               .web(WebApplicationType.NONE)
               .run(args);
//        final FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new RunSparkTask());
//        new Thread(booleanFutureTask).start();
//        System.out.println(booleanFutureTask.get());
        final RunSparkTask sparkTask = new RunSparkTask();
        final boolean rt = sparkTask.run();
        log.info("spark run result=>\t{}",rt);

    }
}
