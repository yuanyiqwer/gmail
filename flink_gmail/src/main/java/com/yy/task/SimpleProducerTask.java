package com.yy.task;

import com.alibaba.fastjson.JSON;
import com.yy.bean.UserInfo;
import com.yy.souce.MockSource;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/** @ClassName SimpleProducer @Author yy @Description @Date 2021/11/11 17:45 @Version 1.0 */
public class SimpleProducerTask {
    public static void main(String[] args) {

        String topicName = "flinkmsg";

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.137.102:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /*创建生产者*/
        Producer<String, String> producer = new KafkaProducer<>(props);
        int i = 0;
        for (UserInfo userInfo : MockSource.getUserInfo().getList(30000)) {
            ProducerRecord<String, String> record =
                    new ProducerRecord<>(topicName, "hello" + i, JSON.toJSONString(userInfo));
            /* 发送消息*/
            producer.send(record);
            i++;
        }

        /*关闭生产者*/
        producer.close();
    }
}
