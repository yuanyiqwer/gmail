package com.yy.controller;

import com.yy.service.aspect.MyAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.*;

/**
 * @ClassName CurrentLimitHandler
 * @Author yy
 * @Description
 * @Date 2021/9/22 14:22
 * @Version 1.0
 **/
@Slf4j
@RestController
public class CurrentLimitHandler {

    public LinkedBlockingQueue<HttpServletRequest> que =   new LinkedBlockingQueue<>(5);
    @Resource
    Executor pool;
    @Resource
    MyAspect aspect;

    @PostMapping("/a")
    public String rqTest(HttpServletRequest request) throws InterruptedException {

       if (null==request){
           return "null";
       }


        return  aspect.say(request).toString();
    }






}
