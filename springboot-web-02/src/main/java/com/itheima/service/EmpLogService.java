package com.itheima.service;

import com.itheima.pojo.EmpLog;
import com.itheima.pojo.PageResult;

public interface EmpLogService {

    PageResult<EmpLog> getAllEmpLogs(Integer pageNum, Integer pageSize);

    void insert(String info);
}
