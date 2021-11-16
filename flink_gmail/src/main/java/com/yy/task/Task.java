package com.yy.task;

import java.util.concurrent.Callable;

public interface Task extends Callable<Boolean> {

    void run();

    void cancel();
}
