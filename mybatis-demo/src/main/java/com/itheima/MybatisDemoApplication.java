package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisDemoApplication {

    /*
    MyBatis：是一款优秀的 持久层 框架，用于简化JDBC 开发工作。
    准备工作：
        1.创建SpringBoot工程，引入Mybatis相关依赖（lombok、mybatis framework、mysql driver ）
        2.准备数据库表user、实体类User
        3.配置Mybatis(在application.properties中数据库连接信息)
    编写Mybatis程序：编写Mybatis的持久层接口，定义SQL(注解/XML)

    application.properties:
        spring.datasource.url=jdbc:mysql://localhost:3306/web01
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.username=root
        spring.datasource.password=123456

    Mybatis的持久层接口命名规范为 XxxMapper，也称为 Mapper接口

    @Mapper
    public interface UserMapper {
        @Select("select * from user")
        public List<User> findAll();
    }


    辅助配置-配置SQL提示
        产生原因：idea和数据库没有建立连接，不识别表信息
        解决方式：在idea中配置MySQL数据库连接

    辅助配置-配置Mybatis的日志输出
    默认情况下，在Mybatis中，SQL语句执行时，看不到SQL语句的执行日志

    #mybatis的配置 日志打印到终端
    mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

    数据库连接池
    • 数据库连接池是个容器，负责分配、管理数据库连接(Connection)。
    • 它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个。
    • 释放空闲时间超过最大空闲时间的连接，来避免因为没有释放连接而引起的数据库连接遗漏。
    • 优势：
        1. 资源重用
        2. 提升系统响应速度
        3. 避免数据库连接遗漏

    • 标准接口：DataSource
        ➤ 官方(sun)提供的数据库连接池接口，由第三方组织实现此接口。
        ➤ 功能：获取连接    Connection getConnection() throws SQLException;

    • Hikari（全称 HikariCP）是一个数据库连接池。

            它的特点：

            高性能：号称是目前最快的 Java 连接池实现，字节码级别的优化
            轻量级：代码量少，依赖少
            稳定可靠：被广泛使用和测试
            Spring Boot 默认：从 Spring Boot 2.0 开始，HikariCP 成为默认的数据库连接池（之前是 Tomcat JDBC Pool）
            与其他连接池的对比：

            C3P0：老牌连接池，但性能较慢，已经较少使用
            DBCP：Apache 的连接池，性能一般
            Druid（德鲁伊）：阿里巴巴开源，功能丰富（监控、统计），国内使用较多
            Hikari：性能最优，Spring Boot 官方推荐


    删除用户-delete
    Mybatis中的 #号 与 $号：

    ┌─────────┬──────────────────────────────────────────────┬────────────────────────┬──────────────────────┐
    │  符号   │                    说明                       │         场景            │       优缺点          │
    ├─────────┼──────────────────────────────────────────────┼────────────────────────┼──────────────────────┤
    │ #{...}  │ 占位符。执行时，会将 #{...} 替换为 ?，生成预编译SQL │ 参数值传递              │ 安全、性能高（推荐）   │
    ├─────────┼──────────────────────────────────────────────┼────────────────────────┼──────────────────────┤
    │ ${...}  │ 拼接符。直接将参数拼接在SQL语句中，存在SQL注入问题  │ 表名、字段名动态设置时使用  │ 不安全、性能低        │
    └─────────┴──────────────────────────────────────────────┴────────────────────────┴──────────────────────┘

    示例：
        @Delete("delete from dept where id = #{id}")

        @Select("select id,name,score from ${tableName} order by ${sortField}")

     */


    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

}
