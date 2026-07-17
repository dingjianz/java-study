import { Outlet } from "react-router-dom"
import Sidebar from "./Sidebar"

export default function AdminLayout() {
  return (
    <div className="flex h-screen bg-gray-100">
      <Sidebar />

      <div className="flex flex-1 flex-col overflow-hidden">
        {/* 顶栏 */}
        <header className="flex h-14 shrink-0 items-center justify-between border-b border-gray-200 bg-white px-6">
          <div className="text-sm text-gray-500">后台管理系统</div>
          <div className="flex items-center gap-3">
            <span className="text-sm text-gray-600">管理员</span>
            <div className="flex size-8 items-center justify-center rounded-full bg-gray-200 text-sm font-medium text-gray-600">
              管
            </div>
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
