// 工作经历
export interface EmpExpr {
  id?: number
  /** 开始日期 */
  begin?: string
  /** 结束日期 */
  end?: string
  /** 公司名称 */
  company?: string
  /** 职位 */
  job?: string
}

// 员工类型定义
export interface Employee {
  id: number
  username: string
  name: string
  gender: number
  phone: string
  image: string
  job: number
  salary: number
  deptId: number
  deptName: string
  entryDate: string
  createTime: string
  updateTime: string
  /** 工作经历（列表页可能不返回，编辑时使用） */
  exprList?: EmpExpr[]
}

// 员工表单数据（新增/编辑时使用，数值字段在未选择前为 undefined）
export interface EmployeeForm {
  id?: number
  username: string
  name: string
  gender?: number
  phone: string
  image?: string
  job?: number
  salary?: number
  deptId?: number
  entryDate?: string
  exprList: EmpExpr[]
}

// 性别选项
export const GENDER_OPTIONS = [
  { label: "男", value: 1 },
  { label: "女", value: 2 },
] as const

// 职位选项
export const JOB_OPTIONS = [
  { label: "班主任", value: 1 },
  { label: "讲师", value: 2 },
  { label: "学工主管", value: 3 },
  { label: "教研主管", value: 4 },
  { label: "咨询师", value: 5 },
] as const

/** 性别编码转文字 */
export function genderText(gender?: number): string {
  return GENDER_OPTIONS.find((o) => o.value === gender)?.label ?? "-"
}

/** 职位编码转文字 */
export function jobText(job?: number): string {
  return JOB_OPTIONS.find((o) => o.value === job)?.label ?? "-"
}
