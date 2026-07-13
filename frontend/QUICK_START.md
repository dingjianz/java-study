# 🚀 快速开始指南

## ✅ 项目已完全配置完成

你的 React + Vite + Tailwind CSS 项目已经准备就绪！

## 📦 已安装的核心技术

- **React 18** - 最新的 React 版本
- **Vite** - 超快的开发服务器
- **TypeScript** - 类型安全
- **Tailwind CSS v3** - 实用优先的 CSS 框架
- **Sass** - CSS 预处理器
- **React Router Dom** - 路由管理
- **Zustand** - 轻量级状态管理
- **dayjs** - 现代化日期处理
- **clsx** - 条件类名工具
- **shadcn/ui** - 高质量 UI 组件

## 🎯 立即开始

### 1. 启动开发服务器

```bash
npm run dev
```

浏览器访问: **http://localhost:8082** (如果端口被占用会自动使用 8083)

### 2. 探索示例页面

- **首页 (/)** - 展示所有技术栈和功能演示
- **关于页面 (/about)** - 项目介绍和目录结构
- **仪表盘 (/dashboard)** - 需要登录的保护路由

### 3. 体验功能

在首页你可以：
- 点击"模拟登录"体验认证状态管理
- 切换主题模式（浅色/深色/系统）
- 查看 dayjs 的日期处理功能
- 查看所有已安装的技术栈

## 📂 关键文件位置

```
src/
├── pages/              # 在这里添加新页面
├── components/ui/      # UI 组件库
├── stores/            # Zustand 状态管理
├── hooks/             # 自定义 React Hooks
├── utils/             # 工具函数
└── styles/            # Sass 样式文件
```

## 🛠️ 常用命令

```bash
# 开发模式
npm run dev

# 构建生产版本
npm run build

# 预览生产构建
npm run preview

# 代码检查
npm run lint
```

## 💡 快速示例

### 使用 Zustand 状态管理

```typescript
import { useAuthStore } from '@/stores/authStore'

function MyComponent() {
  const { user, login, logout, isAuthenticated } = useAuthStore()
  
  return (
    <div>
      {isAuthenticated ? (
        <p>欢迎, {user?.name}</p>
      ) : (
        <button onClick={() => login({ id: '1', name: '用户', email: 'user@example.com' })}>
          登录
        </button>
      )}
    </div>
  )
}
```

### 使用自定义 Hooks

```typescript
import { useLocalStorage } from '@/hooks/useLocalStorage'
import { useDebounce } from '@/hooks/useDebounce'
import { useIsMobile } from '@/hooks/useMediaQuery'

function MyComponent() {
  const [value, setValue] = useLocalStorage('key', 'default')
  const debouncedSearch = useDebounce((term) => console.log(term), 300)
  const isMobile = useIsMobile()
  
  return <div>{isMobile ? '移动端' : '桌面端'}</div>
}
```

### 使用 UI 组件

```typescript
import { Button } from '@/components/ui/button'
import { Card, CardHeader, CardTitle, CardContent } from '@/components/ui/card'

function MyComponent() {
  return (
    <Card>
      <CardHeader>
        <CardTitle>卡片标题</CardTitle>
      </CardHeader>
      <CardContent>
        <Button variant="default">点击我</Button>
      </CardContent>
    </Card>
  )
}
```

## 🎨 样式方案

### Tailwind CSS (推荐)

```typescript
<div className="flex items-center gap-4 p-6 bg-white rounded-lg shadow-md">
  <Button className="bg-blue-500 hover:bg-blue-600">按钮</Button>
</div>
```

### Sass

```typescript
import './MyComponent.scss'

function MyComponent() {
  return <div className="my-component">内容</div>
}
```

## 📚 更多资源

- [Vite 文档](https://vitejs.dev/)
- [React 文档](https://react.dev/)
- [Tailwind CSS 文档](https://tailwindcss.com/)
- [shadcn/ui 组件](https://ui.shadcn.com/)
- [Zustand 文档](https://github.com/pmndrs/zustand)
- [React Router 文档](https://reactrouter.com/)

## 🐛 遇到问题？

查看 `PROJECT_SETUP.md` 了解详细配置信息和已知问题。

---

**开始编码吧！** 🎉
