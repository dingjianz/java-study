import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import { useNavigate } from 'react-router-dom'

export default function AboutPage() {
  const navigate = useNavigate()

  return (
    <div className="container mx-auto p-6 space-y-6">
      <Button variant="ghost" onClick={() => navigate(-1)}>
        ← 返回
      </Button>

      <h1 className="text-4xl font-bold">关于本项目</h1>

      <Card>
        <CardHeader>
          <CardTitle>项目介绍</CardTitle>
          <CardDescription>这是一个现代化的 React 项目模板</CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
          <div>
            <h3 className="font-semibold mb-2">核心特性</h3>
            <ul className="list-disc list-inside space-y-1 text-sm text-muted-foreground">
              <li>使用 Vite 构建，开发体验极速</li>
              <li>TypeScript 提供类型安全</li>
              <li>Tailwind CSS 实现响应式设计</li>
              <li>Zustand 轻量级状态管理</li>
              <li>React Router 路由管理</li>
              <li>shadcn/ui 组件库</li>
            </ul>
          </div>

          <div>
            <h3 className="font-semibold mb-2">目录结构</h3>
            <pre className="bg-muted p-4 rounded-lg text-xs overflow-x-auto">
{`src/
├── components/     # 组件
│   └── ui/        # UI 组件
├── pages/         # 页面
├── stores/        # Zustand 状态管理
├── utils/         # 工具函数
├── lib/           # 库配置
├── hooks/         # 自定义 Hooks
└── styles/        # 样式文件`}
            </pre>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
