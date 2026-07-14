# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

这是一个 Java 全栈学习项目，包含多个独立的学习模块和一个完整的部门管理系统（前后端分离）。

**项目结构：**
- `frontend/` - React + TypeScript 前端应用（部门管理系统前端）
- `springboot-web-02/` - Spring Boot 后端 API（部门管理系统后端）
- `HelloWorld/` - Java 基础学习模块（详见 HelloWorld/CLAUDE.md）
- `maven-study/`, `mybatis-demo/`, `spring-demo/`, `springboot-web-01/` - 其他学习模块

## 前端开发 (frontend/)

### 技术栈
- **React 19** + **Vite 8** + **TypeScript 6**
- **Tailwind CSS 3** - 实用优先的样式框架
- **Zustand** - 轻量级状态管理
- **React Router Dom 7** - 路由管理
- **Axios** - HTTP 客户端
- **dayjs** - 日期处理
- **shadcn/ui** - UI 组件库

### 常用命令

```bash
cd frontend

# 开发模式（默认端口 8082）
npm run dev

# 生产构建（构建前会进行类型检查）
npm run build:check

# 快速构建（跳过类型检查）
npm run build

# 代码检查
npm run lint

# 预览生产构建
npm run preview
```

### 前端架构

```
frontend/src/
├── pages/              # 页面组件（DeptManage, Dashboard, About）
├── components/
│   ├── ui/            # shadcn/ui 基础组件（button, card, modal）
│   └── dept/          # 业务组件（DeptModal）
├── api/               # API 请求封装（dept.ts）
├── stores/            # Zustand 状态管理（authStore, uiStore）
├── hooks/             # 自定义 Hooks（useLocalStorage, useDebounce, useMediaQuery）
├── utils/             # 工具函数
│   ├── http.ts        # Axios 实例配置
│   ├── helpers.ts     # 通用工具函数
│   └── date.ts        # 日期处理
├── types/             # TypeScript 类型定义（dept.ts）
└── lib/               # 库配置（utils.ts - cn 工具）
```

### 关键约定

**API 请求：**
- 所有 API 请求通过 `utils/http.ts` 的 Axios 实例
- 环境变量：`VITE_API_URL`（开发环境默认 `http://localhost:8080`）
- 统一响应格式：`{ code: number, msg: string, data: T }`

**路径别名：**
- `@/*` 映射到 `src/*`（在 vite.config.ts 和 tsconfig.paths.json 中配置）

**状态管理：**
- 全局状态使用 Zustand（认证、UI 主题等）
- 本地状态使用 React useState
- 持久化状态使用 `useLocalStorage` Hook

**样式方案：**
- 优先使用 Tailwind CSS 实用类
- 支持 Sass/SCSS（样式文件在 `src/styles/`）
- 使用 `cn()` 工具（来自 `lib/utils.ts`）合并条件类名

## 后端开发 (springboot-web-02/)

### 技术栈
- **Spring Boot 3.5.16** (Java 17)
- **MyBatis 3.0.5** - 持久层框架
- **MySQL** - 数据库
- **Lombok** - 简化实体类

### 常用命令

```bash
# 在项目根目录或 springboot-web-02/ 目录

# 编译项目
mvn compile

# 运行 Spring Boot 应用
mvn spring-boot:run

# 打包（创建可执行 JAR）
mvn package

# 运行打包的应用
java -jar target/springboot-web-02-0.0.1-SNAPSHOT.jar

# 清理构建
mvn clean
```

### 后端架构

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
│   ├── Dept.java      # 部门实体
│   └── Result.java    # 统一响应对象
└── SpringbootWeb02Application.java  # 启动类
```

### 数据库配置

**连接信息（application.yaml）：**
- 数据库：`web02`
- 地址：`localhost:3306`
- 用户名：`root`
- 密码：`123456`

**MyBatis 配置：**
- 驼峰命名转换：已启用（`map-underscore-to-camel-case: true`）
- SQL 日志：控制台输出（`StdOutImpl`）
- Mapper XML 位置：`classpath:mapper/*.xml`

### RESTful API 设计

**统一响应格式：**
```java
Result.success()      // 成功无数据
Result.success(data)  // 成功带数据
Result.error(msg)     // 失败带消息
```

**部门管理 API：**
- `GET /depts` - 查询所有部门
- `POST /depts` - 新增部门
- `PUT /depts` - 更新部门
- `DELETE /depts/{id}` - 删除部门

## 全栈开发工作流

### 启动完整应用

**1. 启动数据库（MySQL）**
```bash
# 确保 MySQL 服务运行，且存在 web02 数据库
mysql -u root -p
```

**2. 启动后端服务**
```bash
cd springboot-web-02
mvn spring-boot:run
# 后端运行在 http://localhost:8080
```

**3. 启动前端开发服务器**
```bash
cd frontend
npm run dev
# 前端运行在 http://localhost:8082
```

### 前后端联调

- 前端通过 `VITE_API_URL` 环境变量连接后端
- 开发环境默认：`http://localhost:8080`
- 生产环境需在 `.env.production` 中配置实际 API 地址

### CORS 配置

如果遇到跨域问题，需要在 Spring Boot 后端添加 CORS 配置：
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

## Java 版本要求

- **HelloWorld 模块**：Java 25（使用了 `List.addFirst()` 等 Java 21+ 特性）
- **Spring Boot 模块**：Java 17+（Spring Boot 3.x 要求）
- **父 POM**：配置为 Java 25
- 如遇到兼容性问题，可在子模块 pom.xml 中单独指定 Java 版本

## Maven 多模块管理

父 POM 管理以下模块：
- `HelloWorld` - Java 基础学习
- `maven-study` - Maven 学习
- `spring-demo` - Spring 框架基础
- `mybatis-demo` - MyBatis 学习

**注意：** `springboot-web-01` 和 `springboot-web-02` 是独立的 Spring Boot 项目，不在父 POM 的 modules 中。

## 包管理工具

- **前端**：支持 npm 和 pnpm（已配置 `pnpm-workspace.yaml`）
- **后端**：Maven 3.x
- **推荐**：前端使用 pnpm 以提高安装速度

## 环境变量配置

### 前端环境变量
```bash
# .env.development（开发环境）
VITE_API_URL=http://localhost:8080

# .env.production（生产环境）
VITE_API_URL=https://api.production.com
```

### 后端配置
敏感信息（如数据库密码）应通过环境变量或外部配置文件管理，不要提交到版本控制。

## 重要文件

- `frontend/QUICK_START.md` - 前端快速开始指南
- `frontend/PROJECT_SETUP.md` - 前端详细配置说明
- `HelloWorld/CLAUDE.md` - Java 基础学习模块指南
- `.gitignore` - 已配置忽略 node_modules, target, .idea 等
