package com.itheima.stringdemo;

public class Test5 {
    static void main() {
        /*
        介绍下stringBuilder
          StringBuilder 是 Java 中用于高效拼接和修改字符串的可变字符序列类。

          为什么需要 StringBuilder？
          String 是不可变的，每次拼接都会创建新对象：
          String str = "a";
          str += "b";  // 创建新对象
          str += "c";  // 又创建新对象
          // 循环拼接1000次 = 创建1000个对象，浪费内存和性能
         */

        // 1. 创建 StringBuilder
        StringBuilder sb1 = new StringBuilder();  // 默认容量16
        StringBuilder sb2 = new StringBuilder(50);  // 指定初始容量
        StringBuilder sb3 = new StringBuilder("Hello");  // 用字符串初始化

        // 2. append() - 追加内容（最常用）
        sb1.append("Java");
        sb1.append(" ");
        sb1.append("Programming");
        sb1.append(2024);  // 可以追加各种类型
        System.out.println("追加结果: " + sb1);  // Java Programming2024

        // 链式调用
        StringBuilder sb4 = new StringBuilder();
        sb4.append("A").append("B").append("C");
        System.out.println("链式调用: " + sb4);  // ABC

        // 3. insert() - 插入内容
        StringBuilder sb5 = new StringBuilder("Hello World");
        sb5.insert(5, ",");  // 在索引5处插入逗号
        System.out.println("插入结果: " + sb5);  // Hello, World

        // 4. delete() - 删除内容
        StringBuilder sb6 = new StringBuilder("Hello World");
        sb6.delete(5, 11);  // 删除索引5到11之间的内容（包左不包右）
        System.out.println("删除结果: " + sb6);  // Hello

        // 5. deleteCharAt() - 删除指定位置的字符
        StringBuilder sb7 = new StringBuilder("Hello");
        sb7.deleteCharAt(0);  // 删除第一个字符
        System.out.println("删除字符: " + sb7);  // ello

        // 6. replace() - 替换内容
        StringBuilder sb8 = new StringBuilder("Hello World");
        sb8.replace(0, 5, "Hi");  // 将索引0到5的内容替换为"Hi"
        System.out.println("替换结果: " + sb8);  // Hi World

        // 7. reverse() - 反转字符串
        StringBuilder sb9 = new StringBuilder("ABCDE");
        sb9.reverse();
        System.out.println("反转结果: " + sb9);  // EDCBA

        // 8. length() 和 capacity()
        StringBuilder sb10 = new StringBuilder();
        System.out.println("初始长度: " + sb10.length());  // 0
        System.out.println("初始容量: " + sb10.capacity());  // 16
        sb10.append("Hello World Hello World Hello World");
        System.out.println("追加后长度: " + sb10.length());
        System.out.println("追加后容量: " + sb10.capacity());  // 自动扩容

        // 9. toString() - 转换为 String
        StringBuilder sb11 = new StringBuilder("StringBuilder");
        String str = sb11.toString();
        System.out.println("转换为String: " + str);

        // 10. 实际应用：循环拼接（性能对比）
        System.out.println("\n========== 性能对比 ==========");

        // 方式1：String 拼接（慢）
        long start1 = System.currentTimeMillis();
        String result1 = "";
        for (int i = 0; i < 10000; i++) {
            result1 += i;
        }
        long end1 = System.currentTimeMillis();
        System.out.println("String拼接耗时: " + (end1 - start1) + "ms");

        // 方式2：StringBuilder（快）
        long start2 = System.currentTimeMillis();
        StringBuilder sb12 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb12.append(i);
        }
        String result2 = sb12.toString();
        long end2 = System.currentTimeMillis();
        System.out.println("StringBuilder耗时: " + (end2 - start2) + "ms");

        // 11. 实际应用：构建复杂字符串
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM users WHERE ");
        sql.append("age > 18 AND ");
        sql.append("status = 'active' ");
        sql.append("ORDER BY create_time DESC");
        System.out.println("\n构建SQL: " + sql);

    }
}
