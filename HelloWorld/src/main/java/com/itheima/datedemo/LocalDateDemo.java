package com.itheima.datedemo;

import java.time.LocalDate;
import java.time.MonthDay;

public class LocalDateDemo {
    public static void main() {
        // 1.获取当前时间的日历对象（包含年月日）
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); // 2026-03-27

        // 2.获取指定时间的日历对象
        LocalDate localDate1 = LocalDate.of(2026, 3, 27);
        System.out.println(localDate1); // 2026-03-27

        // 3.get系列方法获取日历中的每一个属性值
        int year = localDate1.getYear();
        System.out.println(year); // 2026
        System.out.println(localDate1.getMonth()); // MARCH
        int month = localDate1.getMonthValue();
        System.out.println(month); // 3
        int date = localDate1.getDayOfMonth();
        System.out.println(date); // 27

        // 获取一年中的第几天
        int dayOfYear = localDate1.getDayOfYear();
        System.out.println(dayOfYear); // 86

        // 获取星期
        int dayOfWeek = localDate1.getDayOfWeek().getValue();
        System.out.println(dayOfWeek); // 5

        // is开头的方法判断
        // isBefore 判断当前时间是否在指定时间之前
        boolean before = localDate1.isBefore(LocalDate.of(2026, 3, 26));
        System.out.println(before); // false

        // isAfter 判断当前时间是否在指定时间之后
        boolean after = localDate1.isAfter(LocalDate.of(2026, 3, 26));
        System.out.println(after); // true

        //4.with方法修改日历中的属性值
        LocalDate localDate2 = localDate1.withYear(2027).withMonth(4).withDayOfMonth(28);
        System.out.println(localDate2); // 2027-04-28

        // 5.plus方法增加时间
        LocalDate localDate3 = localDate1.plusYears(1).plusMonths(1).plusDays(1);
        System.out.println(localDate3); // 2027-04-28

        // 6.minus方法减少时间
        LocalDate localDate4 = localDate1.minusYears(1).minusMonths(1).minusDays(1);
        System.out.println(localDate4); // 2025-02-26

        // 7.判断今天是否是你的生日
        LocalDate localDate5 = LocalDate.of(1994, 9, 13);
        MonthDay birthMd = MonthDay.of(localDate5.getMonth(), localDate5.getDayOfMonth());
        System.out.println(birthMd); // --9-13
        MonthDay nowMd = MonthDay.from(LocalDate.now());
        System.out.println(birthMd.equals(nowMd)); // false
    }
}
