package com.itheima.exceptionDemo;

public class AgeOutBoundsException  extends RuntimeException{
    public AgeOutBoundsException() {
    }

    public AgeOutBoundsException(String message) {
        super(message);
    }
}
