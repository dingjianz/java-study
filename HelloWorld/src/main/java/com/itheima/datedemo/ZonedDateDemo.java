package com.itheima.datedemo;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.Instant;

public class ZonedDateDemo {
    public static void main(String[] args) {
        // 获取当前时区的时间对象
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime); // 2026-03-26T17:41:30.325949+08:00[Asia/Shanghai]

        // 获取指定时区的时间
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime1); // 2026-03-26T17:41:30

        // 获取指定时间对象（带时区）
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(2023, 10, 1, 11, 12, 12, 0, ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime2); // 2023-10-01T11:12:12-04:00[America/New_York]

        // 通过Instant对象+指定时区的方式 获取时间对象
        ZonedDateTime zonedDateTime3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.of("Asia/Shanghai"));
        System.out.println(Instant.ofEpochMilli(0L).atZone(ZoneId.of("Asia/Shanghai")));
        System.out.println(zonedDateTime3);

        // withXxxx 修改
        ZonedDateTime zonedDateTime4 = zonedDateTime.withYear(2024).withMonth(4);
        System.out.println(zonedDateTime4); // 2024-04-26T17:41:30.325949+08:00[Asia/Shanghai]

        // plusXxxx 添加
        ZonedDateTime zonedDateTime5 = zonedDateTime.plusYears(1).plusMonths(1);
        System.out.println(zonedDateTime5); // 2027-05-26T17:41:30.325949+08:00[Asia/Shanghai]

        // minusXxxx 减少
        ZonedDateTime zonedDateTime6 = zonedDateTime.minusYears(1).minusMonths(1);
        System.out.println(zonedDateTime6); // 2025-02-26T17:41:30.325949+08:00[Asia/Shanghai]
    }
}
