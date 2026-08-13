package com.itheima.controller;

import com.itheima.pojo.EmpLog;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpLogService;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/empLogs")
@RestController
public class EmpLogController {
    /*
    create table emp_log(
        id int unsigned primary key auto_increment comment "ID，主键",
        operate_time datetime comment "操作时间",
        info varchar(1000) comment "操作信息"
    ) comment "员工日志表";
     */

    @Autowired
    private EmpLogService empLogService;

    /*
    获取员工日志
     */
    @GetMapping
    public PageResult<EmpLog> getAllEmpLogs(Integer pageNum, Integer pageSize) {
        return empLogService.getAllEmpLogs(pageNum, pageSize);
    }

    /**
     * 添加员工日志
     */
    @PostMapping
    public Result insertEmpLog(String info) {
        empLogService.insert(info);
        return Result.success();
    }
}
