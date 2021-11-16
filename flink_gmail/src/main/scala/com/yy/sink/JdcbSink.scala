package com.yy.sink

/**
 * @ClassName JdcbSink
 * @Author yy
 * @Description $description
 * @Date 2021/8/30 16:09 
 * @Version 1.0
 * */
object JdcbSink {
//  jdbc:clickhouse://192.168.5.128:8123/tutorial
def getSinkToClickHouse:String={
  """
    |create table sinkCH(
    |id INT,
    |sku_id STRING,
    |total_amount BIGINT,
    |create_time TIMESTAMP
    |
    |)WITH(
    |'connector'='jdbc',
    |'url'='jdbc:clickhouse://192.168.5.128:8123/tutorial',
    |'table-name'='t_order_mt'
    |)
    |""".stripMargin
}
}
