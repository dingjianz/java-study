package com.itheima.exceptionDemo;

public class NameFormateException extends RuntimeException {
    public NameFormateException() {
    }

    public NameFormateException(String message) {
        super(message);
    }
}
