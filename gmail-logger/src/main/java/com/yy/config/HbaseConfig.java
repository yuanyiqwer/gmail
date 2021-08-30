package com.yy.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName HbaseConfig
 * @Author yy
 * @Description hbase config
 * @Date 2021/7/26 13:44
 * @Version 1.0
 **/
@Configurable
public class HbaseConfig {
    @Bean
    public Configuration initConnConf() {
        final Configuration entries = HBaseConfiguration.create();
        return entries;
    }


}
