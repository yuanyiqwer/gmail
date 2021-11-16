package com.yy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @ClassName UserInfo @Author yy @Description @Date 2021/11/12 15:27 @Version 1.0 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String id;
    private String name;
    private String country;
    private String address;
    private String ts;
}
