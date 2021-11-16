package com.yy.service.aspect;

import com.yy.controller.CurrentLimitHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyAspect
 * @Author yy
 * @Description
 * @Date 2021/9/18 10:03
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class MyAspect {

    @Resource
    CurrentLimitHandler handler;

    @Pointcut("execution(* com.yy.service.*.*(..))")
    private void allMethods() {
    }

    ;

    @Before("allMethods()")
    public void myBefore(JoinPoint point) {
        final String className = point.getTarget().getClass().getSimpleName();
        System.out.println("before :\t" + className);
    }

    @After("allMethods()")
    public void myAfter(JoinPoint point) {

        final String className = point.getTarget().getClass().getSimpleName();
        System.out.println("After :\t" + className);
    }


    @AfterReturning("allMethods()")
    public void myAfterReturn(JoinPoint point) {

        final String className = point.getTarget().getClass().getSimpleName();
        System.out.println("AfterReturning :\t" + className);

    }

//    @Async("MyExecutor")
    public StringBuffer say(HttpServletRequest request) throws InterruptedException {
        handler.que.put(request);
        log.info("name :\t" + Thread.currentThread().getId());
//        TimeUnit.SECONDS.sleep(10);

        final HttpServletRequest rq =request;
        final byte[] bytes = new byte[1024];
        final StringBuffer str = new StringBuffer();
        try {
            final ServletInputStream in = rq.getInputStream();
            int i;
            while ( (i=in.read(bytes))!= -1) {
                log.info(new String(bytes));
                TimeUnit.SECONDS.sleep(2);
                str.append(new String(bytes,0,i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            final Document document = DocumentHelper.parseText(str.toString());
            System.out.println(document.getRootElement().toString());
            final Element rootElement = document.getRootElement();
            final Element element = rootElement.element("element");
            System.out.println(element);
            System.out.println(element.element("complexType").element("sequence"));
            final Iterator<Element> elementIterator = element.element("complexType").element("sequence").elementIterator();
//            .element("xs:element").element("xs:complexType").element("xs:sequence")
            while (elementIterator.hasNext()) {
                System.out.println(elementIterator.next().toString());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        log.info("name :\t" + Thread.currentThread().getId() + "\tover...");
        return str;
    }

    //
//
    @AfterThrowing("allMethods()")
    public void myAfterThrowing(JoinPoint point) {
        final String className = point.getTarget().getClass().getSimpleName();
        System.out.println("AfterThrowing :\t" + className);
    }

    @Around("allMethods()")
    public void myAround(ProceedingJoinPoint point) {

        try {
            System.out.println("before \t");
            final Object[] args = point.getArgs();
            point.proceed(args);
            System.out.println("after \t");
        } catch (Throwable throwable) {
//            throwable.printStackTrace();
            System.out.println("error.............");
        }

    }

}
