package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public Result getAllDept() {
        return Result.success(deptMapper.getAllDept());
    }
}
