package com.itheima.game.ui;

import com.itheima.game.bean.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {


    // 登录界面
    public void start() {
        System.out.println("|--------------------------|");
        System.out.println("   🎮 欢迎来到文字格斗游戏 🎮   ");
        System.out.println("|__________________________|");
        System.out.println("请选择操作：1登录 2注册 3退出");

        ArrayList<User> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        while (true) {
            switch (choice) {
                case 1:
                    login(list);
                    break;
                case 2:
                    register(list);
                    break;
                case 3:
                    System.out.println("用户选择了退出");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
                    break;
            }
        }
    }

    // 登录
    public void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户名：");
            String username = sc.next();
            if (checkLen(3, 6, username)) {
                System.out.println("用户名的长度必须是3-16位");
                continue;
            }
        }
        System.out.println("请输入密码：");
        String password = sc.next();
        System.out.println("用户名是：" + username + "，密码是：" + password);
    }

    // 注册
    public void register(ArrayList<User> list) {

    }

    // 判断字符的长度是否在指定的范围之内
    public boolean checkLen(int minLen, int maxLen, String str) {
        return  str.length() >= minLen && str.length() <= maxLen;
    }
}
