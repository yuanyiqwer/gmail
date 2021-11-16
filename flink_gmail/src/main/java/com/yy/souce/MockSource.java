package com.yy.souce;

import com.alibaba.fastjson.JSON;
import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;
import com.yy.bean.UserInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MockSource
 * @Author yy
 * @Description
 * @Date 2021/11/15 14:22
 * @Version 1.0
 **/
public class MockSource {
  public static MockObject<UserInfo> getUserInfo(){
    final Map<String, Object> fieldMap = new HashMap<>();
    fieldMap.put("id","@UUID");
    fieldMap.put("name","@cname");
    fieldMap.put("country","@cword(5)");
    fieldMap.put("address","@cword(2)");
    fieldMap.put("ts","@toDateTime");
    Mock.set(UserInfo.class,fieldMap);
    final MockObject<UserInfo> object = Mock.get(UserInfo.class);
    return object;
  }





}
