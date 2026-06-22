package com.itheima.operator;

public class OperatorDemo2 {
    public static void main(String[] args) {
        /*

            数字运算：类型不一样不能运算，需要转成同类型的才能计算。

            1. 类型转换：小 -> 大
            byte -> short -> int -> long -> float -> double

            隐式转换的规则：如果参与运算的数中有double类型，则结果是double类型；如果参与运算的数中有float类型，则结果是float类型；如果参与运算的数中有long类型，则结果是long类型；否则结果是int类型。

            触发时机：不同类型的数据进行计算，默认采取隐式转换。Java自动转换，无需我们写代码。
            转换步骤1：如有byte、short、char类型参与运算，先转换成int类型，再进行计算。
            转换步骤2: 把取值范围小的提升为取值范围大的，再进行计算。


            2. 类型转换：大 -> 小
                强制转换：需要我们写代码，告诉Java把大类型转换成小类型。
                语法格式：数据类型 变量名 = (数据类型) 数据;

         */


        /*
            字符运算
            1. 字符类型在计算时会被转换成对应的整数值进行计算，计算结果也是整数类型。
         */


        /*
            字符串运算
            1. 字符串类型在计算时会被转换成字符串进行计算，计算结果也是字符串类型。

         */
        byte a = 1;
        byte b = 2;
        int c = a + b;
        System.out.println(c);

        byte d = (byte) (a + b);
        System.out.println(d);

        short e = 100;
        short f = 200;
        // 300的二进制是0000 0001 0010 1100，截取后8位是0010 1100，转换成十进制是44
        byte g = (byte) (e + f);
        System.out.println(g); // 44

        char h = 'A'; // 65 AsCII码表中，字符'A'对应的整数值是65
        char i = 'B'; // 66 AsCII码表中，字符'B'对应的整数值是66
        int j = h + i; // 131
        System.out.println(j);

        // 需求：将大写的 A 转换成小写的 a
        char k = (char) (h + 32); // 97 AsCII码表中，字符'a'对应的整数值是97
        System.out.println(k);

        // 字符串的运算
        String s1 = 123 + "aaa"; // "123aaa"
        System.out.println(s1);

        int s2 = 10 + 8 + '岁'; // 23699
        String s3 = 10 + 8 + "岁"; // "18岁"
        String s4 = 10 + "8" + '岁'; // "108岁"
        String s5 = 10 + 8 + "岁" + 2 + 3;
        System.out.println(s2);
        System.out.println(s3);
    }
}
