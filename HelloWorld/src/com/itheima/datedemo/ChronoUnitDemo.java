package com.itheima.datedemo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitDemo {
    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); // 2026-03-27T22:45:41.484036

        // 生日时间
        LocalDateTime birthday = LocalDateTime.of(1995, 5, 27, 6, 55, 20);
        System.out.println(birthday); // 1995-05-27T06:55:20

        System.out.println("相差的年数：" + ChronoUnit.YEARS.between(birthday, now)); // 30
        System.out.println("相差的月数：" + ChronoUnit.MONTHS.between(birthday, now)); // 370
        System.out.println("相差的周数：" + ChronoUnit.WEEKS.between(birthday, now)); // 1608
        System.out.println("相差的日数：" + ChronoUnit.DAYS.between(birthday, now)); // 11262
        System.out.println("相差的小时数：" + ChronoUnit.HOURS.between(birthday, now)); // 270303
        System.out.println("相差的分钟数：" + ChronoUnit.MINUTES.between(birthday, now)); // 16218234
        System.out.println("相差的秒数：" + ChronoUnit.SECONDS.between(birthday, now)); // 973094095
        System.out.println("相差的毫秒数：" + ChronoUnit.MILLIS.between(birthday, now)); // 973094095597
        System.out.println("相差的微秒数：" + ChronoUnit.MICROS.between(birthday, now)); // 973094095597571
        System.out.println("相差的纳秒数：" + ChronoUnit.NANOS.between(birthday, now)); // 973094095597571000
        System.out.println("相差的半天数：" + ChronoUnit.HALF_DAYS.between(birthday, now)); // 22525
        System.out.println("相差的十年数：" + ChronoUnit.DECADES.between(birthday, now)); // 3
        System.out.println("相差的世纪（百年）数：" + ChronoUnit.CENTURIES.between(birthday, now)); // 0
        System.out.println("相差的千年数：" + ChronoUnit.MILLENNIA.between(birthday, now)); // 0
        System.out.println("相差的纪元数：" + ChronoUnit.ERAS.between(birthday, now)); // 0

    }
}
