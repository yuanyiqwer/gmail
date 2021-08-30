package com.yy.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class FileUtil {
    static {
        System.out.println("FileUtil");
    }
}
