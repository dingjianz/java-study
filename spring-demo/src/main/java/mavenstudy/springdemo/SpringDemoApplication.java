package mavenstudy.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类/引导类
 */
@SpringBootApplication
public class SpringDemoApplication {
/*
 起步依赖：
    spring-boot-starter-web：SpringBoot默认启动依赖 包含了web应用开发所需要的常见依赖
    spring-boot-starter-test：包含了单元测试所需要的常见依赖

 http协议：超文本传输协议
 请求方式 GET:请求参数在请求行中，没有请求体，如/brand?id=1&name=OPPO
 */
    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
