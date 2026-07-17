package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpExpr {
    private Integer id; // 主键id
    private Integer empId; // 员工id
    private LocalDate beginDate; // 开始时间
    private LocalDate endDate; // 结束时间
    private String company; // 公司名称
    private String job; // 职位
}
