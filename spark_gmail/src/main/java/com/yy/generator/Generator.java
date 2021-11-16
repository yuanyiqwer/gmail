package com.yy.generator;

import java.io.IOException;
import java.io.Serializable;

/**
 * @ClassName Generator
 * @Author yy
 * @Description
 * @Date 2021/11/8 10:45
 * @Version 1.0
 **/
public abstract class Generator implements Serializable {
    Boolean runing  = true;
    abstract void start();
    abstract void stop();
    abstract void task() throws IOException;



}
