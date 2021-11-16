package com.yy.controller;

import com.yy.service.CaclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName AspectController
 * @Author yy
 * @Description
 * @Date 2021/9/18 11:23
 * @Version 1.0
 **/
@RestController
@Slf4j
public class AspectController {

    @Resource
    CaclService service;

    @GetMapping("/helloAspect")
    public String aspectTest(@RequestParam("a") int a, @RequestParam("b") int b) {

        service.twoNbDivede(a,b);
        return "ok";
    }

}
