# CakeGrowth SDK 安装说明

## 第一步：在 public/index.html 中添加 SDK

打开 `public/index.html`，在 `<head>` 标签中添加以下代码：

```html
<script
  async
  src="https://www.cakegrowth.cn/static/cg.min.js"
  data-cakegrowth="YOUR-API-KEY"
  data-env="prod"
></script>
```

**注意：** 请将 `YOUR-API-KEY` 替换为实际的 API Key（联系 CakeGrowth 技术支持获取）

## 第二步：启动开发服务器

```bash
cd frontend
npm run dev
```

## 第三步：访问测试页面

打开浏览器访问：

```
http://localhost:8082/cakegrowth-test
```

## 测试步骤

1. 页面加载后，查看"SDK 状态"卡片，确认 SDK 已就绪且获取到 visitorId
2. 点击"生成测试数据"按钮，自动填充表单
3. 点击"上报注册"按钮，模拟用户注册事件
4. 查看响应结果，确认上报成功
5. 打开浏览器控制台，可以看到详细的请求日志

## 当前配置

- **ACCESS_KEY**: `9povk4nj8y`
- **ACCESS_SECRET**: `s08XOnfNWYNZFnbhOJ57iB1ibPfh64d9M74fomga7pc7Pc0M`
- **API_BASE_URL**: `https://api.cakegrowth.cn/v1/open`

## 文件说明

- `src/utils/cakegrowth.ts` - 签名工具函数
- `src/api/cakegrowth.ts` - API 接口封装
- `src/pages/CakeGrowthTest.tsx` - 测试页面
- `src/types/cakegrowth.d.ts` - TypeScript 类型声明

## 常见问题

### SDK 未就绪
- 检查 `public/index.html` 中是否正确添加了 SDK 脚本
- 确认 API Key 是否正确
- 查看浏览器控制台是否有错误

### visitorId 未获取
- 确保访问的 URL 包含 `?utm=cg&cgv=install` 参数
- 联系 CakeGrowth 技术支持初始化项目，并添加测试域名 `http://localhost:8084`

### 上报失败
- 检查网络连接
- 查看浏览器控制台的错误信息
- 确认签名是否正确（会在控制台打印详细日志）
