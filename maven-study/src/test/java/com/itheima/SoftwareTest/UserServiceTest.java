package com.itheima.SoftwareTest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * UserService 的单元测试
 * <p>
 * 身份证号格式（18位）：
 * - 第 1-6 位：地区码
 * - 第 7-14 位：出生日期 yyyyMMdd
 * - 第 17 位：性别位，奇数为男、偶数为女
 * - 第 18 位：校验位
 */
class UserServiceTest {

    private UserService userService;

    @BeforeAll
    static void setUpAll() {
        System.out.println("setUpAll");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach");
        userService = new UserService();
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");
    }

    // ---------- getAge ----------

    @Test
    @DisplayName("getAge: 正常身份证号应返回正确年龄")
    void getAge_validIdCard_returnsExpectedAge() {
        // 第 7-14 位为出生日期 19900307
        String idCard = "110101199003071234";

        // 用与实现相同的方式计算期望年龄，避免随当前日期变化而失效
        LocalDate birthDate = LocalDate.parse("19900307", DateTimeFormatter.ofPattern("yyyyMMdd"));
        int expected = Period.between(birthDate, LocalDate.now()).getYears();

        assertEquals(expected, userService.getAge(idCard));
    }

    @Test
    @DisplayName("getAge: 生日当天应正确计入当年")
    void getAge_birthdayToday_countsCurrentYear() {
        LocalDate today = LocalDate.now();
        LocalDate birth = today.minusYears(20);
        String birthStr = birth.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String idCard = "110101" + birthStr + "1234";

        assertEquals(20, userService.getAge(idCard));
    }

    @Test
    @DisplayName("getAge: 生日还没过则不计入当年")
    void getAge_birthdayNotYetReached_excludesCurrentYear() {
        LocalDate birth = LocalDate.now().minusYears(20).plusDays(1);
        String birthStr = birth.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String idCard = "110101" + birthStr + "1234";

        assertEquals(19, userService.getAge(idCard));
    }

    @Test
    @DisplayName("getAge: 传入 null 应抛出 IllegalArgumentException")
    void getAge_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> userService.getAge(null));
    }

    @Test
    @DisplayName("getAge: 长度不足 18 位应抛出 IllegalArgumentException")
    void getAge_wrongLength_throws() {
        assertThrows(IllegalArgumentException.class, () -> userService.getAge("12345"));
    }

    @Test
    @DisplayName("getAge: 出生日期非法（月份 13）应抛出 IllegalArgumentException")
    void getAge_invalidBirthDate_throws() {
        // 第 7-14 位为 19901307，13 月不存在
        String idCard = "110101199013071234";
        assertThrows(IllegalArgumentException.class, () -> userService.getAge(idCard));
    }

    @Test
    @DisplayName("getAge: 出生日期晚于当前日期应抛出 IllegalArgumentException")
    void getAge_futureBirthDate_throws() {
        LocalDate future = LocalDate.now().plusYears(1);
        String birthStr = future.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String idCard = "110101" + birthStr + "1234";

        assertThrows(IllegalArgumentException.class, () -> userService.getAge(idCard));
    }

    // ---------- getGender ----------

    @Test
    @DisplayName("getGender: 第 17 位为奇数应返回男")
    void getGender_oddDigit_returnsMale() {
        // 第 17 位（下标 16）为 '3'，奇数
        String idCard = "110101199003071234";
        assertEquals("男", userService.getGender(idCard));
    }

    @Test
    @DisplayName("getGender: 第 17 位为偶数应返回女")
    void getGender_evenDigit_returnsFemale() {
        // 第 17 位（下标 16）为 '2'，偶数
        String idCard = "110101199003071224";
        assertEquals("女", userService.getGender(idCard));
    }

    @Test
    @DisplayName("getGender: 传入 null 应抛出 IllegalArgumentException")
    void getGender_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> userService.getGender(null));
    }

    @Test
    @DisplayName("getGender: 长度不足 18 位应抛出 IllegalArgumentException")
    void getGender_wrongLength_throws() {
        assertThrows(IllegalArgumentException.class, () -> userService.getGender("110101"));
    }
}
