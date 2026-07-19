# 员工头像上传到 S3 功能说明

## 功能概述

员工管理系统已集成 S3 头像上传功能，用户可以在新增或编辑员工时上传头像到 S3 存储。

## 实现细节

### 前端实现

#### 1. 上传 API (`src/api/upload.ts`)

新增了 `uploadApi`，提供以下方法：
- `uploadFile(file)` - 上传任意文件
- `uploadImage(file, prefix)` - 上传图片（带类型验证）
- `deleteFile(key)` - 删除文件
- `getFileUrl(key, expireTime)` - 获取文件访问 URL

#### 2. 头像上传组件 (`AvatarUploader`)

位于 `src/components/dept/EmployeeFormModal.tsx` 中，特性：

**功能特性：**
- ✅ 文件大小验证（最大 2MB）
- ✅ 文件类型验证（PNG、JPEG、JPG）
- ✅ 上传进度显示（加载动画）
- ✅ 头像预览
- ✅ 错误提示

**上传流程：**
1. 用户选择图片文件
2. 验证文件大小和类型
3. 调用 `uploadApi.uploadImage()` 上传到 S3
4. 获取 S3 公共 URL
5. 更新表单的 `image` 字段
6. 显示上传成功提示

**存储路径：**
上传的头像会存储在 S3 的以下路径：
```
avatars/yyyy/MM/dd/uuid.ext
```

示例：
```
avatars/2026/07/19/abc123def456789.jpg
```

### 后端实现

后端已实现 S3 上传接口，详见 `springboot-web-02/S3_UPLOAD_GUIDE.md`

**主要接口：**
- `POST /upload/image?prefix=avatars` - 上传图片

**响应格式：**
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "key": "avatars/2026/07/19/abc123.jpg",
    "fileName": "avatar.jpg",
    "publicUrl": "https://oss-beijing-m8.openstorage.cn/bucket/avatars/2026/07/19/abc123.jpg",
    "presignedUrl": "https://...?..."
  }
}
```

## 使用方法

### 1. 配置 S3（后端）

在 `springboot-web-02/src/main/resources/application.yaml` 中配置：

```yaml
aws:
  s3:
    url: oss-beijing-m8.openstorage.cn
    access-key: YOUR_ACCESS_KEY
    secret-key: YOUR_SECRET_KEY
    bucket: your-bucket-name
    max-connections: 200
    region: cn-north-1
```

### 2. 启动后端服务

```bash
cd springboot-web-02
mvn spring-boot:run
```

### 3. 启动前端服务

```bash
cd frontend
npm run dev
```

### 4. 上传头像

1. 打开员工管理页面 `http://localhost:8082/employees`
2. 点击「新增员工」或「编辑」按钮
3. 在表单中找到「头像」区域
4. 点击头像上传框，选择图片文件
5. 等待上传完成（显示上传动画）
6. 看到头像预览后，填写其他信息并保存

## 用户界面

### 头像上传区域

```
┌─────────────────────────────────────────┐
│ 头像  ┌────────┐  图片大小不超过 2M    │
│       │        │  仅能上传 PNG、JPEG、  │
│       │  📷   │  JPG 等图片            │
│       │        │  建议上传 200*200 或   │
│       └────────┘  300*300 尺寸的照片   │
└─────────────────────────────────────────┘
```

### 上传状态

**未选择：**
```
┌────────┐
│   📷   │  显示图片图标
└────────┘
```

**上传中：**
```
┌────────┐
│   ⏳   │  显示加载动画
│ 上传中...│
└────────┘
```

**上传成功：**
```
┌────────┐
│ [头像] │  显示图片预览
└────────┘
```

## 验证规则

### 文件大小
- **最大：** 2MB
- **超出提示：** "图片大小不能超过 2M"

### 文件类型
- **允许：** PNG、JPEG、JPG
- **不允许提示：** "仅支持 PNG、JPEG、JPG 格式的图片"

### 建议尺寸
- **推荐：** 200×200 或 300×300 像素
- **说明：** 保证头像在列表中清晰显示

## 技术实现

### 前端技术栈
- **React** - UI 框架
- **TypeScript** - 类型安全
- **Axios** - HTTP 客户端
- **Sonner** - Toast 提示

