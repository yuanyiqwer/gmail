package com.yy.controller;

import com.yy.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UrlHandler {



    @Autowired(required = false)
    FileUtil util;

    @GetMapping("/hello")
    public String backMessage(){
        boolean b = util == null;
        log.warn("222222222222222222222222");
        System.out.println(b);
        return "122";
    }


}
