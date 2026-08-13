// 员工统计相关类型定义

/**
 * 职位统计数据（后端 JobOption 结构）
 */
export interface JobOption {
  jobList: string[]; // 职位名称列表
  dataList: number[]; // 对应的员工数量列表
}

/**
 * 性别统计数据项（后端返回的单条记录）
 * 对应 SQL: select if(gender = 1, '男', '女') as name, count(*) as value
 */
export interface GenderDataItem {
  name: string;  // 性别名称（'男' 或 '女'）
  value: number; // 该性别的员工数量
}
