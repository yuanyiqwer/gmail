package com.yy.utils

import jdk.nashorn.internal.objects.NativeArray.shift

import java.text.SimpleDateFormat
import java.time.{Clock, Instant, LocalDateTime, Month, ZoneId, ZoneOffset}
import java.util.{Date, TimeZone}
import scala.beans.BeanProperty
import scala.tools.jline_embedded.internal.Configuration.reset

class DateUtil{
  @BeanProperty
  var a = 13
}
/**
 * @ClassName DateUtil
 * @Author yy
 * @Description $description
 * @Date 2021/8/17 15:09 
 * @Version 1.0
 * */
object DateUtil {

  implicit def Double2Int(in: Double): Int = in.toInt

  private[this] val SH_ZODE: String = "Asia/Shanghai"

  def getRandomDatetime: Long = {
    val clock: Clock = Clock.system(ZoneId.of(SH_ZODE))
    val dt: LocalDateTime = LocalDateTime.now(clock)
    val year: Int = dt.getYear
    val month: Int = dt.getMonth.getValue
    val day: Int = dt.getDayOfMonth
    val hour: Int = Math.random() * 24.toInt
    val minute: Int = Math.random() * 60.toInt
    val second: Int = Math.random() * 60.toInt


    //    val randomTime: LocalDateTime = LocalDateTime.of(year, month, day, hour, minute, second)
    val randomTime: LocalDateTime = LocalDateTime.now()
    randomTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli

  }
//   def intValue(a:Int)=1
  def say(a:DateUtil) = {
    println(a.a)
    a.a=2
  }
  def test1(code:  ()=> Unit) {
    println("start")
    code()
    println("end")
  }
  def main(args: Array[String]): Unit = {
    //    val clock: Clock = Clock.system(ZoneId.of(SH_ZODE))
    //    val dt: LocalDateTime = LocalDateTime.of(2021, 9, 3, 12, 12, 12)
    //    println(dt.toInstant(ZoneOffset.ofHours(8)).toEpochMilli)
//    test1({println(1);()=>{println(100)}})
//    val util: DateUtil = new DateUtil
//    say{println(0);util}
//    println(util.a)
//    println({}==())
//    println(().getClass)
//    println(().isInstanceOf[Unit])
    Seq(1,2,3).foldRight(0)({println(1);(a:Int,b:Int)=>{println(a+b);a+b}})

  }


}
