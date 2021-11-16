package com.yy.io;

import com.yy.conf.KafkaConfig;
import java.util.Properties;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @ClassName KafkaCannel @Author yy @Description @Date 2021/11/12 14:21 @Version 1.0 */
@Configuration
@Slf4j
public class KafkaCannel {

    @Resource KafkaConfig conf;

    @Bean
    public KafkaConsumer<String, String> producerKafkaCannel() {
        Properties props = new Properties();
        log.info(conf.toString());
        props.put("bootstrap.servers", conf.getBootstrapServers());
        props.put("key.deserializer", conf.getKeyDeSerializer());
        props.put("value.deserializer", conf.getValueDeSerializer());
        props.put("group.id", UUID.randomUUID().toString());
        // 可选设置属性
        props.put("enable.auto.commit", conf.getEnableAutoCommit());
        // 自动提交offset,每1s提交一次
        props.put("auto.commit.interval.ms", conf.getAutoCommitInterval());
        //        earliest
        //        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        //            latest
        //        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        //            none
        //        topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        props.put("auto.offset.reset", conf.getAutoOffsetReset());
        KafkaConsumer<String, String> kafka = new KafkaConsumer<>(props);
        return kafka;
    }
}
