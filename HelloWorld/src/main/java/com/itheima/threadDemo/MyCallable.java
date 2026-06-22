package com.itheima.threadDemo;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 求 1~3 的累加和
        int sum = 0;
        for (int i = 1; i <= 3; i++) {
            sum += i;
        }
        return sum;
    }
}
