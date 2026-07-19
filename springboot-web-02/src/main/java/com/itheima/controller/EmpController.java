package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /*
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      String name,
                      Integer gender,
                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询员工信息，page：{}，pageSize：{}，name：{}，gender：{}，begin：{}，end：{}",
                page, pageSize, name, gender, begin, end);
        return Result.success(empService.page(page, pageSize, name, gender, begin, end));
    }
     */

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询员工信息:{}", empQueryParam);
        return Result.success(empService.page(empQueryParam));
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

    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工信息：{}", emp);
        empService.insertEmp(emp);
        return Result.success();
    }
}
