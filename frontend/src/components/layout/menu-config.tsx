import type { ReactNode } from "react"

export interface MenuItem {
  /** 菜单标题 */
  title: string
  /** 路由路径，叶子菜单必填 */
  path?: string
  /** 子菜单 */
  children?: MenuItem[]
  /** 图标 */
  icon?: ReactNode
}

// 简单的内联图标（避免引入额外图标库）
const IconHome = (
  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" className="size-4">
    <path d="M3 10.5 12 3l9 7.5" />
    <path d="M5 9.5V21h14V9.5" />
  </svg>
)
const IconUsers = (
  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" className="size-4">
    <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2" />
    <circle cx="9" cy="7" r="4" />
    <path d="M22 21v-2a4 4 0 0 0-3-3.87" />
  </svg>
)
const IconSettings = (
  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" className="size-4">
    <circle cx="12" cy="12" r="3" />
    <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 1 1-2.83 2.83l-.06-.06a1.65 1.65 0 0 0-2.82 1.17V21a2 2 0 0 1-4 0v-.09A1.65 1.65 0 0 0 8 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 1 1-2.83-2.83l.06-.06A1.65 1.65 0 0 0 4.6 15H4.5a2 2 0 0 1 0-4h.09A1.65 1.65 0 0 0 6 9.4l.33-.06z" />
  </svg>
)
const IconChart = (
  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" className="size-4">
    <path d="M3 3v18h18" />
    <rect x="7" y="10" width="3" height="7" />
    <rect x="13" y="6" width="3" height="11" />
  </svg>
)

// 完整菜单配置。未实现的页面统一指向占位页（/admin/placeholder/...）
export const menuConfig: MenuItem[] = [
  {
    title: "首页",
    path: "/admin/home",
    icon: IconHome,
  },
  {
    title: "班级学员管理",
    icon: IconUsers,
    children: [
      { title: "班级管理", path: "/admin/class" },
      { title: "学员管理", path: "/admin/student" },
    ],
  },
  {
    title: "系统信息管理",
    icon: IconSettings,
    children: [
      { title: "部门管理", path: "/admin/dept" },
      { title: "员工管理", path: "/admin/employee" },
    ],
  },
  {
    title: "数据统计管理",
    icon: IconChart,
    children: [
      { title: "员工信息统计", path: "/admin/stat/employee" },
      { title: "学员信息统计", path: "/admin/stat/student" },
    ],
  },
]

// 已实现的路由集合，其余跳占位页
export const IMPLEMENTED_PATHS = new Set<string>(["/admin/dept", "/admin/employee"])
