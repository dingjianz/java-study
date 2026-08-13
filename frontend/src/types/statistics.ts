// 员工统计相关类型定义

/**
 * 职位统计数据（后端 JobOption 结构）
 */
export interface JobOption {
  jobList: string[]; // 职位名称列表
  dataList: number[]; // 对应的员工数量列表
}
