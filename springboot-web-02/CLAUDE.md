# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

# 后端开发 (springboot-web-02/)

部门管理系统后端 API（独立 Spring Boot 项目，不在父 POM 的 modules 中）。全局项目说明见根目录 `../CLAUDE.md`。

## 技术栈
- **Spring Boot 3.5.16** (Java 17)
- **MyBatis 3.0.5** - 持久层框架
- **MySQL** - 数据库
- **Lombok** - 简化实体类

## 常用命令

```bash
# 在 springboot-web-02/ 目录

# 编译项目
mvn compile

# 运行 Spring Boot 应用（http://localhost:8080）
mvn spring-boot:run

# 打包（创建可执行 JAR）
mvn package

# 运行打包的应用
java -jar target/springboot-web-02-0.0.1-SNAPSHOT.jar

# 清理构建
mvn clean
```

## 后端架构

**标准三层架构：**
```
springboot-web-02/src/main/java/com/itheima/
├── controller/         # 控制层（接收 HTTP 请求）
│   └── DeptController.java
├── service/           # 服务层（业务逻辑）
│   ├── DeptService.java
│   └── impl/
│       └── DeptServiceImpl.java
├── mapper/            # 数据访问层（MyBatis）
│   └── DeptMapper.java
├── pojo/              # 实体类
│   ├── Dept.java      # 部门实体（含 @Valid 校验注解，如 @NotNull/@Size）
│   └── Result.java    # 统一响应对象
├── exception/         # 全局异常处理
│   └── GlobalExceptionHandler.java
├── config/            # 配置类
│   └── AppLogFilter.java
└── SpringbootWeb02Application.java  # 启动类
```

**全局异常处理（`GlobalExceptionHandler`，`@RestControllerAdvice`）：**
- 捕获 `@Valid` 校验失败（`MethodArgumentNotValidException`），提取字段的 `@Size(max=...)` 等注解提示信息返回，长度限制不写死
- 兜底处理 `DataIntegrityViolationException`（如"Data too long"）和其他 `Exception`，统一转成 `Result.error(...)`，避免暴露底层堆栈
- 新增/修改的写库接口应依赖此机制返回友好错误，而非在 Controller 内 try-catch

## 数据库配置

**连接信息（application.yaml）：**
- 数据库：`web02`
- 地址：`localhost:3306`
- 用户名：`root`
- 密码：`123456`

**MyBatis 配置：**
- 驼峰命名转换：已启用（`map-underscore-to-camel-case: true`）
- SQL 日志：控制台输出（`StdOutImpl`）
- Mapper XML 位置：`classpath:mapper/*.xml`
- 后端默认端口 8080（`application.yaml` 未显式配置 `server.port`）

**日志系统（Logback）：**
- 配置文件：`src/main/resources/logback-spring.xml`
- 日志文件输出到 `springboot-web-02/logs/`（`${APP_NAME}.log` + `${APP_NAME}-error.log`），按天+大小滚动
- 业务代码使用 Lombok 的 `@Slf4j` 注解 + `log.info(...)`（Controller 中已广泛使用）
- 测试类 `LogTest` 演示日志用法

## RESTful API 设计

**统一响应格式：**
```java
Result.success()      // 成功无数据
Result.success(data)  // 成功带数据
Result.error(msg)     // 失败带消息
```

**部门管理 API（Controller 类级 `@RequestMapping("/depts")`，方法级只写子路径或不写）：**
- `GET /depts` - 查询所有部门
- `GET /depts/{id}` - 按 id 查询单个部门（`@PathVariable`）
- `POST /depts` - 新增部门（`@RequestBody @Valid Dept`）
- `PUT /depts` - 更新部门（`@RequestBody @Valid Dept`）
- `DELETE /depts?id={id}` - 删除部门（`@RequestParam`，**不是路径参数**）

> DeptController 内保留了大量注释说明各种参数接收方式（HttpServletRequest / @RequestParam / @PathVariable / @RequestBody）作为学习笔记，编辑时注意区分注释与实际生效代码。

## CORS 配置

当前后端**未配置 CORS**，开发环境依赖前端 vite 代理规避跨域。若脱离 vite 代理直连后端（或生产环境同源部署之外的场景）出现跨域问题，可添加 CORS 配置：
```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:8082")
                    .allowedMethods("*");
            }
        };
    }
}
```

## 配置安全
敏感信息（如数据库密码）应通过环境变量或外部配置文件管理，不要提交到版本控制。
