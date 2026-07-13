import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import { useNavigate } from 'react-router-dom'
import { useAuthStore } from '@/stores/authStore'
import { formatCurrency } from '@/utils/helpers'

export default function DashboardPage() {
  const navigate = useNavigate()
  const { isAuthenticated, user } = useAuthStore()

  if (!isAuthenticated) {
    return (
      <div className="container mx-auto p-6 flex items-center justify-center min-h-[60vh]">
        <Card className="w-full max-w-md">
          <CardHeader>
            <CardTitle>需要登录</CardTitle>
            <CardDescription>请先登录后访问仪表盘</CardDescription>
          </CardHeader>
          <CardContent>
            <Button onClick={() => navigate('/')}>返回首页</Button>
          </CardContent>
        </Card>
      </div>
    )
  }

  return (
    <div className="container mx-auto p-6 space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-4xl font-bold">仪表盘</h1>
        <Button variant="ghost" onClick={() => navigate('/')}>
          返回首页
        </Button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <Card>
          <CardHeader>
            <CardDescription>总收入</CardDescription>
            <CardTitle className="text-3xl">{formatCurrency(125680)}</CardTitle>
          </CardHeader>
          <CardContent>
            <p className="text-xs text-muted-foreground">+12.5% 较上月</p>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardDescription>活跃用户</CardDescription>
            <CardTitle className="text-3xl">3,456</CardTitle>
          </CardHeader>
          <CardContent>
            <p className="text-xs text-muted-foreground">+8.2% 较上月</p>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardDescription>转化率</CardDescription>
            <CardTitle className="text-3xl">23.8%</CardTitle>
          </CardHeader>
          <CardContent>
            <p className="text-xs text-muted-foreground">+2.1% 较上月</p>
          </CardContent>
        </Card>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>欢迎回来, {user?.name}</CardTitle>
          <CardDescription>这是你的个人仪表盘</CardDescription>
        </CardHeader>
        <CardContent>
          <p className="text-sm text-muted-foreground">
            这里可以展示用户的数据、图表和其他个性化内容。
          </p>
        </CardContent>
      </Card>
    </div>
  )
}
