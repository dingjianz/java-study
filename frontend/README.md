# React + Vite + TypeScript + Tailwind CSS 项目

这是一个现代化的 React 项目模板，集成了最流行的开发工具和库。

## 技术栈

- **React 18** - 用户界面库
- **Vite** - 快速的构建工具
- **TypeScript** - 类型安全
- **Tailwind CSS** - 实用优先的 CSS 框架
- **Sass** - CSS 预处理器
- **React Router Dom** - 路由管理
- **Zustand** - 轻量级状态管理
- **dayjs** - 日期处理库
- **clsx** - 条件类名工具
- **shadcn/ui** - 高质量 UI 组件库

## 项目结构

```
frontend/
├── src/
│   ├── components/       # 可复用组件
│   │   └── ui/          # shadcn/ui 组件
│   ├── pages/           # 页面组件
│   ├── stores/          # Zustand 状态管理
│   ├── hooks/           # 自定义 React Hooks
│   ├── utils/           # 工具函数
│   ├── lib/             # 库配置
│   ├── styles/          # Sass 样式文件
│   ├── App.tsx          # 主应用组件
│   ├── main.tsx         # 应用入口
│   └── index.css        # 全局样式
├── public/              # 静态资源
├── tailwind.config.js   # Tailwind 配置
├── vite.config.ts       # Vite 配置
└── package.json         # 项目依赖
```

## 开始使用

### 安装依赖

```bash
npm install
```

### 开发模式

```bash
npm run dev
```

应用将在 [http://localhost:8082](http://localhost:8082) 启动

### 构建生产版本

```bash
npm run build
```

### 预览生产构建

```bash
npm run preview
```

## 核心功能

### 状态管理

使用 Zustand 进行状态管理，示例：

```typescript
import { useAuthStore } from '@/stores/authStore'

function MyComponent() {
  const { user, login, logout } = useAuthStore()
  // ...
}
```

### 路由

使用 React Router Dom 进行路由管理：

- `/` - 首页
- `/about` - 关于页面
- `/dashboard` - 仪表盘（需要登录）

### 自定义 Hooks

- `useLocalStorage` - 持久化状态到 localStorage
- `useDebounce` - 防抖处理
- `useMediaQuery` - 响应式媒体查询

### UI 组件

基于 shadcn/ui 的组件系统：

- Button - 按钮组件
- Card - 卡片组件
- 更多组件可按需添加

### 工具函数

- `cn()` - 合并和条件化类名（基于 clsx 和 tailwind-merge）
- `formatDate()` - 日期格式化
- `formatCurrency()` - 货币格式化
- `debounce()` - 防抖函数

## Tailwind CSS

项目配置了 Tailwind CSS 和自定义主题变量。支持深色模式（通过 `dark` 类）。

### 自定义颜色

所有颜色都使用 CSS 变量定义，可在 `src/index.css` 中修改。

## Sass

项目支持 Sass，可以使用 `.scss` 文件。示例文件位于 `src/styles/` 目录。

## 路径别名

配置了 `@` 别名指向 `src` 目录：

```typescript
import { Button } from '@/components/ui/button'
import { useAuthStore } from '@/stores/authStore'
```

## 环境变量

创建 `.env` 文件来添加环境变量：

```env
VITE_API_URL=http://localhost:3000
VITE_APP_NAME=My App
```

在代码中使用：

```typescript
const apiUrl = import.meta.env.VITE_API_URL
```

## 添加更多 shadcn/ui 组件

虽然项目没有配置完整的 shadcn CLI，但你可以手动添加组件：

1. 访问 [shadcn/ui](https://ui.shadcn.com/)
2. 复制组件代码到 `src/components/ui/`
3. 根据需要调整导入路径

## 最佳实践

- 使用 TypeScript 类型定义
- 组件使用函数式编程
- 保持组件小而专注
- 使用自定义 Hooks 复用逻辑
- 使用 Zustand 管理全局状态
- 本地状态使用 useState
- 使用 Tailwind 实用类进行样式设计

## 许可证

MIT
