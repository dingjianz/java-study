package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAllDept() {
        return deptMapper.getAllDept();
    }

    @Override
    public void deleteDeptById(Integer id) {
        deptMapper.deleteDeptById(id);
    }

    @Override
    public void addDept(Dept dept) {
        // createTime、updateTime 由 MyMetaObjectHandler 自动填充
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        // updateById 只更新非 null 字段，createTime 不会被覆盖；
        // updateTime 由 MyMetaObjectHandler 自动填充
        deptMapper.updateById(dept);
    }
}
