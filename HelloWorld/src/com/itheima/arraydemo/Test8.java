package com.itheima.arraydemo;

import java.time.LocalDate;
import java.util.Calendar;

public class Test8 {
    /*
        判断任意的一个年份是闰年还是平年

        要求:
            用JDK7和JDK8两种方式判断
         提示:
            二月有29天是闰年
            一年有366天是闰年
     */
    static void main() {
        // jdk7
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 1, 1); // 月份范围 0~11
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day == 29) {
            System.out.println("闰年");
        } else {
            System.out.println("平年");
        }

        // jdk8
        LocalDate localDate = LocalDate.of(2000, 2, 1); // 月份范围 1~12
        int day2 = localDate.lengthOfMonth();
        System.out.println(day2);
        if (day2 == 29) {
            System.out.println("闰年");
        } else {
            System.out.println("平年");
        }

        // isLeapYear 判断是否是闰年 true 闰年
        System.out.println(localDate.isLeapYear());
    }
}
