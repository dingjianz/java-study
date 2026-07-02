package com.itheima.JDBC;

import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcDemoTest {
    /*
    JDBC入门程序
     */

    @Test
    public void testUpdate() throws Exception {
        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        String mysqlUrl = "jdbc:mysql://localhost:3306/web01"; // jdbc:mysql:// 是固定协议
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(mysqlUrl, username, password);

        //3.获取SQL语句执行对象
        Statement statement = connection.createStatement();

        //4.执行SQL
        String sql = "update user set age = 25 where id = 9";
        int count = statement.executeUpdate(sql); //   executeUpdate 返回一个 int 类型的值，表示受影响的行数：
        System.out.println(count);

        //5.释放资源
        statement.close();
        connection.close();
    }

    @Test
    public  void testSelect() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // 封装查询返回的结果

        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.获取数据库连接
            String mysqlUrl = "jdbc:mysql://localhost:3306/web01";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(mysqlUrl, username, password);

            // 3.获取SQL语句执行对象
            preparedStatement = connection.prepareStatement("select * from user where id = ?"); // ? 表示占位符
            preparedStatement.setInt(1, 9); // 设置占位符的值
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt("id"))
                        .age(resultSet.getInt("age"))
                        .name(resultSet.getString("name"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .build();
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
