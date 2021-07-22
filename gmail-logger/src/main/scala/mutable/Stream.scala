package mutable
import scala.collection.immutable.Stream._



/**
 * @ClassName Stream
 * @Author yy
 * @Description $description
 * @Date 2021/7/13 15:28 
 * @Version 1.0
 * */
object Stream {
  def main(args: Array[String]): Unit = {
    val s1 = 1#::2#::{println("22222222222222")}#::scala.collection.immutable.Stream.empty
    s1(2)
  }
}
