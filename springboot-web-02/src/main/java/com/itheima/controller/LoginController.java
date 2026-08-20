package com.itheima.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    /*
        登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录：{}", emp);
        LoginInfo loginInfo = empService.login(emp);
        return Result.success(loginInfo);
    }

    /*
        退出登录：Sa-Token 的会话保存在服务端，注销后旧 token 立即失效
     */
    @PostMapping("/logout")
    public Result logout(){
        log.info("退出登录：{}", StpUtil.getLoginIdDefaultNull());
        StpUtil.logout();
        return Result.success();
    }
}