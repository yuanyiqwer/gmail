package com.yy.utils;

import com.yy.bean.Blip;
import com.yy.bean.SerializableDemoBean;

import java.io.*;
import java.util.HashMap;

/**
 * @ClassName SerializableUtils
 * @Author yy
 * @Description
 * @Date 2021/8/19 10:58
 * @Version 1.0
 **/
public class SerializableUtils {



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO: 2021/8/19 自定义序列化
        SerializableDemoBean bean = new SerializableDemoBean(1, "ss");

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);
        objectOutputStream.writeObject(bean);
        ByteArrayInputStream byteInput = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInput);

        SerializableDemoBean o =(SerializableDemoBean) objectInputStream.readObject();
        System.out.println(o);

        final HashMap<String, Object> map = new HashMap<>();
        final HashMap<String, String> map1 = new HashMap<>();
        map.putAll(map1);

//        System.out.println("序列化之前");
//        Blip b = new Blip("This String is " , 47);
//        System.out.println(b);
//
//        System.out.println("序列化操作，writeObject");
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(out);
//        oos.writeObject(b);
//        System.out.println("反序列化之后,readObject");
//        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
//        ObjectInputStream ois = new ObjectInputStream(in);
//        Blip bb = (Blip)ois.readObject();
//        System.out.println(bb);


    }


}
