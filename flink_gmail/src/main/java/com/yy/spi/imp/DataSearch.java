package com.yy.spi.imp;

import com.yy.spi.Search;

import java.util.Iterator;
import java.util.ServiceLoader;

/** @ClassName FileSearch @Author yy @Description @Date 2021/9/1 11:11 @Version 1.0 */
public class DataSearch implements Search {
    public DataSearch() {}

    @Override
    public String searchItem(String key) {
        return "file is : " + key;
    }
}
