# S3 文件上传功能使用指南

## 功能概述

本项目已集成 AWS S3 文件上传功能，支持：
- ✅ 文件上传到 S3（支持任意文件类型）
- ✅ 图片上传（带类型验证）
- ✅ 文件下载
- ✅ 文件删除
- ✅ 生成预签名 URL（临时访问链接）
- ✅ 获取公共访问 URL

## 快速开始

### 1. 配置 S3 信息

在 `application.yaml` 中配置 S3 连接信息：

```yaml
aws:
  s3:
    url: oss-beijing-m8.openstorage.cn  # S3 服务地址
    access-key: YOUR_ACCESS_KEY          # 访问密钥
    secret-key: YOUR_SECRET_KEY          # 密钥
    bucket: your-bucket-name             # 桶名称
    max-connections: 200                 # 最大连接数
    region: cn-north-1                   # 区域
```

**注意：** 请将 `YOUR_ACCESS_KEY`、`YOUR_SECRET_KEY` 和 `your-bucket-name` 替换为实际值。

### 2. API 接口说明

#### 2.1 上传文件

**接口：** `POST /upload`

**参数：**
- `file`: 文件（multipart/form-data）

**响应示例：**
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "key": "uploads/2026/07/19/abc123def456.jpg",
    "fileName": "photo.jpg",
    "fileSize": 102400,
    "contentType": "image/jpeg",
    "publicUrl": "https://oss-beijing-m8.openstorage.cn/your-bucket/uploads/2026/07/19/abc123def456.jpg",
    "presignedUrl": "https://oss-beijing-m8.openstorage.cn/your-bucket/uploads/2026/07/19/abc123def456.jpg?..."
  }
}
```

**curl 示例：**
```bash
curl -X POST http://localhost:8080/upload \
  -F "file=@/path/to/your/file.jpg"
```

#### 2.2 上传图片（带验证）

**接口：** `POST /upload/image`

**参数：**
- `file`: 图片文件
- `prefix`: 路径前缀（可选，默认 "images"）

**响应示例：**
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "key": "images/2026/07/19/def456abc789.png",
    "fileName": "avatar.png",
    "publicUrl": "https://...",
    "presignedUrl": "https://..."
  }
}
```

**curl 示例：**
```bash
curl -X POST "http://localhost:8080/upload/image?prefix=avatars" \
  -F "file=@/path/to/image.png"
```

#### 2.3 下载文件

**接口：** `GET /upload/download`

**参数：**
- `key`: S3 文件 key

**curl 示例：**
```bash
curl -X GET "http://localhost:8080/upload/download?key=uploads/2026/07/19/abc123.jpg" \
  --output downloaded_file.jpg
```

#### 2.4 删除文件

**接口：** `DELETE /upload`

**参数：**
- `key`: S3 文件 key

**响应示例：**
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

**curl 示例：**
```bash
curl -X DELETE "http://localhost:8080/upload?key=uploads/2026/07/19/abc123.jpg"
```

#### 2.5 获取文件访问 URL

**接口：** `GET /upload/url`

**参数：**
- `key`: S3 文件 key
- `expireTime`: 过期时间（分钟，可选，默认 10080 = 7天）

**响应示例：**
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "key": "uploads/2026/07/19/abc123.jpg",
    "publicUrl": "https://...",
    "presignedUrl": "https://...",
    "expireMinutes": "60"
  }
}
```

**curl 示例：**
```bash
curl -X GET "http://localhost:8080/upload/url?key=uploads/2026/07/19/abc123.jpg&expireTime=60"
```

## 文件存储路径规则

上传的文件会按以下规则存储：

```
{prefix}/{yyyy}/{MM}/{dd}/{uuid}.{ext}
```

**示例：**
- 前缀: `uploads`
- 日期: `2026/07/19`
- UUID: `abc123def456`
- 扩展名: `.jpg`
- 完整路径: `uploads/2026/07/19/abc123def456.jpg`

## 核心类说明

### 1. S3Properties
配置属性类，读取 `application.yaml` 中的 S3 配置。

### 2. S3Config
S3 客户端配置类，创建 `AmazonS3` Bean。

### 3. S3Service
S3 服务类，提供文件上传、下载、删除等核心功能。

**主要方法：**
- `uploadFile(MultipartFile, String)`: 上传文件
- `downloadFile(String)`: 下载文件
- `deleteFile(String)`: 删除文件
- `fileExists(String)`: 判断文件是否存在
- `generatePresignedUrl(String)`: 生成预签名 URL
- `getPublicUrl(String)`: 获取公共访问 URL

### 4. FileUploadController
文件上传控制器，提供 RESTful API。

## 前端集成示例

### HTML + JavaScript

```html
<!DOCTYPE html>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <input type="file" id="fileInput" />
    <button onclick="uploadFile()">上传</button>
    <div id="result"></div>

    <script>
        async function uploadFile() {
            const fileInput = document.getElementById('fileInput');
            const file = fileInput.files[0];
            
            if (!file) {
                alert('请选择文件');
                return;
            }

            const formData = new FormData();
            formData.append('file', file);

            try {
                const response = await fetch('http://localhost:8080/upload', {
                    method: 'POST',
                    body: formData
                });

                const result = await response.json();
                
                if (result.code === 1) {
                    document.getElementById('result').innerHTML = 
                        `<p>上传成功！</p>
                         <p>文件 Key: ${result.data.key}</p>
                         <p>访问地址: <a href="${result.data.publicUrl}" target="_blank">${result.data.publicUrl}</a></p>`;
                } else {
                    alert('上传失败: ' + result.msg);
                }
            } catch (error) {
                alert('上传出错: ' + error.message);
            }
        }
    </script>
</body>
</html>
```

## 常见问题

### 1. 连接 S3 失败

**问题：** 启动时报错 "Unable to execute HTTP request"

**解决方案：**
- 检查 `aws.s3.url` 是否正确
- 检查网络是否可以访问 S3 服务
- 确认 `access-key` 和 `secret-key` 是否正确

### 2. 上传后无法访问

**问题：** 文件上传成功但访问 URL 返回 403

**解决方案：**
- 检查桶的访问权限设置
- 确认是否设置了正确的 ACL（CannedAccessControlList.PublicReadWrite）
- 如果需要私有访问，使用预签名 URL

### 3. 文件大小限制

**默认限制：** Spring Boot 默认限制文件大小为 1MB

**解决方案：** 在 `application.yaml` 中配置：

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 10MB      # 单个文件最大大小
      max-request-size: 50MB   # 整个请求最大大小
```

## 安全建议

1. **不要将密钥提交到版本控制系统**
   - 将 `application-s3.yaml` 添加到 `.gitignore`
   - 使用环境变量或密钥管理服务

2. **使用预签名 URL**
   - 对于私有文件，使用预签名 URL 而不是公共 URL
   - 设置合理的过期时间

3. **文件类型验证**
   - 在上传前验证文件类型和大小
   - 防止恶意文件上传

4. **访问控制**
   - 根据业务需求设置合适的 ACL
   - 考虑添加用户认证和授权

## 参考资料

- [AWS SDK for Java 文档](https://docs.aws.amazon.com/sdk-for-java/)
- [Amazon S3 开发指南](https://docs.aws.amazon.com/s3/)
