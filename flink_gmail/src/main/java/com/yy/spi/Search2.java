package com.yy.spi;


/***
 * @author yy
 */

public interface Search2 {

    /***
     *  根据key类型，加载相应服务
     * @param key
     */
    String searchItem(String key);


    default  void say(){

    }

}
