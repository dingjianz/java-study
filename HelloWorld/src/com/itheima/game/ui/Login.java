package com.itheima.game.ui;

import java.util.Scanner;

public class Login {
    // 登录界面
    public void start() {
        System.out.println("|--------------------------|");
        System.out.println("   🎮 欢迎来到文字格斗游戏 🎮   ");
        System.out.println("|__________________________|");
        System.out.println("请选择操作：1登录 2注册 3退出");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        while(true) {
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("用户选择了退出");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
                    choice = sc.nextInt();
                    break;
            }
        }
    }

    // 登录
    public void login() {
        System.out.println("请输入用户名：");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        System.out.println("用户名是：" + username + "，密码是：" + password);
    }

    // 注册
    public void register() {

    }
}
