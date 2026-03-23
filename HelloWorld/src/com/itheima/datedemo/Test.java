package com.itheima.datedemo;

import java.util.Date;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        // 1.创建对象表示一个时间
        Date d = new Date();
        System.out.println(d);

        // 2.创建对象表示一个指定的时间
        Date d2  = new Date(0L);
        System.out.println(d2); // Thu Jan 01 08:00:00 CST 1970

        // 3.setTime 修改时间
        d2.setTime(1000L);
        System.out.println(d2); // Thu Jan 01 08:00:01 CST 1970

        // 4.getTime 获取当前时间的毫秒值
        long time = d2.getTime();
        System.out.println(time); // 1000

        // 需求1：打印时间原点开始一年之后的时间
        Date d3 = new Date(0L);
        long _time = d3.getTime();
        _time += 365 * 24 * 60 * 60 * 1000L;
        d3.setTime(_time);
        System.out.println(d3); // Fri Jan 01 08:00:00 CST 1971

        // 需求2：定义任意两个Date对象，比较一下哪个时间在前，哪个时间在后
        Random r = new Random();
        Date d4 = new Date(Math.abs(r.nextInt()));
        Date d5 = new Date(Math.abs(r.nextInt()));
        System.out.println(d4);
        System.out.println(d5);
        System.out.println(d4.getTime() > d5.getTime() ? "d5的时间在前" : "d4的时间在前");
    }
}
