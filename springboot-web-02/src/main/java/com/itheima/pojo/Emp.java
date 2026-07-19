package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    private Integer id; // 主键id
    private String username;
    private String password;
    private String name;
    private Integer gender; // 性别： 1男/2女
    private String phone;
    private Integer job;
    private Integer salary;
    private LocalDate entryDate;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer deptId;
    private String image;

    // 封装部门名称（非 emp 表字段，来自 join dept 表）
    @TableField(exist = false)
    private String deptName;

    // 封装工作经历
    @TableField(exist = false)
    private List<EmpExpr> exprList;
}
