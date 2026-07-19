package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpExprService;
import com.itheima.service.EmpService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;

    @Autowired
    private EmpExprService empExprService;

    /*@Override
    public PageResult<Emp> page(Integer pageNum, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
       *//*
        分页查询方式一：传统的分页处理
            // 总记录数
            Long total = empMapper.count();
            // 当前页数据（pageNum 从 1 开始，转换为 SQL 偏移量）
            int start = (pageNum - 1) * pageSize;
            List<Emp>  records = empMapper.getAllEmp(start, pageSize);

             return new PageResult<>(total, records);
        *//*

        Page<Emp> page = new Page<>(pageNum, pageSize);
        // 按更新时间降序；join 查询用别名 e 限定列名，避免歧义
        page.addOrder(OrderItem.desc("e.update_time"));

        // 直接将查询参数传递给 Mapper，由 XML 动态 SQL 处理
        IPage<Emp> empIPage = empMapper.getAllEmp(page, name, gender, begin, end);
        return new PageResult<>(empIPage.getTotal(), empIPage.getCurrent(), empIPage.getRecords());
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        Page<Emp> page = new Page<>(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 按更新时间降序；join 查询用别名 e 限定列名，避免歧义
        page.addOrder(OrderItem.desc("e.update_time"));

        // 直接将查询参数传递给 Mapper，由 XML 动态 SQL 处理
        IPage<Emp> empIPage = empMapper.getAllEmp(page, empQueryParam);
        return new PageResult<>(empIPage.getTotal(), empIPage.getCurrent(), empIPage.getRecords());
    }


    @Override
    public Emp getEmpById(Integer id) {
        return empMapper.getEmpById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmp(Emp emp) {
        // 更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);

        // 工作经历采用「先删后插」：先删掉该员工的旧工作经历
        empExprService.deleteByEmpId(emp.getId());

        // 再插入新的工作经历（过滤掉空记录后批量插入）
        List<EmpExpr> validExprList = filterValidExpr(emp.getExprList());
        if (!validExprList.isEmpty()) {
            validExprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprService.insertBatch(validExprList);
        }
    }

    @Override
    public void deleteEmpById(Integer id) {
        empMapper.deleteEmpById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertEmp(Emp emp) {
        // 设置默认密码（前端不传递密码）
        if (emp.getPassword() == null || emp.getPassword().isEmpty()) {
            emp.setPassword("123456");
        }

        // 设置创建时间和更新时间
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        // 插入员工基本信息
        empMapper.insertEmp(emp);

        // 保存工作经历（过滤掉空记录后批量插入）
        List<EmpExpr> validExprList = filterValidExpr(emp.getExprList());
        if (!validExprList.isEmpty()) {
            // 设置每条工作经历的 empId
            validExprList.forEach(expr -> expr.setEmpId(emp.getId()));
            // 批量插入工作经历
            empExprService.insertBatch(validExprList);
        }
    }

    /**
     * 过滤掉空的工作经历对象（所有字段都为空的记录）。
     * 前端表单可能提交空行，这里统一清洗，新增和更新都复用。
     */
    private List<EmpExpr> filterValidExpr(List<EmpExpr> exprList) {
        if (exprList == null || exprList.isEmpty()) {
            return List.of();
        }
        return exprList.stream()
                .filter(expr -> expr.getBeginDate() != null
                        || expr.getEndDate() != null
                        || (expr.getCompany() != null && !expr.getCompany().trim().isEmpty())
                        || (expr.getJob() != null && !expr.getJob().trim().isEmpty()))
                .toList();
    }
}
