package com.yy.souce;

import com.alibaba.fastjson.JSON;
import com.yy.bean.Person;
import com.yy.bean.UserInfo;
import com.yy.io.KafkaCannel;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

/** @ClassName KafkaSource @Author yy @Description @Date 2021/11/12 13:45 @Version 1.0 */
@Component(value = "KafkaSource")
@Slf4j
public class KafkaSource implements SourceFunction<String> {

    private static Boolean interrupt = true;

    @Resource(name = "kafkaConsumer")
    KafkaConsumer<String, String> kafkaConsumer;

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        final ArrayList<String> lst = new ArrayList<>();
        lst.add("flinkmsg");
        kafkaConsumer.subscribe(lst);
        while (interrupt) {
            final ConsumerRecords<String, String> records =
                    kafkaConsumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) {
                log.info("本次没有消费到数据");
                TimeUnit.SECONDS.sleep(1);
            }
            for (ConsumerRecord<String, String> record : records) {
                final String[] values = record.value().split("|");
                log.info("size is =====>\t{}", values.length);
                UserInfo userInfo =
                        new UserInfo(values[0], values[1], values[2], values[3], values[4]);
                log.info("msg\t=====>\t{}", record.value());
                //                ctx.collect(record.value());
            }
        }
    }

    @Override
    public void cancel() {
        interrupt = false;
    }
}
