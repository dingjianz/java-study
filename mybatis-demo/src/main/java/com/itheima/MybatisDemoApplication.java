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


    XML映射配置
    • 在Mybatis中，既可以通过注解配置SQL语句，也可以通过XML配置文件配置SQL语句。

    • 默认规则：
        1. XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）。
        2. XML映射文件的namespace属性为Mapper接口全限定名一致。
        3. XML映射文件中sql语句的id与Mapper接口中的方法名一致，并保持返回类型一致。

    使用注解来映射简单语句会使代码显得更加简洁，
    但对于稍微复杂一点的语句，Java 注解不仅力不从心，
    还会让你本就复杂的 SQL 语句更加混乱不堪。
    因此，如果你需要做一些很复杂的操作，最好用 XML 来映射语句。

    选择何种方式来配置映射，以及认为是否应该要统一映射语句定义的形式，
    完全取决于你和你的团队。
    换句话说，永远不要拘泥于一种方式，
    你可以很轻松的在基于注解和 XML 的语句映射方式间自由移植和切换。

    XML映射文件的位置：application.properties文件中配置
     #指定XML映射配置文件的位置
     mybatis.mapper-locations=classpath:mapper/*.xml

SpringBoot配置文件：SpringBoot项目提供了多种属性配置方式(properties、yaml、yml)
    yml配置文件
    • 格式：
        - 数值前边必须有空格，作为分隔符
        - 使用缩进表示层级关系，缩进时，不允许使用Tab键，只能用空格（idea中会自动将Tab转换为空格）
        - 缩进的空格数目不重要，只要相同层级的元素左侧对齐即可
        - # 表示注释，从这个字符一直到行尾，都会被解析器忽略
     */



    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

}
