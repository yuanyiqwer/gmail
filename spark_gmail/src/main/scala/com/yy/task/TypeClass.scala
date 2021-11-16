package com.yy.task

import java.util

/**
 * @ClassName TypeClass
 * @Author yy
 * @Description $description
 * @Date 2021/11/10 12:22 
 * @Version 1.0
 * */
class TypeClass {
  def say(s: String){

  }
}

class A {}

class B extends A {}

object TypeClass {
  def apply(): TypeClass = ???


  def main(args: Array[String]): Unit = {


    val a: TypeClass.type = TypeClass
    val b: TypeClass = a()


  }
}
