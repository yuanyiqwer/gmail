package mutable


class View{

  def say(a:String): Unit ={
    println(a)
  }

}
/**
 * @ClassName View
 * @Author yy
 * @Description $description
 * @Date 2021/7/13 15:29 
 * @Version 1.0
 * */
object View {
  def sum(a:Int)(b:Int){
    println(a+b)
  }
def say(a:String): Unit ={
  println(a)
}
  def apply: View ={
    new View
  }
  def main(args: Array[String]): Unit = {

    val x= View
    x say "s"
    def func(a:Int):String=>Char=>Boolean={
      s => c =>if (a==1) true else false
    }
    val v = Vector(1 to 10: _*)
    println(v)
    val v1 = v.view.map(v=>v)
    println(v1(2))
    // TODO: 整体计算
    println(v1.force)
    sum(1)(6)
    println(func(2)("s")('c'))

  }
}
