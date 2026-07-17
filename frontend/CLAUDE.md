# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

# 前端开发 (frontend/)

部门管理系统前端。全局项目说明见根目录 `../CLAUDE.md`。

## 技术栈
- **React 19** + **Vite 8** + **TypeScript 6**
- **Tailwind CSS 3** - 实用优先的样式框架
- **Zustand** - 轻量级状态管理
- **React Router Dom 7** - 路由管理
- **Axios** - HTTP 客户端
- **sonner** - Toast 通知（全局错误提示）
- **dayjs** - 日期处理
- **crypto-js** - 加密工具
- **UI 组件**：shadcn/ui 风格的自建组件（`components/ui/`），依赖 `class-variance-authority` + `tailwind-merge` + `clsx`（未使用完整 shadcn CLI）
- **代码检查**：oxlint（非 ESLint）

## 常用命令

```bash
cd frontend

# 开发模式（默认端口 8082）
npm run dev

# 生产构建（构建前会进行类型检查）
npm run build:check

# 快速构建（跳过类型检查）
npm run build

# 代码检查（使用 oxlint）
npm run lint

# 预览生产构建
npm run preview
```

> 注意：项目未配置测试框架（无 vitest/jest）。`build:check` 中的 `tsc -b` 是类型层面的主要校验手段。

## 前端架构

```
frontend/src/
├── App.tsx             # 路由入口（BrowserRouter + 全局 Toaster）
├── pages/              # 页面组件（DeptManage, EmployeeManage, Placeholder, About, CakeGrowthTest）
├── components/
│   ├── ui/            # 基础组件（button, card, input, modal, pagination）
│   ├── layout/        # 后台布局（AdminLayout, Sidebar, menu-config）
│   └── dept/          # 业务组件（EmployeeFormModal）
├── api/               # API 请求封装（dept.ts, cakegrowth.ts）
├── stores/            # Zustand 状态管理（authStore, uiStore）
├── hooks/             # 自定义 Hooks（useLocalStorage, useDebounce, useMediaQuery）
├── utils/             # 工具函数（http.ts - Axios 实例）
├── types/             # TypeScript 类型定义（dept.ts, employee.ts, cakegrowth.d.ts）
└── lib/               # 库配置（utils.ts - cn 工具）
```

**路由结构（App.tsx）：**
- `/` → 重定向到 `/admin/dept`
- `/admin` → `AdminLayout`（带侧边栏的后台布局，子路由渲染在 `<Outlet>`）
  - `dept` → 部门管理（已实现）
  - `employee` → 员工管理（已实现，见下方前后端对齐说明）
  - `home` / `class` / `student` / `stat/*` → 统一渲染 `Placeholder`（未实现占位）
- `/about`、`/cakegrowth` → 独立页面（不在后台布局内）

## 关键约定

**API 请求（`utils/http.ts`）：**
- 所有 API 请求通过 `utils/http.ts` 导出的 `request` 对象（封装了 get/post/put/delete/patch，直接返回 `response.data`）
- 环境变量：`VITE_API_BASE_URL`
  - 开发环境（`.env.development`）：`/api`，经 vite `server.proxy` 代理到 `http://localhost:8080`
  - 生产环境（`.env.production`）：配置真实 API 地址
  - 未配置时回退到 `http://localhost:8080`
- 统一响应格式：`{ code: number, msg: string, data: T }`
- **成功判断以 `code === 1` 为准**（非 HTTP 状态码）；响应拦截器对 `code !== 1` 和 HTTP 错误统一通过 `sonner` 的 `toast.error` 弹出提示并 reject，业务代码通常无需再手动处理错误提示

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

## 环境变量配置

```bash
# .env.development（开发环境）
VITE_API_BASE_URL=/api

# .env.production（生产环境）
VITE_API_BASE_URL=https://your-production-api.com
```

## 前后端对齐注意事项
- 前端 `api/dept.ts` 调用了 `GET /depts/page`（分页），但**后端当前未实现该端点**——涉及部门分页时需先在后端补齐
- 前端已有 `EmployeeManage` 页面和 `types/employee.ts`，但**后端暂无员工（Employee）相关 Controller/Service**，员工管理为纯前端占位/待接后端

## 相关文档
- `QUICK_START.md` - 前端快速开始指南
- `PROJECT_SETUP.md` - 前端详细配置说明
