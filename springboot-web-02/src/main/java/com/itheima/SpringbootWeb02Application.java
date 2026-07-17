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


  CREATE TABLE emp(
      id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID,主键',
      username varchar(20) NOT NULL UNIQUE COMMENT '用户名',
      password varchar(32) NOT NULL COMMENT '密码',
      name varchar(10) NOT NULL COMMENT '姓名',
      gender tinyint unsigned NOT NULL COMMENT '性别, 1:男, 2:女',
      phone char(11) NOT NULL UNIQUE COMMENT '手机号',
      job tinyint unsigned COMMENT '职位, 1:班主任,2:讲师...',
      salary int unsigned COMMENT '薪资',
      image varchar(255) COMMENT '头像',
      entry_date date COMMENT '入职日期',
      create_time datetime COMMENT '创建时间',
      update_time datetime COMMENT '修改时间',
      dept_id int unsigned comment '部门ID'
  ) COMMENT '员工表';

  INSERT INTO emp (username, password, name, gender, phone, job, salary, image, entry_date, create_time, update_time, dept_id)
  VALUES
      ('songjiang', 'e10adc3949ba59abbe56e057f20f883e', '宋江', 1, '13800138001', 1, 12000, 'https://example.com/songjiang.jpg', '2022-01-01', '2022-01-01 09:00:00', '2022-01-01 09:00:00', 1),
      ('lujunyi', 'e10adc3949ba59abbe56e057f20f883e', '卢俊义', 1, '13800138002', 1, 11500, 'https://example.com/lujunyi.jpg', '2022-02-15', '2022-02-15 09:00:00', '2022-02-15 09:00:00', 1),
      ('wuyong', 'e10adc3949ba59abbe56e057f20f883e', '吴用', 1, '13800138003', 2, 10800, 'https://example.com/wuyong.jpg', '2022-03-10', '2022-03-10 09:00:00', '2022-03-10 09:00:00', 1),
      ('gongsunsheng', 'e10adc3949ba59abbe56e057f20f883e', '公孙胜', 1, '13800138004', 2, 10500, 'https://example.com/gongsunsheng.jpg', '2022-04-05', '2022-04-05 09:00:00', '2022-04-05 09:00:00', 1),
      ('guansheng', 'e10adc3949ba59abbe56e057f20f883e', '关胜', 1, '13800138005', 1, 10200, 'https://example.com/guansheng.jpg', '2022-05-12', '2022-05-12 09:00:00', '2022-05-12 09:00:00', 1),
      ('linchong', 'e10adc3949ba59abbe56e057f20f883e', '林冲', 1, '13800138006', 1, 11000, 'https://example.com/linchong.jpg', '2022-06-18', '2022-06-18 09:00:00', '2022-06-18 09:00:00', 1),
      ('qinming', 'e10adc3949ba59abbe56e057f20f883e', '秦明', 1, '13800138007', 1, 9800, 'https://example.com/qinming.jpg', '2022-07-22', '2022-07-22 09:00:00', '2022-07-22 09:00:00', 1),
      ('huyanzhuo', 'e10adc3949ba59abbe56e057f20f883e', '呼延灼', 1, '13800138008', 1, 9600, 'https://example.com/huyanzhuo.jpg', '2022-08-08', '2022-08-08 09:00:00', '2022-08-08 09:00:00', 1),
      ('huarong', 'e10adc3949ba59abbe56e057f20f883e', '花荣', 1, '13800138009', 2, 9400, 'https://example.com/huarong.jpg', '2022-09-15', '2022-09-15 09:00:00', '2022-09-15 09:00:00', 1),
      ('chaijin', 'e10adc3949ba59abbe56e057f20f883e', '柴进', 1, '13800138010', 2, 9200, 'https://example.com/chaijin.jpg', '2022-10-20', '2022-10-20 09:00:00', '2022-10-20 09:00:00', 1),
      ('likui', 'e10adc3949ba59abbe56e057f20f883e', '李逵', 1, '13800138011', 1, 8800, 'https://example.com/likui.jpg', '2022-11-10', '2022-11-10 09:00:00', '2022-11-10 09:00:00', 1),
      ('wusong', 'e10adc3949ba59abbe56e057f20f883e', '武松', 1, '13800138012', 1, 10500, 'https://example.com/wusong.jpg', '2023-01-05', '2023-01-05 09:00:00', '2023-01-05 09:00:00', 1),
      ('luzhishen', 'e10adc3949ba59abbe56e057f20f883e', '鲁智深', 1, '13800138013', 1, 10300, 'https://example.com/luzhishen.jpg', '2023-02-12', '2023-02-12 09:00:00', '2023-02-12 09:00:00', 1),
      ('yangzhi', 'e10adc3949ba59abbe56e057f20f883e', '杨志', 1, '13800138014', 2, 9100, 'https://example.com/yangzhi.jpg', '2023-03-18', '2023-03-18 09:00:00', '2023-03-18 09:00:00', 1),
      ('shijin', 'e10adc3949ba59abbe56e057f20f883e', '史进', 1, '13800138015', 2, 8900, 'https://example.com/shijin.jpg', '2023-04-22', '2023-04-22 09:00:00', '2023-04-22 09:00:00', 1),
      ('sunli', 'e10adc3949ba59abbe56e057f20f883e', '孙立', 1, '13800138016', 2, 8700, 'https://example.com/sunli.jpg', '2023-05-08', '2023-05-08 09:00:00', '2023-05-08 09:00:00', 1),
      ('zhutong', 'e10adc3949ba59abbe56e057f20f883e', '朱仝', 1, '13800138017', 2, 8600, 'https://example.com/zhutong.jpg', '2023-06-14', '2023-06-14 09:00:00', '2023-06-14 09:00:00', 1),
      ('lujunyi2', 'e10adc3949ba59abbe56e057f20f883e', '雷横', 1, '13800138018', 2, 8500, 'https://example.com/leiheng.jpg', '2023-07-20', '2023-07-20 09:00:00', '2023-07-20 09:00:00', 1),
      ('shiwen', 'e10adc3949ba59abbe56e057f20f883e', '石秀', 1, '13800138019', 2, 8400, 'https://example.com/shixiu.jpg', '2023-08-25', '2023-08-25 09:00:00', '2023-08-25 09:00:00', 1),
      ('yanqing', 'e10adc3949ba59abbe56e057f20f883e', '燕青', 1, '13800138020', 2, 9500, 'https://example.com/yanqing.jpg', '2023-09-10', '2023-09-10 09:00:00', '2023-09-10 09:00:00', 1),
      ('sunerniang', 'e10adc3949ba59abbe56e057f20f883e', '孙二娘', 2, '13800138021', 2, 8200, 'https://example.com/sunerniang.jpg', '2023-10-15', '2023-10-15 09:00:00', '2023-10-15 09:00:00', 1),
      ('gusao', 'e10adc3949ba59abbe56e057f20f883e', '顾大嫂', 2, '13800138022', 2, 8100, 'https://example.com/gusao.jpg', '2023-11-05', '2023-11-05 09:00:00', '2023-11-05 09:00:00', 1),
      ('humei', 'e10adc3949ba59abbe56e057f20f883e', '扈三娘', 2, '13800138023', 1, 9300, 'https://example.com/humei.jpg', '2023-12-12', '2023-12-12 09:00:00', '2023-12-12 09:00:00', 1),
      ('songqing', 'e10adc3949ba59abbe56e057f20f883e', '宋清', 1, '13800138024', 2, 7800, 'https://example.com/songqing.jpg', '2024-01-18', '2024-01-18 09:00:00', '2024-01-18 09:00:00', 1),
      ('daizong', 'e10adc3949ba59abbe56e057f20f883e', '戴宗', 1, '13800138025', 2, 9000, 'https://example.com/daizong.jpg', '2024-02-22', '2024-02-22 09:00:00', '2024-02-22 09:00:00', 1);

    create table emp_expr(
         id int unsigned primary key auto_increment comment 'ID，主键',
         begin_date date comment '开始时间',
         end_date date comment '结束时间',
         company varchar(50) comment '公司名称',
         job varchar(50) comment '职位',
         emp_id int unsigned comment '关联的职工id'
    ) comment "工作经历表";

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
