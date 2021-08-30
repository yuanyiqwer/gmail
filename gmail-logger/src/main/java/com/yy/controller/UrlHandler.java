package com.yy.controller;

import com.yy.Mapper.UserMapper;
import com.yy.bean.User;
import com.yy.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RestController
@Slf4j
public class UrlHandler {

    static  {
        System.out.println("UrlHandler...........");
    }

    @Autowired(required = false)
    FileUtil util;


    @Autowired(required = false)
    UserMapper user;
    @Autowired(required = false)
    DataSource source;
    @GetMapping("/hello")
    public String backMessage() {
//        boolean b = util == null;
//        System.out.println(source);
//        log.warn("222222222222222222222222");
//        final List<User> lst = user.selectList(null);
//        lst.stream().forEach(rs -> {
//            System.out.println(rs.toString());
//        });

        return "122";
    }


}
