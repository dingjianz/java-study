package com.itheima.service;

import com.itheima.pojo.EmpExpr;

import java.util.List;

public interface EmpExprService {
    /**
     * 批量插入员工工作经历
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工 id 删除其全部工作经历
     */
    void deleteByEmpId(Integer empId);

    /*
     根据员工id 批量删除全部的工作经历
     */
    void deleteByEmpIds(List<Integer> empIds);
}
