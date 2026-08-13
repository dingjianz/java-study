import { request } from '@/utils/http';
import type { JobOption, GenderDataItem } from '@/types/statistics';

/**
 * 获取员工职位统计数据
 * request 已在 http.ts 中自动解包 response.data.data
 */
export const getEmpJobData = (): Promise<JobOption> => {
  return request.get<JobOption>('/report/empJobData');
};

/**
 * 获取员工性别统计数据
 * request 已在 http.ts 中自动解包 response.data.data
 * 后端返回 List<Map<String, Object>>，每项包含 { name: string, value: number }
 */
export const getEmpGenderData = (): Promise<GenderDataItem[]> => {
  return request.get<GenderDataItem[]>('/report/empGenderData');
};
