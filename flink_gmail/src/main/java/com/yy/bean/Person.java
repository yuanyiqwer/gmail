package com.yy.bean;

import java.io.Serializable;
import java.time.Instant;

/**
 * @ClassName Person
 * @Author yy
 * @Description
 * @Date 2021/8/16 13:39
 * @Version 1.0
 **/
public class Person implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private Instant ts;

    public Person(String id, String name, Integer age, Instant ts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ts = ts;
    }

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Instant getTs() {
        return ts;
    }

    public void setTs(Instant ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", ts=" + ts +
                '}';
    }
}
