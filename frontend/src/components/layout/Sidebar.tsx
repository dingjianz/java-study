import { useState } from "react"
import { NavLink, useLocation } from "react-router-dom"
import { cn } from "@/lib/utils"
import { menuConfig, type MenuItem } from "./menu-config"

/** 判断某个分组下是否有当前激活的子菜单 */
function groupHasActiveChild(item: MenuItem, pathname: string): boolean {
  return !!item.children?.some((c) => c.path && pathname.startsWith(c.path))
}

export default function Sidebar() {
  const location = useLocation()

  // 记录展开的分组标题；默认展开当前激活分组
  const [openGroups, setOpenGroups] = useState<Set<string>>(() => {
    const set = new Set<string>()
    menuConfig.forEach((item) => {
      if (item.children && groupHasActiveChild(item, location.pathname)) {
        set.add(item.title)
      }
    })
    return set
  })

  const toggleGroup = (title: string) => {
    setOpenGroups((prev) => {
      const next = new Set(prev)
      if (next.has(title)) {
        next.delete(title)
      } else {
        next.add(title)
      }
      return next
    })
  }

  return (
    <aside className="flex w-56 shrink-0 flex-col border-r border-gray-200 bg-white">
      {/* Logo 区域 */}
      <div className="flex h-14 items-center gap-2 border-b border-gray-200 px-4">
        <div className="flex size-8 items-center justify-center rounded-md bg-blue-600 text-sm font-bold text-white">
          智
        </div>
        <span className="text-base font-semibold text-gray-800">智能学习系统</span>
      </div>

      {/* 菜单区域 */}
      <nav className="flex-1 overflow-y-auto py-2">
        {menuConfig.map((item) => {
          // 叶子菜单（无子项）
          if (!item.children) {
            return (
              <NavLink
                key={item.title}
                to={item.path!}
                className={({ isActive }) =>
                  cn(
                    "flex items-center gap-2 px-4 py-2.5 text-sm font-medium transition-colors",
                    isActive
                      ? "bg-blue-50 text-blue-600"
                      : "text-gray-700 hover:bg-gray-50"
                  )
                }
              >
                {item.icon}
                <span>{item.title}</span>
              </NavLink>
            )
          }

          // 分组菜单
          const isOpen = openGroups.has(item.title)
          const hasActive = groupHasActiveChild(item, location.pathname)

          return (
            <div key={item.title}>
              <button
                type="button"
                onClick={() => toggleGroup(item.title)}
                className={cn(
                  "flex w-full items-center gap-2 px-4 py-2.5 text-sm font-medium transition-colors",
                  hasActive ? "text-blue-600" : "text-gray-700 hover:bg-gray-50"
                )}
              >
                {item.icon}
                <span className="flex-1 text-left">{item.title}</span>
                <svg
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth="2"
                  className={cn(
                    "size-4 shrink-0 transition-transform",
                    isOpen ? "rotate-90" : ""
                  )}
                >
                  <path d="m9 18 6-6-6-6" />
                </svg>
              </button>

              {/* 子菜单 */}
              {isOpen && (
                <div className="bg-gray-50/60">
                  {item.children.map((child) => (
                    <NavLink
                      key={child.title}
                      to={child.path!}
                      className={({ isActive }) =>
                        cn(
                          "flex items-center py-2 pl-11 pr-4 text-sm transition-colors",
                          isActive
                            ? "font-medium text-blue-600"
                            : "text-gray-600 hover:text-blue-600"
                        )
                      }
                    >
                      {child.title}
                    </NavLink>
                  ))}
                </div>
              )}
            </div>
          )
        })}
      </nav>
    </aside>
  )
}
