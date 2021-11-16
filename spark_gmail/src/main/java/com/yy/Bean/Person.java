package com.yy.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Person
 * @Author yy
 * @Description
 * @Date 2021/11/8 13:51
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    String name;
    String id;
    Long ts;
}
