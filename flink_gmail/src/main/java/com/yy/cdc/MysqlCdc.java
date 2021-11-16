package com.yy.cdc;



import com.alibaba.ververica.cdc.connectors.mysql.MySQLSource;
import com.alibaba.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.alibaba.ververica.cdc.debezium.DebeziumSourceFunction;
import com.alibaba.ververica.cdc.debezium.StringDebeziumDeserializationSchema;
import com.yy.cdc.Serialization.ComsuterCdcSerialization;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @ClassName MysqlCdc
 * @Author yy
 * @Description
 * @Date 2021/8/31 14:46
 * @Version 1.0
 **/
public class MysqlCdc {

    public static DebeziumSourceFunction<String> getCdcSourceFunction(StreamExecutionEnvironment env) {

        // TODO: 2021/8/31 通过cdc构建datasource
        DebeziumSourceFunction<String> mysqlCdcSource = MySQLSource.<String>builder()
                .hostname("master02")
                .port(3306)
                .username("root")
                .password("broadtech")
                .databaseList("sharepro")
                .tableList("sharepro.jobs")
                .startupOptions(StartupOptions.initial())
                .deserializer(new ComsuterCdcSerialization())
                .build();
        return mysqlCdcSource;
    }

    public static String getCdcSQL(){
        return "create table mysqlCdc(" +
                "id INT," +
                "name STRING" +
                ") WITH(" +
                "'connector'='mysql-cdc'," +
                "'hostname'='master02'," +
                "'port'='3306'," +
                "'username'='root'," +
                "'password'='broadtech'," +
                "'database-name'='sharepro'," +
                "'table-name'='inventory'" +
                ")";
    }

}
