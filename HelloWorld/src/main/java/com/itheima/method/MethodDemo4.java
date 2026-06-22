package com.itheima.method;

public class MethodDemo4 {
    public static void main(String[] args) {
        /*
         java的运行机制
             1. Java源代码（.java文件）被编译成字节码（.class文件）。
             2. Java虚拟机（JVM）加载字节码文件，并将其转换为机器码。
             3. JVM执行机器码，运行Java程序。
          需要注意的是，Java的运行机制是基于平台无关性的，这意味着Java程序可以在任何支持Java虚拟机的操作系统上运行，而无需修改代码。这是Java的一大优势，使得Java成为一种广泛使用的编程语言，适用于各种应用程序开发，包括桌面应用、Web应用、移动应用等。

          内存和内存分配
                1. Java内存模型：Java内存模型定义了Java程序中不同类型的内存区域，包括堆、栈、方法区等。每个线程都有自己的栈内存，用于存储局部变量和方法调用信息，而堆内存用于存储对象实例。
                2. 内存分配：当创建一个对象时，JVM会在堆内存中分配空间来存储该对象的实例数据。同时，局部变量和方法调用信息会在栈内存中分配空间。当方法执行完成后，栈内存中的相关数据会被释放，而堆内存中的对象实例则需要通过垃圾回收机制来管理和释放。

           Java虚拟机把内存分为以下几个部分：
                1. 程序计数器（Program Counter Register）：每个线程都有一个独立的程序计数器，用于存储当前线程正在执行的字节码指令的地址。
                2. Java虚拟机栈（Java Virtual Machine Stack）：每个线程都有一个独立的Java虚拟机栈，用于存储方法调用和局部变量等信息。
                3. 本地方法栈（Native Method Stack）：用于支持本地方法的调用和执行。
                4. Java堆（Java Heap）：用于存储对象实例和数组等数据，是垃圾回收器管理的主要区域。
                5. 方法区（Method Area）：用于存储类信息、常量、静态变量等数据，也是垃圾回收器管理的区域之一。

            栈、堆、方法区的作用
                栈：方法
                堆：new 关键字
                方法区：字节码文件

            传值机制
                Java中的传值机制是指在方法调用时，参数的值被复制到方法的局部变量中。对于基本数据类型，传递的是值的副本，因此在方法内部对参数的修改不会影响到外部的变量。而对于引用数据类型，传递的是对象的引用，即对象在堆内存中的地址，因此在方法内部对对象的修改会影响到外部的对象。
                基本数据类型在内存中的特点：记录的是真实的数据，传递的也是真实的数据。
                引用数据类型在内存中的特点：记录的是对象的地址，传递的也是对象的地址。
        * */

        int a = 10;
        int b = 20;
        System.out.println("a = " + a + ", b = " + b); // 输出 a = 10, b = 20
        change(a, b);
        System.out.println("a = " + a + ", b = " + b); // 输出 a = 10, b = 20

        int[] arr = {1, 2, 3};
        printArr(arr);
        changeArr(arr);
        printArr(arr);
    }

    public static void change(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static void changeArr(int[] arr) {
       int temp = arr[0];
       arr[0] = arr[2];
       arr[2] = temp;
    }

    public static void printArr(int[] arr) {
        String result = "arr = [";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                result += arr[i] + "]";
            } else {
                result += arr[i] + ", ";
            }
        }
        System.out.println(result);
    }
}
