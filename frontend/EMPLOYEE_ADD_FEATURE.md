# 员工新增功能实现说明

## ✅ 功能已完成并测试通过

实现了完整的新增员工功能，包括：
- ✅ 员工基本信息（用户名、姓名、性别、手机号、职位、薪资、部门、入职日期、头像）
- ✅ 员工工作经历（支持添加多条工作经历记录）
- ✅ 头像上传到 S3
- ✅ 事务性保存（员工信息和工作经历同时保存或同时失败）
- ✅ 默认密码设置为 "123456"

## 测试验证

### 测试数据
```json
{
  "username": "zhang3",
  "name": "张三",
  "gender": 1,
  "phone": "13912345678",
  "job": 2,
  "salary": 8000,
  "deptId": 1,
  "entryDate": "2024-01-15",
  "image": "https://example.com/avatars/zhang3.jpg",
  "exprList": [
    {
      "beginDate": "2020-01-01",
      "endDate": "2023-12-31",
      "company": "ABC科技",
      "job": "Java开发工程师"
    },
    {
      "beginDate": "2018-06-01",
      "endDate": "2019-12-31",
      "company": "XYZ公司",
      "job": "实习生"
    }
  ]
}
```

### 测试结果
- **API 响应**：`{"code":1,"msg":"成功","data":null}`
- **员工信息**：ID=29, username=zhang3, name=张三, phone=13912345678, deptName=学工部
- **工作经历**：2 条记录成功插入到 `emp_expr` 表

### 数据库验证
```sql
-- 员工信息
SELECT * FROM emp WHERE id = 29;
-- 结果：ID=29, username=zhang3, name=张三, password=123456（自动设置）

-- 工作经历
SELECT * FROM emp_expr WHERE emp_id = 29;
-- 结果：2 条记录（ABC科技 和 XYZ公司）
```

## 已完成的修改

### 后端修改

1. **EmpExprMapper.java** - 添加工作经历批量插入方法
   - `insertBatch(List<EmpExpr>)` - 批量插入工作经历
   - `insert(EmpExpr)` - 插入单条工作经历

2. **EmpExprMapper.xml** - 新建 XML 映射文件
   - 实现 `insertBatch` 的批量插入 SQL

3. **EmpExprService.java** - 添加服务接口
   - `insertBatch(List<EmpExpr>)` 方法定义

4. **EmpExprServiceImpl.java** - 实现服务逻辑
   - 完善批量插入的实现（带空值检查）

5. **EmpMapper.xml** - 优化插入语句
   - 添加 `useGeneratedKeys="true"` 和 `keyProperty="id"` 以获取自增主键

6. **EmpServiceImpl.java** - 完善新增员工逻辑
   - 设置默认密码 "123456"（前端不传递密码）
   - 设置 createTime 和 updateTime
   - 添加 `@Transactional` 注解确保事务一致性
   - 插入员工信息后获取自增 ID
   - 将 ID 设置到工作经历列表，批量插入工作经历

### 前端代码（已存在，无需修改）

1. **EmployeeFormModal.tsx** - 员工表单弹窗组件
   - 完整的表单字段（用户名、姓名、性别、手机号、职位、薪资、部门、入职日期）
   - 头像上传功能（上传到 S3）
   - 动态添加/删除工作经历
   - 表单验证（必填字段检查）
   - 调用 `empApi.add()` 新增员工

2. **emps.ts** - 员工 API 封装
   - `add(emp)` - 调用 `POST /emps` 接口

3. **EmployeeManage.tsx** - 员工管理页面
   - 「新增员工」按钮和事件处理
   - 保存成功后刷新列表

## 测试步骤

### 1. 启动后端服务

```bash
cd springboot-web-02
mvn spring-boot:run
```

确保后端运行在 `http://localhost:8080`

### 2. 启动前端服务

```bash
cd frontend
npm run dev
```

前端运行在 `http://localhost:8082`

### 3. 测试新增员工

1. 打开浏览器访问 `http://localhost:8082`
2. 进入「员工管理」页面
3. 点击「新增员工」按钮
4. 填写表单：
   - **必填**：用户名、姓名、性别、手机号
   - **可选**：职位、薪资、所属部门、入职日期、头像、工作经历

#### 测试用例 1：最小必填信息

```
用户名：test001
姓名：测试员工
性别：男
手机号：13800138001
```

点击保存，应该：
- 后端插入成功，密码自动设置为 "123456"
- 前端显示「新增成功」提示
- 列表自动刷新，新员工出现在第一条

