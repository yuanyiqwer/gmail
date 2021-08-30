package com.yy.utils

import java.text.SimpleDateFormat
import java.time.{Clock, Instant, LocalDateTime, Month, ZoneId, ZoneOffset}
import java.util.{Date, TimeZone}

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

  def main(args: Array[String]): Unit = {
    getRandomDatetime



  }


}
