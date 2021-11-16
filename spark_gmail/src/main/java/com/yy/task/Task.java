package com.yy.task;

public interface Task {
    void onStart();
    void onComplete();
    void onErr(Throwable t);
    Boolean run();

}
