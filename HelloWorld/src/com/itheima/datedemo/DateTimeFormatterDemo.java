package com.itheima.datedemo;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterDemo {
    public static void main(String[]  args) {
        // 获取事件对象
        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));

        // 解析/格式化器
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss EEEE a");
        // 格式化
        String str = dtf.format(zonedDateTime);
        System.out.println(str); // 2026年03月26日 23:17:35 星期四 下午

    }
}
