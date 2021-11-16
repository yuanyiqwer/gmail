package com.yy.service.impl;

import com.yy.service.CaclService;
import org.springframework.stereotype.Service;

/**
 * @ClassName CaclServiceImpl
 * @Author yy
 * @Description
 * @Date 2021/9/18 10:02
 * @Version 1.0
 **/
@Service
public class CaclServiceImpl implements CaclService {
    @Override
    public void twoNbDivede(int a, int b) {
        System.out.println(a/b);
    }
}
