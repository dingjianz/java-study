import { request } from '@/utils/http'
import type { Employee } from '@/types/employee'

// 员工 API
export const empApi = {
  // 查询所有员工（后端已实现：GET /emps）
  getAll: () => {
    return request.get<Employee[]>('/emps')
  },

  // 以下端点后端暂未实现，待后端补齐后即可使用
  // 根据 ID 查询员工
  getById: (id: number) => {
    return request.get<Employee>(`/emps/${id}`)
  },

  // 新增员工
  add: (emp: Employee) => {
    return request.post<void>('/emps', emp)
  },

  // 修改员工
  update: (emp: Employee) => {
    return request.put<void>('/emps', emp)
  },

  // 删除员工
  delete: (id: number) => {
    return request.delete<void>(`/emps?id=${id}`)
  },
}
