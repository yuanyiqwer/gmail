package com.yy.cdc.Serialization;

import com.alibaba.ververica.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;

/**
 * @ClassName ComsuterCdcSerialization
 * @Author yy
 * @Description
 * @Date 2021/8/31 19:14
 * @Version 1.0
 **/
public class ComsuterCdcSerialization implements DebeziumDeserializationSchema<String> {

    /***
     * 数据解析
     * @param sourceRecord
     * @param collector
     * @throws Exception
     */
    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<String> collector) throws Exception {
//        final String tp = sourceRecord.topic();
//
//        final Schema schema = sourceRecord.valueSchema();
        collector.collect("{\"id\":\"1\",\"name\":\"yy\"}");


    }

    /***
     * 确认返回值类型
     * @return
     */
    @Override
    public TypeInformation<String> getProducedType() {
        return TypeInformation.of(new TypeHint<String>(){});
    }
}
