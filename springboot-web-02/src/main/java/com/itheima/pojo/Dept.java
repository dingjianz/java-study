package com.itheima.pojo;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept {
    private Integer id;

    // 部门名称长度限制与数据库 varchar(10) 保持一致，超出时返回带限制值的错误提示
    @Size(max = 10, message = "部门名称长度不能超过 {max} 个字符")
    private String name;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
