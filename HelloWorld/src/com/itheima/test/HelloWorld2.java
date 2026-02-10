package com.itheima.test;
// package: 用来指定当前类所在的包

// 表示定义一个类
// class： 表示定义一个类，类是Java中最重要的结构，最基本的组成单元，是对象的模板
// HelloWorld2: 类的名字，类名必须以字母开头，后面可以跟任意数量的字母、数字、下划线和美元符号，但不能使用Java的关键字作为类名
// {}: 大括号，用来表示类的开始和结束
public class HelloWorld2 {

    // main方法: 程序的入口，程序从这里开始执行，逐行执行里面的代码
    // public: 访问修饰符，表示该方法是公共的，可以被任何类访问
    // static: 静态的，表示该方法属于类本身，而不是类的实例
    // void: 返回类型，表示该方法不返回任何值
    // String[] args: 参数，表示该方法接受一个字符串数组作为参数，通常用于命令行参数
    public static void main(String[] args) {
        // 输出语句/打印语句，表示将里面的内容输出到控制台
       System.out.println("Hello World");
    }
}
