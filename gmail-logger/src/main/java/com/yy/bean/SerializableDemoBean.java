package com.yy.bean;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @ClassName SerializableDemoBean
 * @Author yy
 * @Description
 * @Date 2021/8/19 10:58
 * @Version 1.0
 **/
public class SerializableDemoBean implements Externalizable {
    int i;
    String s;

    public SerializableDemoBean() {
    }

    public SerializableDemoBean(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(s);
        out.writeInt(i);


    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        s = (String) in.readObject();
        i = in.readInt();

    }

    @Override
    public String toString() {
        return "SerializableDemoBean{" +
                "i=" + i +
                ", s='" + s + '\'' +
                '}';
    }
}
