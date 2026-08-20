import { useState } from "react"
import { Outlet, useNavigate } from "react-router-dom"
import { toast } from "sonner"
import Sidebar from "./Sidebar"
import { authApi } from "@/api/auth"
import { useAuthStore } from "@/stores/authStore"
import { clearToken } from "@/utils/token"
import { Button } from "@/components/ui/button"

export default function AdminLayout() {
  const navigate = useNavigate()
  const { user, logout } = useAuthStore()
  const [loggingOut, setLoggingOut] = useState(false)

  const handleLogout = async () => {
    if (loggingOut) return
    setLoggingOut(true)
    try {
      // 通知后端退出登录（失败也要清理本地状态）
      await authApi.logout()
    } catch {
      // 错误提示已由 http 拦截器统一处理
    } finally {
      // 清除 token
      clearToken()
      // 清除认证状态
      logout()
      // 提示用户
      toast.success("已退出登录")
      // 跳转到登录页
      navigate("/login")
      setLoggingOut(false)
    }
  }

  return (
    <div className="flex h-screen bg-gray-100">
      <Sidebar />

      <div className="flex flex-1 flex-col overflow-hidden">
        {/* 顶栏 */}
        <header className="flex h-14 shrink-0 items-center justify-between border-b border-gray-200 bg-white px-6">
          <div className="text-sm text-gray-500">后台管理系统</div>
          <div className="flex items-center gap-3">
            <span className="text-sm text-gray-600">{user?.name || "管理员"}</span>
            <div className="flex size-8 items-center justify-center rounded-full bg-gray-200 text-sm font-medium text-gray-600">
              {user?.name?.[0] || "管"}
            </div>
            <Button
              variant="ghost"
              size="sm"
              onClick={handleLogout}
              disabled={loggingOut}
              className="ml-2 text-gray-600 hover:text-gray-900"
            >
              {loggingOut ? "退出中..." : "退出登录"}
            </Button>
          </div>
        </header>

        {/* 内容区 */}
        <main className="flex-1 overflow-auto p-6">
          <Outlet />
        </main>
      </div>
    </div>
  )
}
