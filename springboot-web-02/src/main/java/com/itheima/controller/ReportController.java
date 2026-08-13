package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 性别统计
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        List<Map<String, Object>> genderList = reportService.getGenderData();
        System.out.println(genderList);
        return Result.success(genderList);
    }
}