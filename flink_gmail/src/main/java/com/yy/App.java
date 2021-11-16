package com.yy;

import com.yy.souce.KafkaSource;
import com.yy.task.FlinkAppTask;
import com.yy.task.FlinkConsumerKafkaAppTask;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import lombok.var;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.FutureTask;
import org.springframework.context.ConfigurableApplicationContext;

/** Hello world! */
@SpringBootApplication
@Slf4j
public class App {
    static ConfigurableApplicationContext applicationContext = null;

    public static void main(String[] args) throws Exception {
        applicationContext =
                new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE).run(args);

        //        final var task = new FutureTask<Boolean>(new FlinkAppTask());
        //        new Thread(task).start();
        //        log.info("flink job run {}", task.get() ? "success" : "fail");
        final FlinkConsumerKafkaAppTask app =
                (FlinkConsumerKafkaAppTask)
                        applicationContext.getBean(FlinkConsumerKafkaAppTask.class);
        new Thread(new FutureTask<>(app)).start();
        log.info("hello flink");
    }
}
