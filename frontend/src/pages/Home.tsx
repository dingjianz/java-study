import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import { useAuthStore } from '@/stores/authStore'
import { useUIStore } from '@/stores/uiStore'
import { dayjs, formatDate } from '@/utils/date'
import { Link } from 'react-router-dom'

export default function HomePage() {
  const { user, isAuthenticated } = useAuthStore()
  const { theme, toggleSidebar } = useUIStore()

  return (
    <div className="container mx-auto p-6 space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-4xl font-bold">欢迎来到 React 项目</h1>
        <Button onClick={toggleSidebar}>切换侧边栏</Button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <Card>
          <CardHeader>
            <CardTitle>用户信息</CardTitle>
            <CardDescription>当前登录状态</CardDescription>
          </CardHeader>
          <CardContent>
            {isAuthenticated ? (
              <div className="space-y-2">
                <p className="text-sm">用户名: {user?.name}</p>
                <p className="text-sm">邮箱: {user?.email}</p>
                <Button variant="destructive" size="sm" onClick={() => useAuthStore.getState().logout()}>
                  退出登录
                </Button>
              </div>
            ) : (
              <div>
                <p className="text-sm text-muted-foreground mb-3">未登录</p>
                <Button
                  size="sm"
                  onClick={() =>
                    useAuthStore.getState().login({
                      id: '1',
                      name: '张三',
                      email: 'zhangsan@example.com',
                    })
                  }
                >
                  模拟登录
                </Button>
              </div>
            )}
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>主题设置</CardTitle>
            <CardDescription>当前主题模式</CardDescription>
          </CardHeader>
          <CardContent>
            <p className="text-sm mb-3">当前主题: {theme}</p>
            <div className="flex gap-2">
              <Button size="sm" variant="outline" onClick={() => useUIStore.getState().setTheme('light')}>
                浅色
              </Button>
              <Button size="sm" variant="outline" onClick={() => useUIStore.getState().setTheme('dark')}>
                深色
              </Button>
              <Button size="sm" variant="outline" onClick={() => useUIStore.getState().setTheme('system')}>
                系统
              </Button>
            </div>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>日期时间</CardTitle>
            <CardDescription>使用 dayjs 处理日期</CardDescription>
          </CardHeader>
          <CardContent className="space-y-2">
            <p className="text-sm">当前时间: {formatDate(new Date())}</p>
            <p className="text-sm">ISO 格式: {dayjs().toISOString()}</p>
            <p className="text-sm">相对时间: {dayjs().subtract(2, 'hour').fromNow()}</p>
          </CardContent>
        </Card>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>技术栈</CardTitle>
          <CardDescription>本项目使用的技术</CardDescription>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
            {[
              'React 18',
              'Vite',
              'TypeScript',
              'Tailwind CSS',
              'Sass',
              'React Router',
              'Zustand',
              'dayjs',
              'clsx',
              'shadcn/ui',
            ].map((tech) => (
              <div
                key={tech}
                className="p-3 rounded-lg border bg-card text-center hover:bg-accent transition-colors"
              >
                {tech}
              </div>
            ))}
          </div>
        </CardContent>
      </Card>

      <div className="flex gap-4">
        <Link to="/about">
          <Button variant="outline">关于页面</Button>
        </Link>
        <Link to="/dashboard">
          <Button variant="outline">仪表盘</Button>
        </Link>
      </div>
    </div>
  )
}