#### 测试用例 2：完整信息含工作经历

```
用户名：test002
姓名：张三
性别：女
手机号：13800138002
职位：讲师
薪资：8000
所属部门：选择任意部门
入职日期：2024-01-01
头像：上传一张图片（PNG/JPG，< 2M）

工作经历：
  - 时间：2020-01-01 到 2023-12-31，公司：ABC 公司，职位：Java 开发
  - 时间：2018-01-01 到 2019-12-31，公司：XYZ 公司，职位：实习生
```

点击保存，应该：
- 后端同时插入员工信息和 2 条工作经历
- 如果插入失败（如工作经历表不存在），整个事务回滚
- 前端显示「新增成功」提示
- 列表刷新后，可以编辑该员工查看工作经历是否保存成功

### 4. 验证数据库

连接到 MySQL 数据库：

```sql
-- 查看新增的员工
SELECT * FROM emp ORDER BY create_time DESC LIMIT 5;

-- 查看工作经历
SELECT * FROM emp_expr WHERE emp_id = <新员工的id>;
```

## 数据库表结构要求

### emp 表（员工表）

```sql
CREATE TABLE emp (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(50) NOT NULL,
  gender TINYINT,
  phone VARCHAR(20),
  job TINYINT,
  salary INT,
  entry_date DATE,
  create_time DATETIME,
  update_time DATETIME,
  dept_id INT,
  image VARCHAR(500)
);
```

### emp_expr 表（工作经历表）

```sql
CREATE TABLE emp_expr (
  id INT PRIMARY KEY AUTO_INCREMENT,
  emp_id INT NOT NULL,
  begin_date DATE,
  end_date DATE,
  company VARCHAR(100),
  job VARCHAR(50),
  FOREIGN KEY (emp_id) REFERENCES emp(id) ON DELETE CASCADE
);
```

如果 `emp_expr` 表不存在，需要先创建该表。

## API 接口说明

### 新增员工

**请求：**

```
POST /emps
Content-Type: application/json

{
  "username": "test001",
  "name": "测试员工",
  "gender": 1,
  "phone": "13800138001",
  "job": 2,
  "salary": 8000,
  "deptId": 1,
  "entryDate": "2024-01-01",
  "image": "https://s3.amazonaws.com/bucket/avatars/xxx.jpg",
  "exprList": [
    {
      "beginDate": "2020-01-01",
      "endDate": "2023-12-31",
      "company": "ABC 公司",
      "job": "Java 开发"
    }
  ]
}
```

**响应：**

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

## 注意事项

1. **密码处理**：前端不传递密码，后端自动设置默认密码 "123456"
2. **时间字段**：
   - `createTime` 和 `updateTime` 由后端自动设置
   - `entryDate` 由前端传递（格式：yyyy-MM-dd）
3. **工作经历**：
   - `exprList` 可以为空数组或不传
   - 后端会自动设置每条工作经历的 `empId`
4. **事务处理**：使用 `@Transactional` 确保员工信息和工作经历同时保存成功或失败
5. **头像上传**：
   - 前端先上传图片到 S3 获取 URL
   - 将 URL 保存在 `image` 字段
   - 确保 S3 配置正确（参考 `springboot-web-02/S3_CONFIG_DONE.md`）

## 可能的问题和解决方案

### 问题 1：emp_expr 表不存在

**错误信息：** `Table 'web02.emp_expr' doesn't exist`

**解决方案：** 执行上面的建表 SQL 创建 `emp_expr` 表

### 问题 2：头像上传失败

**错误信息：** S3 相关错误

**解决方案：** 
- 检查 `application-s3.yaml` 配置是否正确
- 确保 S3 bucket 存在且有写入权限
- 或者跳过头像上传，直接保存（`image` 字段可以为空）

### 问题 3：部门下拉为空

**原因：** 部门表 `dept` 没有数据

**解决方案：**
```sql
INSERT INTO dept (name, create_time, update_time) 
VALUES ('教研部', NOW(), NOW()),
       ('咨询部', NOW(), NOW()),
       ('就业部', NOW(), NOW());
```

## 后续优化建议

1. **密码加密**：当前密码是明文存储，建议使用 BCrypt 加密
2. **表单验证增强**：
   - 用户名重复检查
   - 手机号格式验证
   - 薪资范围验证
3. **工作经历校验**：
   - 开始日期不能晚于结束日期
   - 日期不能是未来时间
4. **权限控制**：添加登录用户权限验证
5. **操作日志**：记录员工创建操作日志
