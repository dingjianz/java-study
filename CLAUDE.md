# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

这是一个 Java 全栈学习项目，包含多个独立的学习模块和一个完整的部门管理系统（前后端分离）。

**项目结构：**
- `frontend/` - React + TypeScript 前端应用（部门管理系统前端）
- `springboot-web-02/` - Spring Boot 后端 API（部门管理系统后端）
- `HelloWorld/` - Java 基础学习模块
- `maven-study/`, `mybatis-demo/`, `spring-demo/`, `springboot-web-01/` - 其他学习模块

**模块专属指南（进入对应目录工作时会自动加载）：**
- 前端开发 → `frontend/CLAUDE.md`
- 后端开发 → `springboot-web-02/CLAUDE.md`
- Java 基础 → `HelloWorld/CLAUDE.md`

## 全栈开发工作流

### 启动完整应用

**1. 启动数据库（MySQL）** — 确保 MySQL 服务运行，且存在 `web02` 数据库

**2. 启动后端服务**
```bash
cd springboot-web-02
mvn spring-boot:run       # 后端运行在 http://localhost:8080
```

**3. 启动前端开发服务器**
```bash
cd frontend
npm run dev               # 前端运行在 http://localhost:8082
```

### 前后端联调
- 前端通过 `VITE_API_BASE_URL` 环境变量连接后端
- 开发环境：`VITE_API_BASE_URL=/api`，由 vite `server.proxy` 将 `/api` 代理到 `http://localhost:8080`（开发时**无跨域问题**，无需后端 CORS 配置）
- 生产环境需在前端 `.env.production` 中配置实际 API 地址
- 详细的 API 约定、CORS 说明分别见 `frontend/CLAUDE.md` 与 `springboot-web-02/CLAUDE.md`

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

- **前端**：支持 npm 和 pnpm（已配置 `pnpm-workspace.yaml`），推荐使用 pnpm 以提高安装速度
- **后端**：Maven 3.x

## 重要文件

- `frontend/QUICK_START.md` - 前端快速开始指南
- `frontend/PROJECT_SETUP.md` - 前端详细配置说明
- `.gitignore` - 已配置忽略 node_modules, target, .idea 等
