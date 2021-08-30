package com.yy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yy.utils.HBaseUtils.showTables;

/**
 * @ClassName XdrQueryHander
 * @Author yy
 * @Description
 * @Date 2021/7/26 13:57
 * @Version 1.0
 **/
@RestController
@Slf4j
public class XdrQueryHander {

    @GetMapping("xdr")
    public String  queryHbase(){
        showTables();
        return  "s";
    }



}
