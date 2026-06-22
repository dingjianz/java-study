package com.itheima.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;

import java.util.Date;
import java.util.List;

/**
 * Hutool 工具类库使用示例
 * Hutool 是一个 Java 工具包，提供了大量的工具类和方法
 */
public class HutoolDemo {
    public static void main(String[] args) {
        System.out.println("=== Hutool 工具类库示例 ===\n");

        // 1. 字符串工具类 StrUtil
        testStringUtil();

        // 2. 日期工具类 DateUtil
        testDateUtil();

        // 3. 集合工具类 CollUtil
        testCollectionUtil();

        // 4. JSON 工具类 JSONUtil
        testJsonUtil();
    }

    /**
     * 测试字符串工具类
     */
    private static void testStringUtil() {
        System.out.println("--- 1. 字符串工具类 StrUtil ---");

        // 判断字符串是否为空
        String str1 = "";
        System.out.println("空字符串判断: " + StrUtil.isEmpty(str1)); // true

        // 字符串格式化
        String formatted = StrUtil.format("我是{}, 今年{}岁", "张三", 25);
        System.out.println("格式化字符串: " + formatted);

        // 去除字符串前后空格
        String str2 = "  hello world  ";
        System.out.println("去除前后空格: [" + StrUtil.trim(str2) + "]");

        // 下划线转驼峰
        String camelCase = StrUtil.toCamelCase("user_name_info");
        System.out.println("下划线转驼峰: " + camelCase);

        System.out.println();
    }

    /**
     * 测试日期工具类
     */
    private static void testDateUtil() {
        System.out.println("--- 2. 日期工具类 DateUtil ---");

        // 获取当前日期时间
        Date now = new Date();
        System.out.println("当前时间: " + DateUtil.now());
        System.out.println("当前日期: " + DateUtil.today());

        // 格式化日期
        String formatted = DateUtil.format(now, "yyyy年MM月dd日 HH:mm:ss");
        System.out.println("格式化日期: " + formatted);

        // 日期偏移
        Date tomorrow = DateUtil.offsetDay(now, 1);
        System.out.println("明天: " + DateUtil.formatDate(tomorrow));

        // 计算日期间隔
        Date pastDate = DateUtil.parse("2024-01-01");
        long betweenDays = DateUtil.between(pastDate, now, DateUnit.DAY);
        System.out.println("距离2024-01-01已过去: " + betweenDays + " 天");

        System.out.println();
    }

    /**
     * 测试集合工具类
     */
    private static void testCollectionUtil() {
        System.out.println("--- 3. 集合工具类 CollUtil ---");

        // 创建集合
        List<String> list = CollUtil.newArrayList("apple", "banana", "orange");
        System.out.println("创建的集合: " + list);

        // 判断集合是否为空
        System.out.println("集合是否为空: " + CollUtil.isEmpty(list));

        // 集合去重
        List<Integer> numbers = CollUtil.newArrayList(1, 2, 2, 3, 3, 4);
        List<Integer> distinct = CollUtil.distinct(numbers);
        System.out.println("去重后: " + distinct);

        // 集合交集
        List<Integer> list1 = CollUtil.newArrayList(1, 2, 3, 4);
        List<Integer> list2 = CollUtil.newArrayList(3, 4, 5, 6);
        System.out.println("交集: " + CollUtil.intersection(list1, list2));

        System.out.println();
    }

    /**
     * 测试 JSON 工具类
     */
    private static void testJsonUtil() {
        System.out.println("--- 4. JSON 工具类 JSONUtil ---");

        // 创建一个对象
        User user = new User("张三", 25, "zhangsan@example.com");

        // 对象转 JSON 字符串
        String jsonStr = JSONUtil.toJsonStr(user);
        System.out.println("对象转JSON: " + jsonStr);

        // JSON 字符串转对象
        User parsedUser = JSONUtil.toBean(jsonStr, User.class);
        System.out.println("JSON转对象: " + parsedUser);

        // 美化输出
        String prettyJson = JSONUtil.toJsonPrettyStr(user);
        System.out.println("美化输出:\n" + prettyJson);

        System.out.println();
    }

    /**
     * 测试用的用户类
     */
    static class User {
        private String name;
        private int age;
        private String email;

        public User() {}

        public User(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
    }
}
