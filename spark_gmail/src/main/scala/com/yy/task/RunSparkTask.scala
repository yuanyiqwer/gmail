package com.yy.task

import lombok.extern.slf4j.Slf4j
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions.explode
import org.apache.spark.sql.types.{ArrayType, DataTypes, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

import java.util.concurrent.{Callable, FutureTask}

case class Maps(r: List[RECORDS])

case class RECORDS(id: String, country_id: String, code: String, name: String, cname: String, lower_name: String)
case class person(str: String)
/**
 * @ClassName RunSparkTask
 * @Author yy
 * @Description $description
 * @Date 2021/11/6 16:01 
 * @Version 1.0
 * */
@Slf4j
class RunSparkTask extends Task with Callable[java.lang.Boolean] {
  private val logger: Logger = LoggerFactory.getLogger("RunSparkTask")
  val spark: SparkSession = SparkSession.builder().appName("sql")
    .config("spark.some.config.option", "some-value")
    .getOrCreate();
  var schema: StructType = _

  def schemaBuilder(): StructType ={
    val id: StructField = StructField("id", StringType, nullable = true)
    val country_id: StructField = StructField("country_id", StringType, nullable = true)
    val code: StructField = StructField("code", StringType, nullable = true)
    val name: StructField = StructField("name", StringType, nullable = true)
    val cname: StructField = StructField("cname", StringType, nullable = true)
    val lower_name: StructField = StructField("lower_name", StringType, nullable = true)
    val recodes: ArrayType = ArrayType(StructType(Nil :+ id :+ country_id :+ code :+ name :+ cname :+ lower_name))
    schema = new StructType().add("RECORDS ", recodes)
    schema
  }

  override def onStart(): Unit = {
    spark.sparkContext.setLogLevel("WARN")
    schemaBuilder


  }

  override def onComplete(): Unit = ???

  override def onErr(t: Throwable): Unit = ???

  override def call(): java.lang.Boolean = {
    onStart();

    import spark.sql
    import spark.implicits._
    val dfArray: DataFrame = spark.read.schema(schema)
      .json("F:\\Desktop\\sparkdata\\world-area-master\\world-area-master\\children\\json\\area.json")

    dfArray.createOrReplaceTempView("map")
    try {
      val frame: DataFrame = dfArray.select(explode($"RECORDS")).toDF(
        "col"
      )
      frame.select(($"col.id")).show
      spark.stop()
      true
    }
    catch {
      case e: Exception =>
        println("error message=>\t{}", e.getMessage)
        false
    }


  }

  override def run(): java.lang.Boolean = {

    val task: FutureTask[java.lang.Boolean] = new FutureTask[java.lang.Boolean](new RunSparkTask)
    new Thread(task).start()
    var b: java.lang.Boolean = true
    while (!task.isDone) {
      b = task.get()
    }
    b
  }

}

object RunSparkTask {
  val spark: SparkSession = SparkSession.builder().appName("sql")
    .config("spark.some.config.option", "some-value")
    .getOrCreate();
  spark.sparkContext.setLogLevel("WARN")
  import spark.implicits._
  def rddToDfToDs(): Unit ={
    val intRdd: RDD[Int] = spark.sparkContext.parallelize(Seq(1, 2, 3))
    val intDs: Dataset[Int] = intRdd.toDS()
    val intDf: DataFrame = intRdd.toDF()
    // TODO: 直接操作df进行算子操作可能出现找不到隐式转换最后类型转化异常
    intDf.map(v => v.getInt(0)).foreach(v=>println(v))
    intDs.printSchema()
    intDf.printSchema()
    val pRdd: RDD[person] = spark.sparkContext.parallelize(Seq(person("1"), person("1"), person("1")))
    val poDs: Dataset[person] = pRdd.toDS()
    val poFrame: DataFrame = pRdd.toDF()
    poDs.printSchema()
    poFrame.printSchema()
    // TODO: 相当于给表添加别名
    poFrame.as("id").printSchema()

  }

def zip(): Unit ={
  val rdd1: RDD[String] = spark.sparkContext.parallelize(List[String]("a","b","c","d"),2)
  val rdd2: RDD[Int] = spark.sparkContext.parallelize(List[Int](1,2,3,4),2)
  val result: RDD[(String, Int)] = rdd1.zip(rdd2)
  result.foreach(println)
}
  def union(): Unit ={
    val rdd1: RDD[String] = spark.sparkContext.parallelize(List[String]("a","b","c","d"),2)
    val rdd2: RDD[Int] = spark.sparkContext.parallelize(List[Int](1,2,3,4),2)
    val rdd3: RDD[Int] = spark.sparkContext.parallelize(List[Int](1,2,3,4,5),2)
    val result: RDD[(String, Int)] = rdd1.zip(rdd2)
    rdd2.union(rdd3).foreach(println)
    result.foreach(println)

  }

  def main(args: Array[String]): Unit = {
    println(List.getClass)


  }

}


