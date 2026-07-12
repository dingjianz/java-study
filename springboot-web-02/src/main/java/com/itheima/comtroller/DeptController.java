package com.itheima.comtroller;

import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Resource
    private DeptService deptService;

    @GetMapping("/depts")
    public Result list(){
        return deptService.getAllDept();
    }
}
