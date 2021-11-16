package com.yy.jvm.stack;

/**
 * @ClassName LocalVariable
 * @Author yy
 * @Description
 * @Date 2021/10/8 14:24
 * @Version 1.0
 **/
public class LocalVariable {

    int a=10;
    static String str="123";
    public void testTemp(){
//        int num=1;
//        System.out.println(num);
    }
    public void testTemp2(){
        testTemp();
        System.out.println(str);
        a++;
    }

    public static void main(String[] args) {
          Object o = null;
        System.out.println(o.hashCode());
//        int a=10;
//        int b=11;
//        int c=a+b;
//        System.out.println(c);
//        final LocalVariable localVariable = new LocalVariable();
//        localVariable.testTemp();
//        localVariable.a++;
    }

}
