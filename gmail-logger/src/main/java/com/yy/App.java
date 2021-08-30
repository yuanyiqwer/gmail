package com.yy;

import com.yy.Mapper.UserMapper;
import com.yy.controller.Url2;
import com.yy.controller.UrlHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

//@SpringBootApplication(scanBasePackages = "com.yy.config")
@SpringBootApplication
//@ComponentScan(basePackageClasses = {com.yy.controller.Url2.class})
@MapperScan("com.yy.Mapper")
public class App 
{
    public static void main( String[] args )
    {
        final ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );

    }
}
