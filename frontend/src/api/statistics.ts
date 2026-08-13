import { request } from '@/utils/http';
import type { JobOption } from '@/types/statistics';

/**
 * 获取员工职位统计数据
 * request 已在 http.ts 中自动解包 response.data.data
 */
export const getEmpJobData = (): Promise<JobOption> => {
  return request.get<JobOption>('/report/empJobData');
};
