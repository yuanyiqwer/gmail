package com.yy;

import jdk.nashorn.internal.codegen.CompilerConstants;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.DriverManager;

/**
 * Hello world!
 */

@SpringBootApplication(scanBasePackages = "com.yy.config")
//@SpringBootApplication
////@ComponentScan(basePackageClasses = {com.yy.controller.Url2.class})
@MapperScan("com.yy.Mapper")
@EnableAsync
public class App {
    private static  int i = 0;
    public static void main(String[] args) {

        SpringApplication.run(App.class,args);


    }
}
