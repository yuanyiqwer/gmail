package com.yy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolConfig
 * @Author yy
 * @Description
 * @Date 2021/9/22 14:41
 * @Version 1.0
 **/
@Configuration
//@EnableAsync
public class ThreadPoolConfig {

    @Bean("MyExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // 设置线程池核心容量
        executor.setCorePoolSize(availableProcessors);
        // 设置线程池最大容量
        executor.setMaxPoolSize(availableProcessors * 20);
        // 设置任务队列长度
        executor.setQueueCapacity(200);
        // 设置线程超时时间
        executor.setKeepAliveSeconds(60);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("MyExecutor--");
        // 设置任务丢弃后的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }


}
