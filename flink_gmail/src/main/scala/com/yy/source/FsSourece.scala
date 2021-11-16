package com.yy.source

/**
 * @ClassName FsSourece
 * @Author yy
 * @Description $description
 * @Date 2021/8/30 16:09 
 * @Version 1.0
 * */
object FsSourece {

  def readFsByJson(tb:String,path: String,format: String):String=
    s"""
      |create table ${tb}(
      |id STRING,
      |name STRING,
      |age INT,
      |ts  BIGINT,
      |wk as TO_TIMESTAMP_LTZ(ts,3),
      |WATERMARK FOR wk AS wk - INTERVAL '10' SECOND
      |)WITH(
      |'connector'='filesystem',
      |'path'='${path}',
      |'format'='${format}'
      |)
      |""".stripMargin

}
