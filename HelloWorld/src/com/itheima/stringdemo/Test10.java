package com.itheima.stringdemo;

public class Test10 {
    public static void main(String[] args) {
        /*
           字符串计算
                定义两个字符串，记录为非负整数，求它们的和。
                输入:"12395"和"133"，输出:"12528"
                注意:需要数据过大，超出int取值范围的情况
         */
        // System.out.println('a' + 0); // 97
        // System.out.println('A' + 0); // 65

        String s1 = "12395";
        String s2 = "133";
        int len = Math.max(s1.length(), s2.length());
        int[] arr1 = copyData(s1, len);
        int[] arr2 = copyData(s2, len);

        /*
            for (int i = 0; i < arr1.length; i++) {
                System.out.print(arr1[i] + " ");
            }

            System.out.println();

            for (int i = 0; i < arr2.length; i++) {
                System.out.print(arr2[i] + " ");
            }
         */

        int[] sum = new int[len + 1];
        int num = 0; // 进位
        for (int i = arr1.length - 1; i >= 0; i--) {
            int temp = arr1[i] + arr2[i] + num;
            sum[i + 1] = temp % 10; // 因为sum的长度比arr1和arr2长1
            num = temp / 10;
        }

        sum[0] = num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] != 0) {
                sb.append(sum[i]);
            }
        }

        System.out.println(sb);
    }

    public static int[] copyData(String str, int len) {
        int[] data = new int[len];
        char[] arr = str.toCharArray();
        int index = len - 1;
        for (int i = arr.length; i > 0; i--) {
            int num = arr[i - 1] - 48;
            data[index] = num;
            index--;
        }
        return data;
    }
}
