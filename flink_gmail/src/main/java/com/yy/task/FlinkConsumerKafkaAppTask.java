package com.yy.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.yy.bean.UserInfo;
import java.util.Properties;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.runtime.state.memory.MemoryStateBackend;
import org.apache.flink.runtime.state.storage.JobManagerCheckpointStorage;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig.ExternalizedCheckpointCleanup;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.springframework.stereotype.Component;

/**
 * @ClassName FlinkConsumerKafkaAppTask @Author yy @Description @Date 2021/11/12 15:46 @Version 1.0
 */
@Component
@Slf4j
public class FlinkConsumerKafkaAppTask implements Task {

    @Resource(name = "kafkaProperties")
    Properties kafkaConf;

    @Override
    public Boolean call() throws RuntimeException {
        try {
            if (kafkaConf == null) {
                log.info(JSON.toJSONString(kafkaConf));
                throw new RuntimeException("kafkaConf is null");
            } else {
                log.info(JSON.toJSONString(kafkaConf));
                log.info("kafkaConf is not null");
            }
            run();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void run() {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(3000, CheckpointingMode.AT_LEAST_ONCE);
        env.setParallelism(2);

        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            //            // TODO: 2021/11/16 1.13之前写法
            //            env.setStateBackend(new MemoryStateBackend());
            // TODO: 2021/11/16 1.13之后写法
            env.setStateBackend(new HashMapStateBackend());
            env.getCheckpointConfig().setCheckpointStorage(new JobManagerCheckpointStorage());
        } else {
            // TODO: 2021/11/15 RocksDB适用于大型job，支持增量，HashMap适用于小型job但是速度非常快，默认使用HashMap
            env.setStateBackend(new EmbeddedRocksDBStateBackend());
            //            env.setStateBackend(new HashMapStateBackend());
            // 设置job完成后保留checkpoint
            env.getCheckpointConfig()
                    .enableExternalizedCheckpoints(
                            ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
            env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
            // 设置默认忍受失败的checkpoint数量,init=0
            env.getCheckpointConfig().setTolerableCheckpointFailureNumber(10);
            env.getCheckpointConfig().setCheckpointTimeout(60 * 1000);
            // TODO: 2021/11/16 如果没有配置checkpoints目录，则使用全局配置
            env.getCheckpointConfig().setCheckpointStorage("hdfs:///user/flink/checkpoints");
        }
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(3, Time.seconds(3)));

        final KafkaSource<String> kafkaCannel =
                KafkaSource.<String>builder()
                        .setTopics("flinkmsg")
                        .setBootstrapServers("192.168.137.102:9092")
                        .setGroupId(UUID.randomUUID().toString())
                        .setStartingOffsets(OffsetsInitializer.earliest())
                        .setValueOnlyDeserializer(new SimpleStringSchema())
                        .build();
        // TODO: 2021/11/15 Class<T> 只能使用非泛型 ，TypeHint适用一切
        final DataStreamSource<String> kafkaSource =
                env.fromSource(kafkaCannel, WatermarkStrategy.noWatermarks(), "kafka source");
        kafkaSource
                .map(
                        (v) -> {
                            UserInfo userInfo = JSON.parseObject(v, UserInfo.class);
                            return new Tuple2<String, Integer>(userInfo.getName(), 1);
                        })
                .returns(TypeInformation.of(new TypeHint<Tuple2<String, Integer>>() {}))
                .keyBy(value -> value.f0)
                .process(
                        new KeyedProcessFunction<
                                String, Tuple2<String, Integer>, Tuple2<String, Integer>>() {

                            private transient ValueState<Integer> sum;

                            @Override
                            public void open(Configuration parameters) throws Exception {
                                ValueStateDescriptor value =
                                        new ValueStateDescriptor(
                                                "value", TypeInformation.of(Integer.class));
                                sum = getRuntimeContext().getState(value);
                            }

                            @Override
                            public void processElement(
                                    Tuple2<String, Integer> value,
                                    Context ctx,
                                    Collector<Tuple2<String, Integer>> out)
                                    throws Exception {

                                Integer val = (Integer) sum.value();
                                if (val != null) {
                                    sum.update(val + 1);

                                } else {
                                    sum.update(1);
                                }
                                out.collect(value);
                            }
                        })
                .returns(TypeInformation.of(new TypeHint<Tuple2<String, Integer>>() {}))
                .keyBy(value -> value.f0)
                .sum(1)
                .print();
        try {
            env.execute();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("当前是flink job，暂时不支持中断job执行");
    }

    public static void main(String[] args) {
        final FlinkConsumerKafkaAppTask kafkaAppTask = new FlinkConsumerKafkaAppTask();
        kafkaAppTask.run();
    }
}
