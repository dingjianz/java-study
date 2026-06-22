package com.itheima.datedemo;

import java.time.ZoneId;
import java.util.Set;

public class ZoneIdDemo {
    public static void main(String[] args) {
        // 获取所有的时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(zoneIds); // size() 600个时区

        // 获取默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId); // Asia/Shanghai

        // 获取指定时区
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        System.out.println(zoneId1);
    }
}
