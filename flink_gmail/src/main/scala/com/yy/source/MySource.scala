package com.yy.source

import com.yy.bean.Person
import com.yy.utils.DateUtil
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.table.api.{DataTypes, Schema}
import java.time.Instant
import java.util.UUID

/**
 * @ClassName MySource
 * @Author yy
 * @Description $description
 * @Date 2021/8/13 16:12 
 * @Version 1.0
 * */
class MySource extends SourceFunction[Person] {

  var Interrupt: Boolean = true

  override def run(ctx: SourceFunction.SourceContext[Person]): Unit = {
    while (Interrupt) {
      val str: java.lang.String = UUID.randomUUID().toString

      ctx.collect(new Person(str, str.substring(2, 7), Math.random().intValue(), Instant.ofEpochMilli(DateUtil.getRandomDatetime)))
    }
  }

  override def cancel(): Unit = Interrupt = false
}

object MySource {

  def getSchema: Schema = {
    Schema.newBuilder()
      .column("id", DataTypes.STRING())
      .column("name", DataTypes.STRING)
      .column("age", DataTypes.INT())
      .column("ts", DataTypes.TIMESTAMP_LTZ(3))
      .watermark("ts", "ts - INTERVAL '5' SECOND")
      .build()
  }

  def getSqlSchema(format: String, path: String): String =
    s"""
       |CREATE TABLE person (
       |id STRING,
       |name STRING,
       |age INT,
       |ts BIGINT,
       |wk as TO_TIMESTAMP_LTZ(ts,3),
       |WATERMARK FOR wk AS wk - INTERVAL '10' SECOND
       |)WITH(
       |'connector' =  'filesystem',
       |'format' = '${format}',
       |'path' = 'file://${path}'
       |)
       |""".stripMargin
  //  F:\Desktop\data.csv


//
  def getOutFsSql(format: String, path: String): String=
    s"""
      |create table outJson(
      | id STRING,
      | name STRING,
      | age INT,
      | ts BIGINT
      |) WITH(
      |'connector'='filesystem',
      |'format'='${format}',
      |'path'='${path}',
      |'json.fail-on-missing-field'='false',
      |'json.ignore-parse-errors'='true'      --解析失败的字段不会导致job失败，并初始化为空
      |)
      |""".stripMargin

  def main(args: Array[String]): Unit = {

  }

}
