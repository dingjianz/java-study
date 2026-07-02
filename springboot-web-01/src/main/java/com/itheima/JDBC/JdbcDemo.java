package com.itheima.JDBC;

public class JdbcDemo {
    /*
     JDCB：Java DataBase Connectivity，java数据库连接。就是使用Java语言操作关系型数据库的一套API。

     本质：
        sun公司官方定义的一套操作所有关系型数据库的规范，即接口。
        各个数据库厂商去实现这套接口，提供数据库驱动jar包。
        我们可以使用这套接口(JDBC)编程，真正执行的代码是驱动jar包中的实现类。


     入门程序：
        1.需求：基于JDBC程序，执行update语句(update user set age = 25 where id = 1)
               步骤：
                    准备工作：创建一个maven项目，引入依赖，并准备数据库表user；
                    代码实现：编写JDBC程序，操作数据库。

        create table user(
             id int unsigned primary key auto_increment comment "id，主键",
             username varchar(20) comment "用户名",
             password varchar(32) comment "密码",
            name varchar(10) comment "姓名",
            age tinyint unsigned comment "年龄"
        ) comment "用户表";

        INSERT INTO user(username, password, name, age)
        VALUES
          ("xiaoqiao", "123456", "小乔", 22),
          ("daqiao", "123456", "大乔", 24),
          ("zhouyu", "123456", "周瑜", 28),
          ("sunce", "123456", "孙策", 27),
          ("luban", "123456", "鲁班", 18),
          ("diaochan", "123456", "貂蝉", 20),
          ("lvbu", "123456", "吕布", 30),
          ("zhaoyun", "123456", "赵云", 26),
          ("guanyu", "123456", "关羽", 35),
          ("zhangfei", "123456", "张飞", 33),
          ("liubei", "123456", "刘备", 38),
          ("caocao", "123456", "曹操", 40),
          ("dianwei", "123456", "典韦", 32),
          ("xuchu", "123456", "许褚", 31),
          ("simayi", "123456", "司马懿", 42);




  │ executeQuery()  │ 执行 SELECT 查询语句                       │ ResultSet（结果集）     │
  │ executeUpdate() │ 执行 INSERT / UPDATE / DELETE 等 DML 语句  │ int（受影响的行数）      │

         静态SQL：使用原始的SQL语句，将SQL语句写死在程序中。参数硬编码。
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("update user set age = 25 where id = 1");
            System.out.println("SQL执行完毕，影响的记录数为：" + i);

         预编译SQL：参数动态传递。
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username = ? and password = ?");
            preparedStatement.setString(1, "daqiao");
            preparedStatement.setString(2, "123456");
            ResultSet resultSet = preparedStatement.executeQuery();

          预编译SQL优势：
            1.可以防止SQL注入，更安全；
            2.性能更高；


     */
}
