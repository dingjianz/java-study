# S3 上传功能实现总结

## 已完成的工作

### 1. 添加依赖
- ✅ 在 `pom.xml` 中添加了 AWS S3 SDK 依赖（版本 1.12.767）

### 2. 创建核心类

#### 配置相关
- ✅ **S3Properties.java**: S3 配置属性类，使用 `@ConfigurationProperties` 读取配置
- ✅ **S3Config.java**: S3 客户端配置类，创建 `AmazonS3` Bean

#### 服务类
- ✅ **S3Service.java**: S3 文件服务类，提供以下功能：
  - 文件上传（支持 MultipartFile 和 InputStream）
  - 文件下载
  - 文件删除
  - 文件存在性检查
  - 生成预签名 URL（临时访问链接）
  - 获取公共访问 URL
  - 创建文件夹
  - 列出文件

#### 控制器
- ✅ **FileUploadController.java**: RESTful API 控制器，提供以下接口：
  - `POST /upload` - 上传文件
  - `POST /upload/image` - 上传图片（带验证）
  - `GET /upload/download` - 下载文件
  - `DELETE /upload` - 删除文件
  - `GET /upload/url` - 获取文件访问 URL

### 3. 配置文件
- ✅ **application.yaml**: 添加了 S3 配置示例
- ✅ **application-s3.yaml.example**: 配置模板文件

### 4. 文档
- ✅ **S3_UPLOAD_GUIDE.md**: 详细的使用指南，包含：
  - 快速开始
  - API 接口说明
  - curl 示例
  - 前端集成示例
  - 常见问题解答
  - 安全建议

## 功能特性

### 文件存储路径
上传的文件按以下规则存储：
```
{prefix}/{yyyy}/{MM}/{dd}/{uuid}.{ext}
```

**示例：**
```
uploads/2026/07/19/abc123def456789.jpg
```

### 访问方式
1. **公共 URL**: 适用于公开访问的文件
   ```
   https://oss-beijing-m8.openstorage.cn/bucket-name/uploads/2026/07/19/xxx.jpg
   ```

2. **预签名 URL**: 适用于私有文件，带过期时间（默认 7 天）
   ```
   https://...?AWSAccessKeyId=...&Expires=...&Signature=...
   ```

### 安全特性
- ✅ 支持公共读写 ACL（`PublicReadWrite`）
- ✅ 支持预签名 URL 临时访问
- ✅ 图片类型验证
- ✅ 文件大小限制（可配置）
- ✅ 连接池管理（默认 200 个连接）

## 使用步骤

### 1. 配置 S3 信息
在 `application.yaml` 中配置：
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

### 2. 启动项目
```bash
cd springboot-web-02
mvn spring-boot:run
```

### 3. 测试上传
```bash
curl -X POST http://localhost:8080/upload \
  -F "file=@/path/to/your/file.jpg"
```

## API 使用示例

### 上传文件
```bash
curl -X POST http://localhost:8080/upload \
  -F "file=@photo.jpg"
```

**响应：**
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "key": "uploads/2026/07/19/abc123.jpg",
    "fileName": "photo.jpg",
    "fileSize": 102400,
    "contentType": "image/jpeg",
    "publicUrl": "https://...",
    "presignedUrl": "https://...?..."
  }
}
```

### 上传图片
```bash
curl -X POST "http://localhost:8080/upload/image?prefix=avatars" \
  -F "file=@avatar.png"
```

### 下载文件
```bash
curl -X GET "http://localhost:8080/upload/download?key=uploads/2026/07/19/abc123.jpg" \
  --output downloaded.jpg
```

### 删除文件
```bash
curl -X DELETE "http://localhost:8080/upload?key=uploads/2026/07/19/abc123.jpg"
```

### 获取访问 URL
```bash
curl -X GET "http://localhost:8080/upload/url?key=uploads/2026/07/19/abc123.jpg&expireTime=60"
```

## 前端集成

### HTML + JavaScript
```html
<input type="file" id="fileInput" />
<button onclick="uploadFile()">上传</button>

<script>
async function uploadFile() {
    const file = document.getElementById('fileInput').files[0];
    const formData = new FormData();
    formData.append('file', file);

    const response = await fetch('http://localhost:8080/upload', {
        method: 'POST',
        body: formData
    });

    const result = await response.json();
    console.log(result);
}
</script>
```

### React
```jsx
const uploadFile = async (file) => {
    const formData = new FormData();
    formData.append('file', file);

    const response = await fetch('http://localhost:8080/upload', {
        method: 'POST',
        body: formData
    });

    return await response.json();
};
```

## 目录结构

```
springboot-web-02/
├── src/main/java/com/itheima/
│   ├── config/
│   │   ├── S3Config.java           # S3 客户端配置
│   │   └── S3Properties.java       # S3 配置属性
│   ├── controller/
│   │   └── FileUploadController.java  # 文件上传 API
│   └── service/
│       └── S3Service.java          # S3 服务类
├── src/main/resources/
│   ├── application.yaml            # 主配置文件
│   └── application-s3.yaml.example # S3 配置示例
├── pom.xml                         # Maven 依赖配置
└── S3_UPLOAD_GUIDE.md             # 使用指南
```

## 技术栈

- **Spring Boot 3.5.16**: Web 框架
- **AWS SDK for Java 1.12.767**: S3 客户端
- **Lombok**: 简化代码
- **Java 17**: 运行环境

## 注意事项

### 1. 安全性
- ⚠️ 不要将 `access-key` 和 `secret-key` 提交到 Git
- ⚠️ 建议使用环境变量或密钥管理服务
- ⚠️ 生产环境建议使用 HTTPS

### 2. 文件大小限制
Spring Boot 默认限制文件大小为 1MB，如需修改：
```yaml
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB
```

### 3. 桶权限
- 确保 S3 桶已创建
- 确保 access-key 有权限访问该桶
- 根据需求设置合适的 ACL

### 4. 网络连接
- 确保服务器可以访问 S3 服务地址
- 如有防火墙，需要开放相应端口
- 建议配置连接超时和重试策略

## 参考项目

本实现参考了 `digit-cloud-gc` 项目的 S3 上传功能，简化并适配到学习项目中：
- 移除了复杂的图片处理逻辑
- 简化了依赖和配置
- 保留了核心的上传、下载、删除功能
- 添加了完整的 RESTful API

## 后续优化建议

1. **图片处理**
   - 添加图片压缩
   - 添加缩略图生成
   - 支持图片格式转换

2. **安全增强**
   - 添加用户认证
   - 添加上传权限控制
   - 添加文件类型白名单

3. **性能优化**
   - 添加上传进度反馈
   - 支持断点续传
   - 支持分片上传（大文件）

4. **监控和日志**
   - 添加上传统计
   - 添加错误监控
   - 记录操作日志

## 测试建议

1. **单元测试**
   - S3Service 的各个方法
   - FileUploadController 的接口

2. **集成测试**
   - 完整的上传下载流程
   - 异常情况处理

3. **性能测试**
   - 并发上传
   - 大文件上传

## 相关文档

- [AWS SDK for Java 官方文档](https://docs.aws.amazon.com/sdk-for-java/)
- [Amazon S3 开发指南](https://docs.aws.amazon.com/s3/)
- [Spring Boot 文件上传](https://spring.io/guides/gs/uploading-files/)

---

**编译状态：** ✅ 编译成功  
**创建时间：** 2026-07-19  
**版本：** 1.0.0
