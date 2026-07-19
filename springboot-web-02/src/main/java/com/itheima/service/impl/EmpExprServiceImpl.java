package com.itheima.service.impl;

import com.itheima.mapper.EmpExprMapper;
import com.itheima.pojo.EmpExpr;
import com.itheima.service.EmpExprService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpExprServiceImpl implements EmpExprService {
    @Resource
    private EmpExprMapper empExprMapper;

    @Override
    public void insertBatch(List<EmpExpr> exprList) {
        if (exprList != null && !exprList.isEmpty()) {
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public void deleteByEmpId(Integer empId) {
        empExprMapper.deleteByEmpId(empId);
    }
}
