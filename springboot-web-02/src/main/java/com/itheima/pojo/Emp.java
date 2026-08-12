package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emp {
    @TableId(type = IdType.AUTO) // 主键由数据库自增生成，不用 MP 默认的雪花 ID
    private Integer id; // 主键id
    private String username;
    private String password;
    private String name;
    private Integer gender; // 性别： 1男/2女

    // phone 在库里是 NOT NULL UNIQUE，不能被清空，
    // 所以沿用 updateById 默认策略：为 null 时不参与更新
    private String phone;
    private Integer job;
    private Integer salary;
    private LocalDate entryDate;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer deptId;

    // image 是可空列，允许把头像清空，故传 null 时也写进库
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private String image;

    // 封装部门名称（非 emp 表字段，来自 join dept 表）
    @TableField(exist = false)
    private String deptName;

    // 封装工作经历
    @TableField(exist = false)
    private List<EmpExpr> exprList;
}
