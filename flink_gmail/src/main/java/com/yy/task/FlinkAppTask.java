package com.yy.task;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.util.concurrent.Callable;

/** @ClassName FlinkAppTask @Author yy @Description @Date 2021/11/10 18:57 @Version 1.0 */
@Slf4j
public class FlinkAppTask implements Task {
    @Override
    public Boolean call() throws Exception {
        try {
            run();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    Tuple2<StreamExecutionEnvironment, StreamTableEnvironment> initEnv() {
        final var env = StreamExecutionEnvironment.getExecutionEnvironment();
        final var setting =
                EnvironmentSettings.newInstance().inStreamingMode().useBlinkPlanner().build();
        final var tabEnv = StreamTableEnvironment.create(env, setting);
        final var envSet = new Tuple2<StreamExecutionEnvironment, StreamTableEnvironment>();
        envSet.setField(env, 0);
        envSet.setField(tabEnv, 1);
        return envSet;
    }

    @Override
    public void run() {
        final var envSet = initEnv();
        final var env = envSet.f0;
        env.setParallelism(2);
        final var textSource = env.socketTextStream("localhost",15000);
        textSource
                .map(
                        v -> {
                            return v.length();
                        })
                .print();

        try {
            env.execute("flink");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        final var flinkAppTask = new FlinkAppTask();
        flinkAppTask.run();
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("当前是flink job，暂时不支持中断job执行");
    }
}
