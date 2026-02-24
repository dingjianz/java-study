package com.itheima.operator;

import java.util.Scanner;

public class OperatorDemo4 {
    public static void main(String[] args) {
        /*
        判断语句 if语句
         */

        /* 需求：初始最大生命值200，受到x点伤害，技能回复Y点血，x和y由用户输入，判断最终生命值
        假设，游戏人物永远不会死亡，最少1点血。
        问，最终游戏人物血量多少？

         */
        int hp = 200;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入受到的伤害：");
        int damage = sc.nextInt();
        if (hp - damage <= 1) {
            hp = 1;
        } else {
            hp = hp - damage;
        }
        System.out.println("请输入技能回复的血量：");
        int heal = sc.nextInt();
        if (hp + heal >= 200) {
            hp = 200;
        } else {
            hp = hp + heal;
        }
        System.out.println(hp);

        /*
        卡拉兹函数
        给定正整数n，返回n的卡拉兹函数值。
        卡拉兹函数定义如下：
            卡拉兹函数(n) = n / 2, 如果n是偶数
            卡拉兹函数(n) = 3 * n + 1, 如果n是奇数
         */
        int n = sc.nextInt();
        if (n % 2 == 0) {
        // n是偶数
            n = n / 2;
        } else {
        // n是奇数
            n = 3 * n + 1;
        }
        System.out.println(n);
    }
}
