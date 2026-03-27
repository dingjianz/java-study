package com.itheima.datedemo;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationDemo {
    public static void main(String[] args) {
        // 本地 年月日时分秒
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); // 2026-03-27T22:38:49.211173

        // 出生的 时间
        LocalDateTime birth = LocalDateTime.of(1994, 9, 13, 6, 50, 20);
        System.out.println(birth); // 1994-09-13T06:50:20

        Duration between = Duration.between(birth, now); // 第二个参数 减去 第一个参数
        System.out.println("相差的时间间隔对象" + between); // PT276447H51M20.743134S
        System.out.println(between.toDays()); // 相差的天数 11518
        System.out.println(between.toHours()); // 相差的小时 276447
        System.out.println(between.toMinutes()); // 16586871
    }

}
