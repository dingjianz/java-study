package com.itheima.mySQL;

public class ConstraintDemo {
    /*
        约束：作用于表中字段上的规则，用于限制存储在表中的数据。
        目的：保证数据库的数据安全、一致性、完整性

        非空约束：not null 限制该字段的数据不能为null
        唯一约束：unique 保证该字段的所有数据都是唯一、不重复的
        主键约束：primary key 主键是一行数据的唯一标识，要求非空且唯一
        外键约束：foreign key 用来让两张表的数据之间建立连接，保证数据的一致性和完整性
        默认约束：default 保存数据时，如果未指定该字段的值，则采用默认值
        检查约束：check 保证字段值满足某一个条件


        需求：根据需求，完成表结构的创建
       字段名           字段含义        字段类型         约束条件
        id            ID唯一标识        int          主键并且自动增长
        name             姓名         varchar(10)    不为空，并且唯一
        age              年龄          int          大于0，且小于等于120
        status           状态          char(1)     如果没有指定该值，默认为1
        gender           性别          char(1)            无

        create table employee(
            id int primary key auto_increment comment 'id唯一标识',
            name varchar(10) not null unique comment '姓名',
            age int check(age > 0 and age <= 120) comment '年龄',
            status char(1) default '1' comment '状态',
            gender char(1) comment '性别'
        ) comment '雇员表';

        关键字 auto_increment: 自动增长

       ----------- 外键约束 ------------
       语法：
        1. 添加外键约束
            1.1 建表时直接写在列定义后面
            CREATE TABLE 表名(
                 字段名 数据类型,
                 ...,
                 [CONSTRAINT] [外键名称] FOREIGN KEY (外键字段名) REFERENCES 主表名(字段名)
             );

            1.2 建表后追加
              ALTER TABLE 表名 ADD CONSTRAINT 外键名称 FOREIGN KEY (外键字段名) REFERENCES 主表名(字段名);
              括号一定要加上！！！
              外键名称: 命名没有强制规范，但推荐 fk_从表名_主表名，一眼就能看出关联关系。
             eg:
              CREATE TABLE employe (
                  id      INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                  name    VARCHAR(10) NOT NULL COMMENT '姓名',
                  age     INT COMMENT '年龄',
                  dept_id INT NOT NULL COMMENT '部门ID',
                  CONSTRAINT fk_employe_dept FOREIGN KEY (dept_id) REFERENCES dept(id)
              ) COMMENT '员工表';
             eg: alter table employe add constraint fk_employe_dept foreign key (dept_id) references dept(id);

        2. 删除外键约束
            ALTER TABLE 表名 DROP FOREIGN KEY 外键名称;
            eg: alter table employe drop foreign key fk_employe_dept;

        3.外键约束的 删除/更新行为
           NO ACTION: 默认值，删除/更新主表数据时，如果从表中有外键数据，则不允许删除/更新
           RESTRICT: 删除/更新主表数据时，如果从表中有外键数据，则不允许删除/更新
           CASCADE: 删除/更新主表数据时，如果从表中有外键数据，则一并删除/更新
           SET NULL: 删除主表数据时，如果从表中有外键数据，则将从表中的外键字段设置为null
           SET DEFAULT: 删除/更新主表数据时，如果从表中有外键数据，则将从表中的外键字段设置为默认值(Innodb不支持)

           ALTER TABLE 表名 ADD CONSTRAINT 外键名称 FOREIGN KEY (外键字段) REFERENCES 主表名(主表字段) ON DELETE CASCADE ON UPDATE CASCADE;
     */
}
