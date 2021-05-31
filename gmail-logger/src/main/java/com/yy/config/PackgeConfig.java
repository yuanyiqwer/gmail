package com.yy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ComponentScan(value = "com.yy.controller,com.yy.utils")
@Component
public class PackgeConfig {}
