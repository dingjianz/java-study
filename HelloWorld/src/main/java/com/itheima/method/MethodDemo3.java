package com.itheima.method;

public class MethodDemo3 {
    public static void main(String[] args) {
        /*
         方法重载（Overloading）是指在同一个类中，允许存在多个方法名相同但参数列表不同的方法。编译器根据方法调用时传递的参数类型和数量来确定调用哪个方法。

         同一个类中，定义了多个方法，这些方法具有相同的方法名，但参数列表不同（参数的类型、数量或顺序不同）。编译器根据方法调用时传递的参数类型和数量来确定调用哪个方法。
            方法重载的好处：
                1. 提高代码的可读性：方法重载允许使用相同的方法名来表示不同的功能，使代码更易于理解和维护。
                2. 增强代码的灵活性：通过方法重载，可以根据不同的参数类型和数量来实现不同的功能，增加了代码的灵活性和适应性。
                3. 提供更好的用户体验：方法重载可以让用户使用相同的方法名来调用不同的功能，提供更好的用户体验。

                简单理解：同一个类，方法名相同，参数不同的方法，无需看返回值。
                参数不同包括：个数不同、类型不同、顺序不同。

            需要注意的是，方法重载仅仅是方法名相同，参数列表不同，并不考虑方法的返回类型。也就是说，方法的返回类型不能作为区分重载方法的依据。
        * */

        int a = 1;
        int b = 2;
        int[] arr = new int[]{1, 2, 3};
        String[] strArr = new String[]{"a", "b", "c"};
        System.out.println(A.sum(a, b)); // 调用 sum(int a, int b) 方法
        System.out.println(arr);
        System.out.println(strArr);
    }

    // 定义一个类
    public static class A {
        public static int sum(int a, int b) {
            return a;
        }

        public static double sum(int a, double b) {
            return a + b;
        }

        public static double sum(double a, int b) {
            return a + b;
        }

        public static double sum(double a, double b) {
            return a + b;
        }

    }

}
