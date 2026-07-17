import { request } from '@/utils/http'
import type { Employee } from '@/types/employee'
import type { PageResult } from '@/types/dept'

// 员工 API
export const empApi = {
    // 分页查询员工列表
    getPage: (page: number, pageSize: number) => {
      return request.get<PageResult<Employee>>('/emps', {
        params: { page, pageSize }
      })
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
