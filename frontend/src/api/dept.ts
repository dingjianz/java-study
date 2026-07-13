import { request } from '@/utils/http'
import type { Dept, PageResult } from '@/types/dept'

// 部门 API
export const deptApi = {
  // 分页查询部门列表
  getPage: (page: number, pageSize: number) => {
    return request.get<PageResult<Dept>>('/depts/page', {
      params: { page, pageSize }
    })
  },

  // 查询所有部门
  getAll: () => {
    return request.get<Dept[]>('/depts')
  },

  // 根据 ID 查询部门
  getById: (id: number) => {
    return request.get<Dept>(`/depts/${id}`)
  },

  // 新增部门
  add: (dept: Dept) => {
    return request.post<void>('/depts', dept)
  },

  // 修改部门
  update: (dept: Dept) => {
    return request.put<void>('/depts', dept)
  },

  // 删除部门
  delete: (id: number) => {
    return request.delete<void>(`/depts/${id}`)
  },
}
