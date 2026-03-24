package com.itheima.datedemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestLocale {
    public static void main(String[] args) {
        Date d = new Date(0L);

        System.out.println("=== 使用 EE (简写) ===");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd EE", Locale.CHINA);
        System.out.println("Locale.CHINA: " + sdf1.format(d));

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd EE", Locale.US);
        System.out.println("Locale.US: " + sdf2.format(d));

        System.out.println("\n=== 使用 EEEE (完整) ===");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd EEEE", Locale.CHINA);
        System.out.println("Locale.CHINA: " + sdf3.format(d));

        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd EEEE", Locale.US);
        System.out.println("Locale.US: " + sdf4.format(d));
    }
}