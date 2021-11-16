package com.yy.spi;


/***
 * @author yy
 */
public interface Search {

    /***
     *  根据key类型，加载相应服务
     * @param key
     */
    String searchItem(String key);

}
