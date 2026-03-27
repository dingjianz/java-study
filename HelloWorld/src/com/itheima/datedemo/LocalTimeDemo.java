package com.itheima.datedemo;

import java.time.LocalTime;

public class LocalTimeDemo {
    public static void main(String[] args) {
        // 创建LocalTime对象(包含时分秒)
        LocalTime time = LocalTime.now();
        System.out.println(time); // 22:16:12.732424

        // 指定 时间
        LocalTime time1 = LocalTime.of(22, 16, 12, 732424);

        // getHour() 获取小时
        int hour = time1.getHour();
        System.out.println(hour); // 22

        // getMinute() 获取分钟
        int minute = time1.getMinute();
        System.out.println(minute); // 16

        // getSecond() 获取秒
        int second = time1.getSecond();
        System.out.println(second); // 12

        // getNano() 获取纳秒
        int nano = time1.getNano();
        System.out.println(nano); // 732424

        // isBefore() 判断当前时间是否在指定时间之前
        boolean before = time1.isBefore(time);
        System.out.println(before); // true

        // isAfter() 判断当前时间是否在指定时间之后
        boolean after = time1.isAfter(time);
        System.out.println(after); // false

        // withHour() 修改小时
        LocalTime time2 = time1.withHour(23).minusMinutes(1).minusSeconds(1);
        System.out.println(time2); // 23:15:11.000732424

    }
}
