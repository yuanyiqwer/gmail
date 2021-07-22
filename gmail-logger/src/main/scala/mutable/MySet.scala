package mutable

import scala.collection.mutable


/**
 * @ClassName MySet
 * @Author yy
 * @Description $description
 * @Date 2021/7/13 11:05 
 * @Version 1.0
 * */
object MySet {
  def main(args: Array[String]): Unit = {

    val s1: Set[Int] = Set(1, 2, 3, 4, 5)
    val s2: mutable.Set[Int] = scala.collection.mutable.Set(0)
    val value: Set[Int] = s1 + 20
    println(value)
    s2++=s1
    s2+=7
    println(s2)
    println(s1.getClass)
    println(s2.getClass)

  }
}
