package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> getAllDept();

    void deleteDeptById(Integer id);

    void addDept(Dept dept);

    Dept getDeptById(Integer id);

    void updateDept(Dept dept);
}
