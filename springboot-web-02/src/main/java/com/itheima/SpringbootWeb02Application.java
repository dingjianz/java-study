package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 部门表结构及测试数据
  CREATE TABLE dept (
      id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
      name varchar(10) NOT NULL UNIQUE COMMENT '部门名称',
      create_time datetime DEFAULT NULL COMMENT '创建时间',
      update_time datetime DEFAULT NULL COMMENT '修改时间'
  ) COMMENT '部门表';

  INSERT INTO dept VALUES (1,'学工部','2024-09-25 09:47:40','2024-09-25 09:47:40'),
                          (2,'教研部','2024-09-25 09:47:40','2024-09-09 15:17:04'),
                          (3,'咨询部','2024-09-25 09:47:40','2024-09-30 21:26:24'),
                          (4,'就业部','2024-09-25 09:47:40','2024-09-25 09:47:40'),
                          (5,'人事部','2024-09-25 09:47:40','2024-09-25 09:47:40'),
                          (6,'行政部','2024-11-30 20:56:37','2024-09-30 20:56:37');
 */
/**
 * 数据封装

   手动结果映射：通过 @Results 及 @Result 进行手动结果映射。

     @Results({
         @Result(column = "create_time", property = "createTime"),
         @Result(column = "update_time", property = "updateTime")
     })
     @Select("select id, name, create_time, update_time from dept order by update_time desc")
     public List<Dept> findAll();

   起别名：在SQL语句中，对不一样的列名起别名，别名和实体类属性名一样。
     @Select("select id, name, create_time createTime, update_time updateTime from dept ...")
     public List<Dept> findAll();

   开启驼峰命名：如果字段名与属性名符合驼峰命名规则，mybatis会自动通过驼峰命名规则映射。

     application.yml 配置：
     mybatis:
       configuration:
         map-underscore-to-camel-case: true

 */
@SpringBootApplication
public class SpringbootWeb02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeb02Application.class, args);
    }

}
