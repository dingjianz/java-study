package com.itheima.variable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDem2 {
    public static void main(String[] args) {
        /*
            public BigDecimal add(BigDecimal val)   加法
            public BigDecimal subtract(BigDecimal val)   减法
            public BigDecimal multiply(BigDecimal val)   乘法
            public BigDecimal divide(BigDecimal val)   除法
            public BigDecimal divide(BigDecimal val, int scale, int roundingMode)   除法
              int scale：精确到几位
              int roundingMode：四舍五入的规则
         */

        BigDecimal b1 = new BigDecimal("0.01");
        BigDecimal b2 = new BigDecimal("0.02");

        // 加法
        BigDecimal b3 = b1.add(b2);
        System.out.println(b3); // 0.03

        // 减法
        BigDecimal b4 = b1.subtract(b2);
        System.out.println(b4); // -0.01

        // 乘法
        BigDecimal b5 = b1.multiply(b2);
        System.out.println(b5); // 0.0002

        // 除法
        BigDecimal b6 = new BigDecimal(10);
        BigDecimal b7 = new BigDecimal(3);

        /*
            0 除以 3 的结果是无限循环小数（3.333333...），
            而 BigDecimal 的无参 divide() 方法要求结果必须是精确的有限小数，
            否则就会抛出 ArithmeticException:
            Non-terminating decimal expansion 异常。

            RoundingMode
                UP - 远离零方向舍入（向上取整）
                  - 正数：向正无穷方向
                  - 负数：向负无穷方向
                  - 例：1.1 → 2，-1.1 → -2

              DOWN - 向零方向舍入（向下取整）
              - 直接截断小数部分
              - 例：1.9 → 1，-1.9 → -1

              CEILING - 向正无穷方向舍入
              - 正数：向上取整
              - 负数：向零方向
              - 例：1.1 → 2，-1.9 → -1

              FLOOR - 向负无穷方向舍入
              - 正数：向零方向
              - 负数：向下取整
              - 例：1.9 → 1，-1.1 → -2

              HALF_UP - 四舍五入（最常用）
              - ≥0.5：远离零方向
              - <0.5：向零方向
              - 例：1.5 → 2，1.4 → 1，-1.5 → -2

              HALF_DOWN - 五舍六入
              - >0.5：远离零方向
              - ≤0.5：向零方向
              - 例：1.5 → 1，1.6 → 2

              HALF_EVEN - 银行家舍入法（推荐用于金融计算）
              - 当舍弃部分恰好为 0.5 时，舍入到最近的偶数
              - 可以减少累计误差
              - 例：1.5 → 2，2.5 → 2，3.5 → 4

              UNNECESSARY - 断言不需要舍入
              - 如果需要舍入则抛出 ArithmeticException

         */
        System.out.println(b6.divide(b7, 2, RoundingMode.HALF_UP)); // 3.3333
        System.out.println(b6.divide(b7, 2, RoundingMode.UP)); // 3.34

    }
}
