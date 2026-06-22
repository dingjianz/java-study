package com.itheima.exceptionDemo;

public class ThrowsDemo {
    public static void main(String[] args) {
        /*
            throws：写在方法定义处，表示声明一个异常
            告诉调用者，使用本方法可能会有哪些异常
            public void 方法名() throws 异常类名1, 异常类名2, ... {}
                注意：编译时异常：必须要写
                     运行时异常：不需要写

            throw
                写在方法内，结束方法
                手动抛出异常对象，交给调用者
                方法中下面的代码不再执行
     */

        // 需求：定一个方法求数组的最大值

        int[] arr = null;
        int max = 0;
        try {
            max = getMax(arr);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(max);
    }

    public static int getMax(int[] arr) {
        if (arr == null) {
            throw new NullPointerException("数组不能为null");
        } else if (arr.length == 0) {
            throw new ArrayIndexOutOfBoundsException("数组不能为空");
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }
}
