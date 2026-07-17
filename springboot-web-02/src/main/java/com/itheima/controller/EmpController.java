package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询员工信息，page：{}，pageSize：{}", page, pageSize);
        return Result.success(empService.page(page, pageSize));
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询员工信息，id：{}", id);
        return Result.success(empService.getEmpById(id));
    }

    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工信息：{}", emp);
        emp.setUpdateTime(LocalDateTime.now());
        empService.updateEmp(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id) {
        log.info("删除员工信息，id：{}", id);
        empService.deleteEmpById(id);
        return Result.success();
    }
}
