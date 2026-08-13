import { useEffect, useState } from 'react';
import ReactECharts from 'echarts-for-react';
import type { EChartsOption } from 'echarts';
import { getEmpJobData } from '@/api/statistics';
import type { JobOption } from '@/types/statistics';

/**
 * 员工统计页面
 * 目前包含职位统计柱状图
 */
export default function EmployeeStatisticsPage() {
  const [jobStats, setJobStats] = useState<JobOption | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchStatistics();
  }, []);

  const fetchStatistics = async () => {
    try {
      setLoading(true);
      const result = await getEmpJobData();
      setJobStats(result);
    } catch (error) {
      // 错误提示已由 http 响应拦截器统一弹出，这里仅记录日志
      console.error('获取员工职位统计失败:', error);
    } finally {
      setLoading(false);
    }
  };

  // 职位统计柱状图配置
  const jobChartOption: EChartsOption = {
    title: {
      text: '员工职位统计',
      left: 'center',
      top: 20,
      textStyle: {
        fontSize: 18,
        fontWeight: 'normal',
      },
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: 80,
      containLabel: true,
    },
    xAxis: {
      type: 'category',
      data: jobStats?.jobList ?? [],
      axisLabel: {
        interval: 0,
        rotate: 0,
      },
    },
    yAxis: {
      type: 'value',
      minInterval: 1,
    },
    series: [
      {
        type: 'bar',
        data: jobStats?.dataList ?? [],
        itemStyle: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [
              { offset: 0, color: '#FFA726' }, // 顶部橙色
              { offset: 1, color: '#EF5350' }, // 底部粉红色
            ],
          },
          borderRadius: [4, 4, 0, 0],
        },
        barWidth: '40%',
      },
    ],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
      formatter: '{b}: {c} 人',
    },
  };

  if (loading) {
    return (
      <div className="flex h-full items-center justify-center">
        <div className="text-lg text-gray-500">加载中...</div>
      </div>
    );
  }

  if (!jobStats) {
    return (
      <div className="flex h-full items-center justify-center">
        <div className="text-lg text-gray-500">暂无数据</div>
      </div>
    );
  }

  return (
    <div className="h-full overflow-auto p-6">
      <div className="grid grid-cols-1 gap-6">
        {/* 职位统计柱状图 */}
        <div className="rounded-lg bg-white p-6 shadow-sm">
          <ReactECharts option={jobChartOption} style={{ height: '500px' }} />
        </div>
      </div>
    </div>
  );
}
