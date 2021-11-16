package com.yy.sink

/**
 * @ClassName FsSink
 * @Author yy
 * @Description $description
 * @Date 2021/9/3 17:02 
 * @Version 1.0
 * */
object FsSink {


  /** *
   * 写入指定格式和路径的文件系统sink
   *
   * @param format 需要指定的格式
   * @param path   需要指定的路径
   * @return
   */
  def getFsSinkSql(format: String, path: String) =
    s"""
       |create table fs (
       |op STRING,
       |nb BIGINT
       |)With(
       |'connector'='filesystem',
       |'path'='${path}',
       |'format'='${format}'
       |)
       |""".stripMargin
  def getPrintSinkSql =
    s"""
       |create table fs (
       |op STRING,
       |nb BIGINT
       |)With(
       |'connector'='print'
       |)
       |""".stripMargin

  //PARTITIONED BY (ts)
  val intoFsSql: String =
    """
      |insert into fs
      |select op,
      |count(id) as nb
      |from TABLE(
      | HOP(
      | DATA => TABLE KafkaSource,
      | TIMECOL => DESCRIPTOR(wk),
      | SLIDE => INTERVAL '2' SECOND,
      | SIZE => INTERVAL '4' SECOND
      | )
      |) group by op,window_start, window_end
      |""".stripMargin

}
