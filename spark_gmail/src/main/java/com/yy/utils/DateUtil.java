package com.yy.utils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @ClassName DateUtil
 * @Author yy
 * @Description
 * @Date 2021/11/8 13:53
 * @Version 1.0
 **/
public class DateUtil {
    public static Long getTimestamp(Long offset, int op) {
        return getTimestamp(offset * op);
    }

    static Long getTimestamp(Long offset) {
        return Clock.systemUTC().millis() - offset;
    }

    static Long getTimestamp() {

        return getTimestamp(0L);
    }

    static String getTimestamp(String zone) {
        final Clock clock = Clock.system(ZoneId.of(zone));
        final LocalDateTime now = LocalDateTime.now(clock);
        return "";
    }
}
