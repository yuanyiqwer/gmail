package com.yy.conf;

import java.util.Properties;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @ClassName KafkaConfig @Author yy @Description @Date 2021/11/12 14:06 @Version 1.0 */
@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaConfig {
    private String bootstrapServers;
    private String keyDeSerializer;
    private String valueDeSerializer;
    private String groupId;
    private String enableAutoCommit;
    private String autoCommitInterval;
    private String autoOffsetReset;
    private String deSerializerEncoding;

    @Bean("kafkaConsumer")
    public KafkaConsumer<String, String> producerKafkaCannel() {
        Properties props = new Properties();
        props.put("bootstrap.servers", getBootstrapServers());
        props.put("key.deserializer", getKeyDeSerializer());
        props.put("value.deserializer", getValueDeSerializer());
        props.put("deserializer.encoding",getDeSerializerEncoding());
        props.put("group.id", UUID.randomUUID().toString());
        // 可选设置属性
        props.put("enable.auto.commit",getEnableAutoCommit());
        // 自动提交offset,每1s提交一次
        props.put("auto.commit.interval.ms", getAutoCommitInterval());
        //        earliest
        //        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        //            latest
        //        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        //            none
        //        topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        props.put("auto.offset.reset", getAutoOffsetReset());
        KafkaConsumer<String, String> kafka = new KafkaConsumer<>(props);
        return kafka;
    }
    @Bean("kafkaProperties")
    public Properties producerKafkaProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", getBootstrapServers());
        props.put("key.deserializer", getKeyDeSerializer());
        props.put("value.deserializer", getValueDeSerializer());
        props.put("deserializer.encoding",getDeSerializerEncoding());
        props.put("group.id", UUID.randomUUID().toString());
        // 可选设置属性
        props.put("enable.auto.commit",getEnableAutoCommit());
        // 自动提交offset,每1s提交一次
        props.put("auto.commit.interval.ms", getAutoCommitInterval());
        //        earliest
        //        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        //            latest
        //        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        //            none
        //        topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        props.put("auto.offset.reset", getAutoOffsetReset());
        return props;
    }

}
