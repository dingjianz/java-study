package com.itheima.datedemo;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {
    public static void main(String[] args) {
        /*
         1.获取日历对象
        细节1: Calendar是一个抽象类，不能直接new，而是通过一个静态方法获取到子类对象

         底层原理
         会根据系统的不同时区获取不同的日历对象，默认表示当前时间
         会把时间中的纪元、年、月、日、时、分、秒、星期等等都放到一个数组当中
         0: 纪元
         1: 年
         2: 月
         3: 一年中的第几周
         4: 一个月中的第几周
         5:  一个月中的第几天(日期 )

        细节2:
            月份:范围 0~11,如果取出来的是0,其实是1月
            星期: 1-7 ,其中1是星期日,2是星期一,7是星期六
         */


        Calendar c = Calendar.getInstance();
        System.out.println(c);

        // 2.修改日历代表的时间
        Date d = new Date(0L);
        c.setTime(d);
        // c.set(2026, 2, 27);
        // c.set(Calendar.YEAR , 2026);
        // c.set(Calendar.MONTH, 2);
        // c.set(Calendar.DATE, 27);

        // 3.获取时间
        Date d2 = c.getTime();
        System.out.println(d2); // Thu Jan 01 08:00:00 CST 1970

        //4.get 获取日期中的某个字段信息
        // int year = c.get(1);
        // int month = c.get(2) + 1;
        // int date = c.get(5);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "-" + month + "-" + date); // 1970-1-1
        System.out.println(getWeek(c.get(Calendar.DAY_OF_WEEK))); // 星期四

        //5. public void set(int field, int value)
        c.set(Calendar.YEAR, 2026);
        System.out.println(c.get(Calendar.YEAR)); // 2026
        c.set(Calendar.MONTH, -12); // 月份会按照 / 12 累加/减
        System.out.println(c.get(Calendar.YEAR)); // 2027

        // 6. public void add(int field, int value) 在原有的基础上进行增加或者减少
        c.add(Calendar.MONTH, -1);
        System.out.println(c.get(Calendar.MONTH));

    }


    // 查表法
    // 表:容器
    // 让数据跟索引产生对应的关系
    public static String getWeek(int date) {
        // 定义一个数组,让汉字星期几 跟1-7 产生对应关系
        String[] arr = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return arr[date];
    }
}
