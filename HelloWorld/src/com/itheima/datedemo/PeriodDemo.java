package com.itheima.datedemo;

import java.time.LocalDate;
import java.time.Period;

public class PeriodDemo {
    public static void main(String[] args) {
        // 当前本地 年月日
        LocalDate now = LocalDate.now();
        System.out.println(now); // 2026-03-27

        // 我的生日
        LocalDate birthday = LocalDate.of(1996, 5, 29);
        System.out.println(birthday); // 1996-05-27

        Period period = Period.between(birthday, now); // 第二个参数 减去 第一个参数

        System.out.println("相差的时间间隔对象：" +  period); // P29Y9M27D
        System.out.println(period.getYears()); // 29
        System.out.println(period.getMonths()); // 9
        System.out.println(period.getDays()); // 27
        // 29 * 12 + 9 = 357
        System.out.println(period.toTotalMonths()); // 357 个月

    }
}
