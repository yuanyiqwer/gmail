package com.yy.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
