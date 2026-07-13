import type { Dept, PageResult, ApiResponse } from '@/types/dept'

// API 基础地址
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

// 通用请求函数
async function request<T>(url: string, options?: RequestInit): Promise<ApiResponse<T>> {
  const response = await fetch(`${API_BASE_URL}${url}`, {
    headers: {
      'Content-Type': 'application/json',
      ...options?.headers,
    },
    ...options,
  })

  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`)
  }

  return response.json()
}

// 部门 API
export const deptApi = {
  // 分页查询部门列表
  getPage: (page: number, pageSize: number) => {
    return request<PageResult<Dept>>(`/depts/page?page=${page}&pageSize=${pageSize}`)
  },

  // 查询所有部门
  getAll: () => {
    return request<Dept[]>('/depts')
  },

  // 根据 ID 查询部门
  getById: (id: number) => {
    return request<Dept>(`/depts/${id}`)
  },

  // 新增部门
  add: (dept: Dept) => {
    return request<void>('/depts', {
      method: 'POST',
      body: JSON.stringify(dept),
    })
  },

  // 修改部门
  update: (dept: Dept) => {
    return request<void>('/depts', {
      method: 'PUT',
      body: JSON.stringify(dept),
    })
  },

  // 删除部门
  delete: (id: number) => {
    return request<void>(`/depts/${id}`, {
      method: 'DELETE',
    })
  },
}
