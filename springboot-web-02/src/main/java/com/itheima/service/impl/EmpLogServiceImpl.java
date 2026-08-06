package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.pojo.EmpLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Override
    public PageResult<EmpLog> getAllEmpLogs(Integer pageNum, Integer pageSize) {
        Page<EmpLog> page = new Page<>(pageNum, pageSize);
        IPage<EmpLog> empLogPage = empLogMapper.getAllEmpLogs(page);
        return new PageResult<>(empLogPage.getTotal(), empLogPage.getCurrent(), empLogPage.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 日志单独开事务，主业务失败了，日志也可以保留下来。
    public void insert(String info) {
        // operateTime 交给 MyMetaObjectHandler 自动填充，这里只组装业务字段
        EmpLog empLog = new EmpLog();
        empLog.setInfo(info);
        empLogMapper.insert(empLog);
    }
}
