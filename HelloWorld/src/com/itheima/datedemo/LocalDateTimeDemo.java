package com.itheima.datedemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeDemo {
    static void main() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time); // 2026-03-27T22:26:32.744074
        System.out.println(time.getYear()); // 2026
        System.out.println(time.getMonthValue()); // 3
        System.out.println(time.getDayOfMonth()); // 27
        System.out.println(time.getHour()); // 22
        System.out.println(time.getMinute()); // 26
        System.out.println(time.getSecond()); // 32
        System.out.println(time.getNano()); // 744074

        // toLocalDate() 获取LocalDate
        LocalDate date = time.toLocalDate();
        System.out.println(date); // 2026-03-27

        // toLocalTime() 获取LocalTime
        LocalTime time2 = time.toLocalTime();
        System.out.println(time2); // 22:26:32.744074
    }
}
