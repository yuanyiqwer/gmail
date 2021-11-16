package com.yy.source

/**
 * @ClassName KafkaSource
 * @Author yy
 * @Description $description
 * @Date 2021/8/30 16:09 
 * @Version 1.0
 * */
object KafkaSource {

  def getKafkaSourceSql(topic: String, testGroup:String)=
    s"""
      |create table KafkaSource(
      |id STRING,
      |op STRING,
      |ts BIGINT,
      |wk as TO_TIMESTAMP_LTZ(ts,3),
      |WATERMARK FOR wk AS wk - INTERVAL '1' SECOND
      |)WITH(
      |'connector'='kafka',
      |'topic'='${topic}',
      |'properties.bootstrap.servers'='192.168.5.153:9092,192.168.5.128:9092,192.168.5.154:9092',
      |'format'='csv',
      |'properties.group.id' = '${testGroup}',
      |'scan.startup.mode' = 'latest-offset'
      |)
      |""".stripMargin


}
