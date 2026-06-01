package com.itheima.ioDemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PrintStreamDemo1 {
    public static void main(String[] args) throws FileNotFoundException {
    /*
        字节打印流
            构造方法：
                public PrintStream(OutputStream/File/String) 关联字节输出流/文件/文件路径
                public PrintStream(String fileName, String charsetName)  指定字符编码
                public PrintStream(OutputStream out, boolean autoFlush)  自动刷新
                public PrintStream(OutputStream out, boolean autoFlush, String charsetName) 指定字符编码和自动刷新

           成员方法：
                public void write(int b) 常规方法：规则和之前一样，将指定的字节写出
                public void print(String s) 特有方法：打印任意数据，不换行
                public void println(String s) 特有方法：打印任意数据，自动刷新，自动换行
                public void printf(String format, Object... args) 特有方法：带有占位符的打印语句，不换行

                %n 表示换行
                %s	字符串	ps.printf("我叫%s %n", "阿玮")	我叫阿玮
                %c	字符（char）	ps.printf("字母H的大写：%c %n", 'H')	字母H的大写：H
                %b	布尔值	ps.printf("8>3的结果是：%b %n", 8 > 3)	8>3的结果是：true
                %d	十进制整数	ps.printf("100的一半是：%d %n", 100 / 2)	100的一半是：50
                %x	十六进制整数	ps.printf("100的16进制数是：%x %n", 100)	100的16进制数是：64
                %o	八进制整数	ps.printf("100的8进制数是：%o %n", 100)	100的8进制数是：144
                %f	浮点数（十进制）	ps.printf("50元的书打8.5折扣是：%f元%n", 50 * 0.85)	42.500000元
                %a	浮点数（十六进制）	ps.printf("计算的结果转16进制：%a %n", 50 * 0.85)	0x1.54p+5
                %e	科学计数法	ps.printf("计算的结果转科学计数法表示：%e %n", 50 * 0.85)	4.250000e+01
                %g	较短的浮点表示	ps.printf("...结果的长度较短的是：%g %n", 50 * 0.85)	42.5000
                %%	输出字面量 %	ps.printf("带有百分号的符号表示法，以百分之85为例：%d%% %n", 85)	85%

                精度控制
                double num1 = 1.0;
                ps.printf("num: %.4g %n", num1);  // 4位有效数字 → 1.000
                ps.printf("num: %.5g %n", num1);  // 5位有效数字 → 1.0000
                ps.printf("num: %.6g %n", num1);  // 6位有效数字 → 1.00000

                float num2 = 1.0F;
                ps.printf("num: %.4f %n", num2);  // 小数点后4位 → 1.0000
                ps.printf("num: %.5f %n", num2);  // 小数点后5位 → 1.00000
                ps.printf("num: %.6f %n", num2);  // 小数点后6位 → 1.000000
                %g vs %f 的区别：

                %.Ng：N 是有效数字总位数（整数+小数）
                %.Nf：N 是小数点后的位数


                扩展知识点
                    1. %n vs \n 的区别

                    // \n 是 Java 字符串转义，固定输出 Unix 换行符 (LF)
                    ps.printf("hello\n");

                    // %n 是平台相关换行符，Windows 输出 \r\n，Linux/Mac 输出 \n
                    ps.printf("hello%n");
                    推荐在 printf 中用 %n，跨平台兼容性更好。

                    2. 宽度与对齐控制

                    // %10s → 右对齐，总宽度10
                    ps.printf("%10s%n", "hello");    // "     hello"

                    // %-10s → 左对齐，总宽度10
                    ps.printf("%-10s|%n", "hello");  // "hello     |"

                    // %08d → 整数补零，总宽度8
                    ps.printf("%08d%n", 42);         // "00000042"

                    // %+d → 强制显示正负号
                    ps.printf("%+d %+d%n", 42, -42); // "+42 -42"
                    3. String.format() 与 printf 同源
                    printf 的格式化规则和 String.format() 完全一致，区别只是一个直接输出、一个返回字符串：


                    // printf 直接输出
                    ps.printf("我叫%s，今年%d岁%n", "阿玮", 18);

                    // String.format 返回格式化后的字符串
                    String msg = String.format("我叫%s，今年%d岁", "阿玮", 18);
                    System.out.println(msg);

                    // Java 15+ 的 formatted() 方法，更简洁
                    String msg2 = "我叫%s，今年%d岁".formatted("阿玮", 18);
                    4. PrintStream vs PrintWriter
                    特性	PrintStream	PrintWriter
                    基类	OutputStream（字节流）	Writer（字符流）
                    编码处理	需手动指定 Charset	天然支持字符编码
                    典型用途	System.out、文件字节输出	文本文件写入、网络响应
                    自动刷新	println 触发	构造时可指定 autoFlush
                    文本文件写入推荐用 PrintWriter，编码处理更直观：


                    PrintWriter pw = new PrintWriter(
                        new FileWriter("output.txt", StandardCharsets.UTF_8), true
                    );
                    pw.printf("格式化输出：%s%n", "内容");
                    pw.close();
                    5. System.out 本质就是 PrintStream

                    // System.out 的类型就是 PrintStream
                    PrintStream out = System.out;
                    out.printf("直接用 System.out.printf：%d%n", 100);

                    // 可以重定向 System.out 到文件
                    PrintStream fileOut = new PrintStream("log.txt");
                    System.setOut(fileOut);  // 之后所有 System.out 输出都写入文件
                    System.out.println("这行写到文件里了");

     */

        // 1.创建字节打印流的对象
        PrintStream ps = new PrintStream(new FileOutputStream("src/com/itheima/ioDemo/1.txt"), true, StandardCharsets.UTF_8);
        // println: 自动刷新，自动换行, 原数据 写到方法的是97 到文件中也是97
        ps.println(97);
        ps.println(98);

        // print: 不自动刷新，不自动换行, 原数据 写到方法的是1 到文件中也是1
        ps.print(1);
        ps.print("hello world");

        // printf：不自动刷新，不自动换行, 原数据
        ps.printf("%s爱%n上了%s", "阿珍", "阿强");
        ps.printf("%d", 97);
        ps.close();

    }
}
