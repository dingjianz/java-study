package com.itheima.oopjavabean;

public class Test {
    public static void main(String[] args) {
        /*
        我们现在有乒乓球运动员和篮球运动员，乒乓球教练和篮球教练。
        为了出国交流，跟乒乓球相关的人员都需要学习英语。
        请用所有知识分析，在这个案例中，哪些是具体类，哪些是抽象类，哪些是接口?

        乒乓球运动员:姓名，年龄，学打乒乓球，说英语
        篮球运动员:姓名，年龄，学打篮球
        乒乓球教练:姓名，年龄，教打乒乓球，说英语
        篮球教练:姓名，年龄，教打篮球
         */

        PingpangPlayer pingpangPlayer = new PingpangPlayer("张三", 20);
        PingpangCoach pingpangCoach = new PingpangCoach("Peter", 58);

        BasketballPlayer basketballPlayer = new BasketballPlayer("里斯", 24);
        BacketballCoach backetballCoach = new BacketballCoach("王武", 46);

        pingpangPlayer.study();
        pingpangPlayer.speakEnglish();

        basketballPlayer.study();

        pingpangCoach.teach();
        pingpangCoach.speakEnglish();

        backetballCoach.teach();

    }
}
