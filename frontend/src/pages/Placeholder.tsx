import { useLocation } from "react-router-dom"
import { menuConfig } from "@/components/layout/menu-config"

/** 根据路径找到对应菜单标题 */
function findTitle(pathname: string): string {
  for (const item of menuConfig) {
    if (item.path === pathname) return item.title
    const child = item.children?.find((c) => c.path === pathname)
    if (child) return child.title
  }
  return "该模块"
}

export default function PlaceholderPage() {
  const location = useLocation()
  const title = findTitle(location.pathname)

  return (
    <div className="flex h-full flex-col items-center justify-center text-center">
      <div className="mb-4 flex size-16 items-center justify-center rounded-full bg-blue-50 text-blue-500">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" className="size-8">
          <path d="M12 9v4" />
          <path d="M12 17h.01" />
          <circle cx="12" cy="12" r="9" />
        </svg>
      </div>
      <h2 className="text-xl font-semibold text-gray-800">{title}</h2>
      <p className="mt-2 text-sm text-gray-500">该模块正在开发中，敬请期待</p>
    </div>
  )
}
