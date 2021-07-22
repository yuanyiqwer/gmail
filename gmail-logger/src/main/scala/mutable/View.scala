package mutable

import scala.collection.IndexedSeqView

/**
 * @ClassName View
 * @Author yy
 * @Description $description
 * @Date 2021/7/13 15:29 
 * @Version 1.0
 * */
object View {
  def main(args: Array[String]): Unit = {
    val v = Vector(1 to 10: _*)
    println(v)
    val v1 = v.view.map(v=>v)
    println(v1(2))
    // TODO: 整体计算
    println(v1.force)

  }
}
