package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);

    void deleteEmpById(Integer id);
}
