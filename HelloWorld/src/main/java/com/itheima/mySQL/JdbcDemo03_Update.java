package com.itheima.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC 示例 3：增、删、改操作（DML）
 *
 *  - 使用 PreparedStatement 防止 SQL 注入
 *  - executeUpdate() 返回受影响的行数
 */
public class JdbcDemo03_Update {

    private static final String URL = "jdbc:mysql://localhost:3306/itheima"
            + "?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "dJ573828395";

    public static void main(String[] args) throws SQLException {
        insert();   // 新增
        update();   // 修改
        delete();   // 删除
    }

    /** 新增一条用户数据 */
    public static void insert() throws SQLException {
        String sql = "INSERT INTO tb_user(name, age, gender) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "赵六");
            ps.setInt(2, 25);
            ps.setString(3, "男");

            int rows = ps.executeUpdate();
            System.out.println("插入成功，受影响行数：" + rows);
        }
    }

    /** 修改用户年龄 */
    public static void update() throws SQLException {
        String sql = "UPDATE tb_user SET age = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 26);
            ps.setString(2, "赵六");

            int rows = ps.executeUpdate();
            System.out.println("修改成功，受影响行数：" + rows);
        }
    }

    /** 删除用户 */
    public static void delete() throws SQLException {
        String sql = "DELETE FROM tb_user WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "赵六");

            int rows = ps.executeUpdate();
            System.out.println("删除成功，受影响行数：" + rows);
        }
    }
}
