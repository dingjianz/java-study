// 部门类型定义
export interface Dept {
  id?: number
  name: string
  createTime?: string
  updateTime?: string
}

// 分页结果类型
export interface PageResult<T> {
  total: number
  rows: T[]
}

// API 响应类型
export interface ApiResponse<T = any> {
  code: number
  message: string
  data: T
}
