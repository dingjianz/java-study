package com.itheima.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC 示例 1：测试 MySQL 连接
 *
 * 使用前提：
 *  1. 已启动 MySQL 服务
 *  2. 已通过 Maven 引入 mysql-connector-j 依赖
 *  3. 已创建数据库 itheima：create database itheima default charset utf8mb4;
 */
public class JdbcDemo01_Connection {

    // JDBC 连接 URL：协议:子协议://主机:端口/数据库名?参数
    private static final String URL = "jdbc:mysql://localhost:3306/itheima"
            + "?useSSL=false"                       // 关闭 SSL（学习环境）
            + "&serverTimezone=Asia/Shanghai"       // 时区
            + "&characterEncoding=utf8"             // 字符编码
            + "&allowPublicKeyRetrieval=true";      // 允许公钥检索（MySQL 8 必需）

    private static final String USER = "root";
    private static final String PASSWORD = "dJ573828395"; // 你的 MySQL 密码

    public static void main(String[] args) {
        // try-with-resources 会自动关闭 Connection
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ 连接成功！");
            System.out.println("数据库产品名称：" + conn.getMetaData().getDatabaseProductName());
            System.out.println("数据库版本：" + conn.getMetaData().getDatabaseProductVersion());
            System.out.println("驱动版本：" + conn.getMetaData().getDriverVersion());
        } catch (SQLException e) {
            System.out.println("❌ 连接失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
