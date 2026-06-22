package com.itheima.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC 示例 2：查询数据（SELECT）
 *
 * 准备工作（在 MySQL 中先执行）：
 *  use itheima;
 *  create table tb_user(
 *      id int primary key auto_increment comment '编号',
 *      name varchar(50) comment '姓名',
 *      age int comment '年龄',
 *      gender varchar(1) comment '性别'
 *  ) comment '用户表';
 *
 *  insert into tb_user(name, age, gender) values
 *      ('张三', 20, '男'),
 *      ('李四', 22, '男'),
 *      ('王五', 18, '女');
 */
public class JdbcDemo02_Query {

    private static final String URL = "jdbc:mysql://localhost:3306/itheima"
            + "?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "dJ573828395";

    public static void main(String[] args) {
        // 查询年龄大于 18 岁的用户
        String sql = "SELECT id, name, age, gender FROM tb_user WHERE age > ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 设置参数：第一个 ? 的值为 18
            ps.setInt(1, 18);

            // 执行查询，得到结果集
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("ID\t姓名\t年龄\t性别");
                System.out.println("-----------------------------");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");

                    System.out.println(id + "\t" + name + "\t" + age + "\t" + gender);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
