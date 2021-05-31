package com.yy.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Author yy
 * @Description
 * @Date 2021/5/31 14:20
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User {

    @TableId
    private Long id;
    private String name;
    private Integer age;
    @TableField(exist = false)
    private String email;

}
