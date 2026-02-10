package com.itheima.variable;

public class VariableDemo4 {
    /*
        计算你的 BMI 指数
        BMI（Body Mass Index）指数是用来评估一个人是否处于健康体重范围的指标。计算公式为：BMI = 体重（kg）/ 身高（m）的平方。

        < 18.5 体重过轻
        18.5 - 23.9 正常范围
        24.0 - 26.9 体重过重
        27.0 - 29.9 肥胖
        >= 30 严重肥胖
     */
    public static void main() {
        // 1. 定义变量记录体重和身高
        double weight = 86.0; // 体重，单位为千克
        double height = 1.87; // 身高，单位为米

        // 2. 计算BMI指数
        double bmi = weight / (height * height);
        System.out.println(bmi);

        // 扩展：计算你当前的身高，在标准的BMI范围内，体重应该是多少？
        double minWeight = 18.5 * (height * height); // BMI为18.5时的最小体重
        double maxWeight = 23.9 * (height * height); // BMI为23.9时的最大体重
        System.out.println("在标准的BMI范围内，体重应该在 " + minWeight + " kg 和 " + maxWeight + " kg");
    }
}