### 上传流程

```typescript
// 1. 用户选择文件
const file = e.target.files?.[0]

// 2. 验证文件
if (file.size > 2 * 1024 * 1024) {
  toast.warning("图片大小不能超过 2M")
  return
}

// 3. 上传到 S3
const response = await uploadApi.uploadImage(file, 'avatars')

// 4. 获取 URL
const imageUrl = response.data.publicUrl

// 5. 更新表单
onChange(imageUrl)

// 6. 提示成功
toast.success("头像上传成功")
```

### API 调用

```typescript
// uploadApi.uploadImage 实现
uploadImage(file: File, prefix = 'images'): Promise<{ data: UploadResponse }> {
  const formData = new FormData()
  formData.append('file', file)

  return axios.post('/upload/image', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    params: {
      prefix,
    },
  })
}
```

## 错误处理

### 前端错误
- ❌ 文件过大 → Toast 提示
- ❌ 文件类型错误 → Toast 提示
- ❌ 上传失败 → Toast 提示 + 控制台日志

### 后端错误
- ❌ S3 连接失败 → HTTP 响应拦截器处理
- ❌ 权限不足 → HTTP 响应拦截器处理
- ❌ 网络错误 → HTTP 响应拦截器处理

## 后续优化建议

### 1. 图片裁剪
添加图片裁剪功能，让用户在上传前调整头像：
- 使用 `react-image-crop` 库
- 支持缩放、旋转
- 预设 1:1 比例

### 2. 图片压缩
在上传前压缩图片，减少上传时间：
- 使用 `browser-image-compression` 库
- 自动压缩超过 500KB 的图片
- 保持图片质量

### 3. 拖拽上传
支持拖拽文件到上传区域：
- 使用 HTML5 Drag & Drop API
- 显示拖拽提示
- 更好的用户体验

### 4. 批量上传
支持一次选择多个文件上传：
- 显示上传进度列表
- 支持取消单个上传
- 显示上传队列

### 5. 图片预览增强
- 点击头像查看大图
- 支持删除已上传的头像
- 显示上传时间和文件大小

## 测试建议

### 功能测试
- ✅ 上传正常图片（PNG、JPEG、JPG）
- ✅ 上传超大文件（> 2MB）
- ✅ 上传错误格式（GIF、BMP 等）
- ✅ 取消上传
- ✅ 重新上传

### 边界测试
- 最小尺寸图片（1×1）
- 最大尺寸图片（10000×10000）
- 空文件
- 损坏的图片文件

### 兼容性测试
- Chrome
- Firefox
- Safari
- Edge

## 常见问题

### 1. 上传失败

**问题：** 点击上传后提示"头像上传失败，请重试"

**排查步骤：**
1. 检查后端服务是否运行（`http://localhost:8080`）
2. 检查 S3 配置是否正确（`application.yaml`）
3. 检查网络连接
4. 查看浏览器控制台错误信息
5. 查看后端日志

### 2. 图片不显示

**问题：** 上传成功但列表中不显示头像

**排查步骤：**
1. 检查返回的 `publicUrl` 是否可访问
2. 检查 S3 桶的访问权限（应为 PublicReadWrite）
3. 检查浏览器控制台是否有 CORS 错误
4. 尝试在新标签页打开图片 URL

### 3. 上传速度慢

**问题：** 上传进度很慢

**优化方案：**
1. 检查网络速度
2. 在上传前压缩图片
3. 调整 S3 配置中的 `max-connections`
4. 考虑使用 CDN 加速

### 4. 文件类型限制

**问题：** 想上传 GIF、WebP 等格式

**解决方案：**
修改前端验证规则：
```typescript
const validTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif', 'image/webp']
```

修改后端配置（如需要）

## 相关文档

- [S3 上传功能使用指南](../../springboot-web-02/S3_UPLOAD_GUIDE.md)
- [S3 实现总结](../../springboot-web-02/S3_IMPLEMENTATION_SUMMARY.md)
- [AWS SDK for Java 文档](https://docs.aws.amazon.com/sdk-for-java/)

---

**创建时间：** 2026-07-19  
**版本：** 1.0.0
