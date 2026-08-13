# 员工统计页面实现说明

## 功能概述

在 `/admin/stat/employee` 路由实现了员工统计页面，包含两个图表：

1. **员工职位统计**（柱状图）- 显示不同职位的员工数量，带橙红渐变色
2. **员工性别统计**（环形图）- 显示男女比例分布，使用灰色系配色

## 技术实现

### 依赖包
- `echarts` - 图表核心库
- `echarts-for-react` - React 封装组件

### 文件结构

```
frontend/src/
├── api/
│   ├── statistics.ts              # 统计 API 接口
│   └── mock/
│       └── statistics.ts          # 模拟数据（开发用）
├── types/
│   └── statistics.ts              # 统计数据类型定义
├── pages/
│   └── EmployeeStatistics.tsx     # 员工统计页面组件
└── App.tsx                        # 路由配置（已更新）
```

### API 接口

**端点**: `GET /employees/stats`

**响应格式**:
```typescript
{
  code: 1,
  msg: "success",
  data: {
    jobStats: [
      { job: "讲师", count: 14 },
      { job: "咨询师", count: 8 },
      // ...
    ],
    genderStats: [
      { gender: 1, count: 18 },  // 1-男
      { gender: 2, count: 14 }   // 2-女
    ]
  }
}
```

## 使用说明

### 开发环境

前端已实现完整功能，目前使用模拟数据：

1. 启动前端：`npm run dev`
2. 访问：http://localhost:8082/admin/stat/employee
3. 从左侧菜单「数据统计管理 > 员工信息统计」进入

### 后端对接

需要在 Spring Boot 后端实现对应接口：

1. 创建 `EmployeeStatisticsController`
2. 实现 `GET /employees/stats` 端点
3. 返回符合上述格式的 JSON 数据

示例后端实现（伪代码）：
```java
@RestController
@RequestMapping("/employees")
public class EmployeeStatisticsController {
    
    @GetMapping("/stats")
    public Result<EmployeeStatistics> getStatistics() {
        // 查询职位统计
        List<JobStatItem> jobStats = employeeMapper.countByJob();
        
        // 查询性别统计
        List<GenderStatItem> genderStats = employeeMapper.countByGender();
        
        return Result.success(new EmployeeStatistics(jobStats, genderStats));
    }
}
```

## 图表配置

### 柱状图特性
- 橙红渐变色（顶部 #FFA726 → 底部 #EF5350）
- 圆角顶部
- 鼠标悬停显示提示框

### 环形图特性
- 内半径 50%，外半径 70%（环形效果）
- 灰色系配色
- 图例显示在右侧
- 鼠标悬停显示百分比

## 响应式布局

- 桌面端：两个图表并排显示（`grid-cols-2`）
- 移动端：图表垂直堆叠（`grid-cols-1`）
- 每个图表高度固定为 500px

## 注意事项

1. **模拟数据**：当前使用 `mockEmployeeStatistics`，后端 API 实现后会自动切换
2. **错误处理**：API 失败时自动降级到模拟数据，无需手动处理
3. **加载状态**：显示「加载中...」提示
4. **空数据**：显示「暂无数据」提示

## 后续优化建议

1. 添加数据刷新按钮
2. 支持日期范围筛选
3. 添加数据导出功能
4. 增加更多维度的统计图表（如部门分布、入职时间分布等）
