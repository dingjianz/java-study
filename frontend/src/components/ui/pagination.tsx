import * as React from "react"
import { cn } from "@/lib/utils"

interface PaginationProps {
  /** 当前页码，从 1 开始 */
  page: number
  /** 每页条数 */
  pageSize: number
  /** 数据总条数 */
  total: number
  /** 页码或每页条数变化时的回调 */
  onChange?: (page: number, pageSize: number) => void
  /** 可选的每页条数选项 */
  pageSizeOptions?: number[]
  /** 只有一页数据时是否隐藏分页，默认 false（始终展示） */
  hideOnSinglePage?: boolean
  className?: string
}

/** 生成页码序列，超出部分用省略号（-1 左，-2 右）占位 */
function getPageItems(current: number, totalPages: number): number[] {
  if (totalPages <= 7) {
    return Array.from({ length: totalPages }, (_, i) => i + 1)
  }

  const items: number[] = [1]

  if (current > 4) {
    items.push(-1)
  }

  const start = Math.max(2, current - 1)
  const end = Math.min(totalPages - 1, current + 1)
  for (let i = start; i <= end; i++) {
    items.push(i)
  }

  if (current < totalPages - 3) {
    items.push(-2)
  }

  items.push(totalPages)
  return items
}

const Pagination = ({
  page,
  pageSize,
  total,
  onChange,
  pageSizeOptions = [10, 20, 50, 100],
  hideOnSinglePage = false,
  className,
}: PaginationProps) => {
  const totalPages = Math.max(1, Math.ceil(total / pageSize))
  const currentPage = Math.min(Math.max(1, page), totalPages)

  // 只有一页数据且开启了 hideOnSinglePage 时，不渲染分页
  if (hideOnSinglePage && totalPages <= 1) {
    return null
  }

  const goTo = (target: number) => {
    const next = Math.min(Math.max(1, target), totalPages)
    if (next !== currentPage) {
      onChange?.(next, pageSize)
    }
  }

  const handlePageSizeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const nextSize = Number(e.target.value)
    onChange?.(1, nextSize)
  }

  const pageItems = getPageItems(currentPage, totalPages)

  const itemClass =
    "inline-flex h-8 min-w-8 items-center justify-center rounded-md px-2 text-sm transition-colors disabled:pointer-events-none disabled:opacity-50"

  return (
    <div
      className={cn(
        "flex items-center justify-between gap-4 text-sm text-gray-600",
        className
      )}
    >
      <div className="flex items-center gap-2">
        <span>共 {total} 条</span>
        <select
          value={pageSize}
          onChange={handlePageSizeChange}
          className="h-8 rounded-md border border-gray-300 bg-white px-2 text-sm text-gray-700 focus:outline-none focus:ring-1 focus:ring-gray-400"
        >
          {pageSizeOptions.map((size) => (
            <option key={size} value={size}>
              {size} 条/页
            </option>
          ))}
        </select>
      </div>

      <div className="flex items-center gap-1">
        <button
          type="button"
          className={cn(itemClass, "hover:bg-gray-100")}
          onClick={() => goTo(currentPage - 1)}
          disabled={currentPage <= 1}
          aria-label="上一页"
        >
          上一页
        </button>

        {pageItems.map((item, idx) =>
          item < 0 ? (
            <span
              key={`ellipsis-${idx}`}
              className="inline-flex h-8 min-w-8 items-center justify-center text-gray-400"
            >
              …
            </span>
          ) : (
            <button
              key={item}
              type="button"
              className={cn(
                itemClass,
                item === currentPage
                  ? "bg-gray-900 text-white"
                  : "text-gray-700 hover:bg-gray-100"
              )}
              onClick={() => goTo(item)}
              aria-current={item === currentPage ? "page" : undefined}
            >
              {item}
            </button>
          )
        )}

        <button
          type="button"
          className={cn(itemClass, "hover:bg-gray-100")}
          onClick={() => goTo(currentPage + 1)}
          disabled={currentPage >= totalPages}
          aria-label="下一页"
        >
          下一页
        </button>
      </div>
    </div>
  )
}

export { Pagination }
export type { PaginationProps }
