package com.yy.transform

import com.yy.bean.Person
import com.yy.env.TVFEnv.envSet
import com.yy.source.MySource
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.table.api.FieldExpression
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

import java.util.concurrent.{ExecutorService, Executors}

class WindowTF {

  def sayA = WindowTF.synchronized {
    println("sleep start...........")
    println("Window a")
    Thread.sleep(2 * 1000)
    println("sleep over ...........")

  }

  def sayB = WindowTF.synchronized {
    println("Window b")
  }

}

/**
 * @ClassName WindowTF
 * @Author yy
 * @Description $description
 * @Date 2021/8/13 16:14
 * @Version 1.0
 * */
object WindowTF {

  def CustomSource = {
    val envS: (StreamTableEnvironment, StreamExecutionEnvironment) = envSet
    val tabEnv: StreamTableEnvironment = envS._1
    val env: StreamExecutionEnvironment = envS._2

    // TODO: 加载数据源，创建虚拟表
    val mySource: DataStream[Person] = env.addSource(new MySource)
    tabEnv.createTemporaryView("person", mySource, MySource.getSchema)


    import tabEnv.executeSql
    executeSql(
      """
        |desc person
        |""".stripMargin).print()
    executeSql(
      """
        |select
        |id
        |,name
        |,age
        |from person
        |""".stripMargin).print()
  }

  def FsSql(path: String, format: String) = {
    val envs: (StreamTableEnvironment, StreamExecutionEnvironment) = envSet
    val tabEnv: StreamTableEnvironment = envs._1
    val env: StreamExecutionEnvironment = envs._2
    import tabEnv.executeSql

    executeSql(MySource.getSqlSchema(format, path))
    executeSql(MySource.getOutFsSql("json", "/F:\\Desktop\\data2.json"))
    executeSql("desc person").print()
    executeSql("desc outJson").print()
    executeSql("select * from person").print()
    executeSql("insert into outJson  select *  from outJson")
    executeSql("select * from outJson").print()


  }

  def main(args: Array[String]): Unit = {
    //打印自定义数据源
    //execCustomSource
    FsSql("/F:\\Desktop\\data.csv", "csv");
  }

}
