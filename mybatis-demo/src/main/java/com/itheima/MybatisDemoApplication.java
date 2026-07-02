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
     */


    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

}
