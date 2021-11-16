package com.yy.env

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.EnvironmentSettings
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

/**
 * @ClassName TVFenv
 * @Author yy
 * @Description $description
 * @Date 2021/8/13 16:16
 * @Version 1.0
 * */
object TVFEnv {

  def envSet: (StreamTableEnvironment, StreamExecutionEnvironment) = {


    // TODO: 设置流环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // TODO: 设置表配置
    val settings: EnvironmentSettings = EnvironmentSettings.newInstance
      .useBlinkPlanner
      .inStreamingMode
      .build
    // TODO: 设置表环境
    val tabEnv: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)


    (tabEnv, env)

  }

}
