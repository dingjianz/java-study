package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);

    void deleteEmpById(Integer id);

    void insertEmp(Emp emp);

    void deleteBatch(List<Integer> ids);

    /**
     * 员工登录
     */
    LoginInfo login(Emp emp);
}
