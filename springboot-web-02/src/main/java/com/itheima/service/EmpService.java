package com.itheima.service;

import com.itheima.pojo.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> getAllEmp();

    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);

    void deleteEmpById(Integer id);
}
