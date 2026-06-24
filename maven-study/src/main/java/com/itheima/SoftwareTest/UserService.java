package com.itheima.SoftwareTest;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserService {
    /*
        给出一个身份证号，计算出该用户的年龄
        @param idCard 身份证号
     */
    public Integer getAge(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            throw new IllegalArgumentException("请输入18位身份证号");
        }
        // 截取出生日期部分(第7到14位:yyyyMMdd)
        String birthDay = idCard.substring(6, 14);
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("身份证号中的出生日期无效:" + birthDay, e);
        }
        // 获取当前日期
        LocalDate now = LocalDate.now();
        if (birthDate.isAfter(now)) {
            throw new IllegalArgumentException("出生日期不能晚于当前日期");
        }
        // Period 会自动按年/月/日计算,生日没过的不计入当年
        return Period.between(birthDate, now).getYears();
    }

    /*
        给出一个身份证号，返回该用户的性别
        @param idCard 身份证号
        @return 性别
     */
    public String getGender(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            throw new IllegalArgumentException("请输入18位身份证号");
        }
        return Integer.parseInt(idCard.substring(16, 17)) % 2 == 1 ? "男" : "女";
    }
}
