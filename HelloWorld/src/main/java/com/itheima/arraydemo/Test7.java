package com.itheima.arraydemo;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test7 {
    // 请使用代码实现计算你活了多少天，用JDK7和JDK8两种方式完成
    static void main() {
        // JDK7
        String birthdayStr = "1994-09-13";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date birthday = sdf.parse(birthdayStr);
            long birthdayTime = birthday.getTime();
            long nowTime = System.currentTimeMillis();
            long days = (nowTime - birthdayTime) / (1000 * 60 * 60 * 24);
            System.out.println(days);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // JDK8
        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.of(1994, 9, 13);
        System.out.println(ChronoUnit.DAYS.between(birthday, now));
    }
}
