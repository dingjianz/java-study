# 工作经历空对象过滤功能

## 问题描述

在添加员工时，如果用户点击了"添加工作经历"按钮但没有填写任何内容，会产生空对象 `{}`，这些空对象会被提交到后端，导致：
- 数据库中产生无效记录
- 可能触发数据库约束错误
- 影响数据质量

## 解决方案

采用**前后端双重过滤**的策略，确保空对象不会被保存到数据库。

### 1. 前端过滤（第一道防线）

**位置：** `frontend/src/components/dept/EmployeeFormModal.tsx`

**逻辑：** 在提交前过滤 `exprList`，只保留至少有一个字段有值的记录

```typescript
// 过滤掉空的工作经历对象
const filteredExprList = (form.exprList ?? []).filter((expr) => {
  // 只要有任一字段有值，就保留该条记录
  return (
    expr.beginDate ||
    expr.endDate ||
    expr.company?.trim() ||
    expr.job?.trim()
  )
})

const submitData = { ...form, exprList: filteredExprList }
```

### 2. 后端过滤（第二道防线）

**位置：** `springboot-web-02/src/main/java/com/itheima/service/impl/EmpServiceImpl.java`

**逻辑：** 在保存工作经历前再次过滤，确保数据安全

```java
// 过滤掉空的工作经历对象（所有字段都为空的记录）
List<EmpExpr> validExprList = emp.getExprList().stream()
        .filter(expr -> expr.getBeginDate() != null
                || expr.getEndDate() != null
                || (expr.getCompany() != null && !expr.getCompany().trim().isEmpty())
                || (expr.getJob() != null && !expr.getJob().trim().isEmpty()))
        .toList();

// 如果过滤后还有有效的工作经历，则保存
if (!validExprList.isEmpty()) {
    validExprList.forEach(expr -> expr.setEmpId(emp.getId()));
    empExprService.insertBatch(validExprList);
}
```

## 测试验证

### 测试用例 1：部分空对象

**输入：**
```json
{
  "username": "wangwu",
  "name": "王五",
  "phone": "13611112222",
  "exprList": [
    {
      "beginDate": "2021-05-01",
      "endDate": "2024-02-28",
      "company": "测试公司",
      "job": "班主任"
    },
    {},  // 空对象
    {
      "beginDate": "2019-01-01",
      "endDate": "2021-04-30",
      "company": "另一家公司",
      "job": "助教"
    },
    {}   // 空对象
  ]
}
```

**结果：**
- ✅ 员工保存成功（ID=31）
- ✅ 只保存了 2 条有效的工作经历
- ✅ 空对象被过滤掉

**数据库验证：**
```sql
SELECT * FROM emp_expr WHERE emp_id = 31;
-- 结果：2 条记录（测试公司 和 另一家公司）
```

### 测试用例 2：全部为空对象

**输入：**
```json
{
  "username": "zhaoliu",
  "name": "赵六",
  "phone": "13722223333",
  "exprList": [
    {},
    {},
    {}
  ]
}
```

**结果：**
- ✅ 员工保存成功（ID=32）
- ✅ 工作经历表中没有记录（count=0）
- ✅ 所有空对象都被过滤掉

**数据库验证：**
```sql
SELECT COUNT(*) FROM emp_expr WHERE emp_id = 32;
-- 结果：0
```

### 测试用例 3：没有工作经历

**输入：**
```json
{
  "username": "test",
  "name": "测试",
  "phone": "13800000000",
  "exprList": []  // 或不传 exprList
}
```

**结果：**
- ✅ 员工保存成功
- ✅ 不执行工作经历插入逻辑

## 判断标准

一个工作经历对象被视为**有效**的条件是：**至少有一个字段不为空**

| 字段 | 检查逻辑 |
|------|---------|
| `beginDate` | 不为 null |
| `endDate` | 不为 null |
| `company` | 不为 null 且 trim 后不为空字符串 |
| `job` | 不为 null 且 trim 后不为空字符串 |

只要满足以上任一条件，该工作经历对象就会被保留。

## 优势

1. **双重保险**：前后端都过滤，即使一方失效也不会产生脏数据
2. **性能优化**：前端过滤减少不必要的网络传输
3. **数据质量**：确保数据库中不会有无效的工作经历记录
4. **用户体验**：不需要强制用户删除空的工作经历输入框

## 相关文件

- `frontend/src/components/dept/EmployeeFormModal.tsx` - 前端过滤逻辑
- `springboot-web-02/src/main/java/com/itheima/service/impl/EmpServiceImpl.java` - 后端过滤逻辑

---

**实现时间：** 2026-07-19  
**测试状态：** ✅ 通过