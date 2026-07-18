package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender; // 性别： 1男/2女
    private String phone;
    private Integer job;
    private Integer salary;
    private LocalDate entryDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deptId;
    private String image;
    // 封装部门名称
    private String deptName;
}
