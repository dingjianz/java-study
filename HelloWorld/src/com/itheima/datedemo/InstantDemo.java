package com.itheima.datedemo;

import com.itheima.oopinnerclass.Outer;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantDemo {
    public static void main(String[] args) {
        // 获取当前时间的Instant对象(标准时间 不带时区)
        Instant now = Instant.now();
        System.out.println(now); // 2026-03-26T09:16:27.163574Z

        //    - 参数：从 1970-01-01T00:00:00Z（Unix 纪元）开始的毫秒数
        //    - 这里传入 1L 表示纪元后 1 毫秒
        Instant instant = Instant.ofEpochMilli(1000L); // 1970-01-01T00:00:01Z

        //  - 参数：从 Unix 纪元开始的秒数
        //  - 这里传入 1L 表示纪元后 1 秒
        Instant instant1 = Instant.ofEpochSecond(60L); // 1970-01-01T00:01:00Z

        //   - 第一个参数：从 Unix 纪元开始的秒数
        //   - 第二个参数：额外的纳秒调整值（0-999,999,999）
        Instant instant2 = Instant.ofEpochSecond(1L, 1000000000L); // 1970-01-01T00:00:02Z

        // 指定时区
        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime); // 2026-03-26T17:41:30.325949+08:00[Asia/Shanghai]

        // isXxx 判断
        // isBefore 判断当前时间 instant1 是否在 instant2 之前
        boolean before = instant1.isBefore(instant2);
        System.out.println(before); // false
        // isAfter 判断当前时间 instant1 是否在 instant2 之后
        System.out.println(instant1.isAfter(instant2)); // true
        // 相等也会报错
        System.out.println(instant1.isAfter(instant1)); // false

        // 减少时间 原时间对象不变
        Instant instant3 = Instant.ofEpochMilli(3000L); // 1970-01-01T00:00:03Z
        Instant instant4 = instant3.minusSeconds(2L); // 1970-01-01T00:00:01Z


    }
}
