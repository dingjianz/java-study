package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
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
        /*
         * MyBatis-Plus 分页原理（拦截器 + SQL 改写）：
         *
         * 1. new Page<>(当前页, 每页条数) 本身不查数据库，它只是一个「分页参数载体」，
         *    同时也是查询结果的接收容器（实现了 IPage 接口）。
         *
         * 2. 关键在于 MybatisPlusConfig 中注册的 PaginationInnerInterceptor 分页插件。
         *    它拦截 MyBatis 执行 SQL 的过程，发现 Mapper 方法的第一个参数是 IPage 类型时，
         *    自动把 XML 里那条「原始 SQL」改写成两条 SQL 执行：
         *      ① COUNT 语句：select count(*) from (原始SQL)  —— 得到 total 总记录数
         *      ② 分页语句：原始SQL + order by ... limit ?, ?  —— 得到当前页数据
         *    其中 limit 的偏移量由插件自动计算：offset = (page - 1) * pageSize。
         *    所以我们不用像传统写法那样手动算 start，也不用自己写 count 语句。
         *
         * 3. limit 语法与数据库方言相关，因此配置插件时指定了 DbType.MYSQL，
         *    插件据此生成对应方言的分页 SQL（Oracle 用 rownum、PostgreSQL 用 limit offset 等）。
         *
         * 4. 查询完成后，插件把 total / current / records 回填到这个 page 对象里，
         *    所以下面从返回的 IPage 上就能直接取到总数和当前页数据。
         */
        Page<Emp> page = new Page<>(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 排序条件也交给插件拼接（追加到分页 SQL 的 order by 中，不会污染 COUNT 语句）
        // 按更新时间降序；join 查询用别名 e 限定列名，避免歧义
        page.addOrder(OrderItem.desc("e.update_time"));

        // 直接将查询参数传递给 Mapper，由 XML 动态 SQL 处理
        // 注意：page 必须作为第一个参数传入，插件才能识别并改写 SQL；返回的即是被回填后的分页对象
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
        // 更新员工基本信息：updateById 只更新非 null 字段，
        // 所以 password、createTime 不会被误覆盖（与原来手写 SQL 的行为一致）；
        // updateTime 由 MyMetaObjectHandler 自动填充
        empMapper.updateById(emp);

        // 工作经历采用「先删后插」：先删掉该员工的旧工作经历
        empExprService.deleteByEmpId(emp.getId());

        // 再插入新的工作经历（过滤掉空记录后批量插入）
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprService.insertBatch(exprList);
        }
    }

    @Override
    public void deleteEmpById(Integer id) {
        empMapper.deleteEmpById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 事务管理
    /*
    @Transactional: Spring 事务管理的注解，会在方法运行之前，开启事务
    运行完毕之后，根据运行的结果，来提交或回滚事务。
    位置：方法上 类上 接口上

    默认 出现运行时异常 RuntimeException 才会回滚
    rollbackFor = Exception.class 所有的异常都会回滚
     */
    public void insertEmp(Emp emp) {
        // 设置默认密码（前端不传递密码）
        if (emp.getPassword() == null || emp.getPassword().isEmpty()) {
            emp.setPassword("123456");
        }

        // 插入员工基本信息：createTime、updateTime 由 MyMetaObjectHandler 自动填充，
        // 自增主键会回填到 emp.id 上，供下面的工作经历使用
        empMapper.insert(emp);

      Integer empId = emp.getId();
      List<EmpExpr> exprList = emp.getExprList();
      if (!CollectionUtils.isEmpty(exprList)) {
          exprList.forEach(expr -> expr.setEmpId(empId));
          empExprService.insertBatch(exprList);
      }
    }
}
