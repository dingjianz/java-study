package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(Integer pageNum, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
       /*
        分页查询方式一：传统的分页处理
            // 总记录数
            Long total = empMapper.count();
            // 当前页数据（pageNum 从 1 开始，转换为 SQL 偏移量）
            int start = (pageNum - 1) * pageSize;
            List<Emp>  records = empMapper.getAllEmp(start, pageSize);

             return new PageResult<>(total, records);
        */

        Page<Emp> page = new Page<>(pageNum, pageSize);
        // 按更新时间降序；join 查询用别名 e 限定列名，避免歧义
        page.addOrder(OrderItem.desc("e.update_time"));

        // 直接将查询参数传递给 Mapper，由 XML 动态 SQL 处理
        IPage<Emp> empIPage = empMapper.getAllEmp(page, name, gender, begin, end);
        return new PageResult<>(empIPage.getTotal(), empIPage.getCurrent(), empIPage.getRecords());

    }


    @Override
    public Emp getEmpById(Integer id) {
        return empMapper.getEmpById(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
    }

    @Override
    public void deleteEmpById(Integer id) {
        empMapper.deleteEmpById(id);
    }
}
