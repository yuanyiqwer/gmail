package com.yy.task;

import java.util.concurrent.Callable;

/**
 * @ClassName FlinkAppTask
 * @Author yy
 * @Description
 * @Date 2021/11/6 15:37
 * @Version 1.0
 **/
public class FlinkAppTask implements Callable<Boolean>, Task {



    @Override
    public Boolean call() throws Exception {
        onStart();
        onComplete();
        return false;
    }
    @Override
    public Boolean run() {
      return  true;
    }
    @Override
    public void onStart() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onErr(Throwable t) {

    }
}
