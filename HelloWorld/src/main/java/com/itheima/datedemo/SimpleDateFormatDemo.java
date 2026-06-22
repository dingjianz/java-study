package com.itheima.datedemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class SimpleDateFormatDemo {
    public static void main(String[] args) throws ParseException {
        // 1.利用空参构建simpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date d = new Date(0L);
        String str = sdf.format(d);
        System.out.println(str); // 1970/1/1 08:00

        // 2.利用带参构造创建SimpleDateFormat对象，指定格式
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str2 = sdf2.format(d);
        System.out.println(str2); // 1970年01月01日 08:00:00
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 EE");
        System.out.println(sdf3.format(d)); // 1970年01月01日 周四

        // 3.定义一个字符串表示时间
        String str3 = "2023-11-11 11:11:11";
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d2 = sdf4.parse(str3); // 将字符 转换成 Date对象
        System.out.println(d2); // Sat Nov 11 11:11:11 CST 2023
        System.out.println(d2.getTime()); // 1699672271000

        // 将 2000-11-11 转换成 2000年11月11日
        String str4 = "2000-11-11";
        SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd");
        Date d3 = sdf5.parse(str4);
        SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(sdf6.format(d3)); // 2000年11月11日
    }
}


