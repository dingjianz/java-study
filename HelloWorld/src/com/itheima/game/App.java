package com.itheima.game;


import com.itheima.game.ui.Login;

public class App {
    public static void main() {
        /*
            启动类
            这个类只负责启动整个程序，里面不写任何的业务逻辑
         */

        System.out.println("iflytek" + String.format("%05d", (int) (Math.random() * 100000)));
        // 启动登录界面
        Login login = new Login();
        login.start();
    }
}
