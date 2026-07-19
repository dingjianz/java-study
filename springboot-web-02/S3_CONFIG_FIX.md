# S3 配置错误修复指南

## ❌ 错误原因

```
Error Code: InvalidAccessKeyId
```

这个错误表示 S3 的访问密钥配置无效。当前 `application.yaml` 中使用的是占位符，不是真实的 S3 凭证。

## ✅ 解决方案

### 方案 1：使用真实的 S3 服务（推荐）

如果你有 S3 服务（阿里云 OSS、腾讯云 COS、AWS S3 等），请按以下步骤配置：

#### 1. 获取 S3 凭证

登录你的 S3 服务控制台，获取以下信息：
- **Access Key**（访问密钥 ID）
- **Secret Key**（访问密钥密码）
- **Bucket Name**（桶名称）
- **Endpoint URL**（服务地址）
- **Region**（区域）

#### 2. 更新配置文件

编辑 `springboot-web-02/src/main/resources/application.yaml`：

```yaml
aws:
  s3:
    url: your-s3-endpoint.com           # 例如：oss-cn-beijing.aliyuncs.com
    access-key: AKIAIOSFODNN7EXAMPLE     # 替换为你的 Access Key
    secret-key: wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY  # 替换为你的 Secret Key
    bucket: my-bucket                   # 替换为你的桶名称
    max-connections: 200
    region: cn-beijing                  # 替换为你的区域
```

#### 3. 重启后端服务

```bash
cd springboot-web-02
mvn spring-boot:run
```

---

### 方案 2：使用 MinIO 本地对象存储（开发测试）

如果只是用于学习和测试，可以使用 MinIO 搭建本地 S3 兼容存储。

#### 1. 安装 MinIO

**使用 Docker（推荐）：**
```bash
docker run -d \
  -p 9000:9000 \
  -p 9001:9001 \
  --name minio \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin" \
  -v ~/minio/data:/data \
  minio/minio server /data --console-address ":9001"
```

**使用 Homebrew（macOS）：**
```bash
brew install minio/stable/minio
minio server ~/minio/data
```

#### 2. 访问 MinIO 控制台

打开浏览器访问：`http://localhost:9001`

- **用户名：** `minioadmin`
- **密码：** `minioadmin`

#### 3. 创建 Bucket

1. 登录后点击 "Buckets" → "Create Bucket"
2. 输入名称：`test-bucket`
3. 点击 "Create Bucket"

#### 4. 创建 Access Key

1. 点击左侧 "Access Keys"
2. 点击 "Create access key"
3. 记录生成的 Access Key 和 Secret Key

#### 5. 配置 application.yaml

```yaml
aws:
  s3:
    url: http://localhost:9000           # MinIO 服务地址
    access-key: minioadmin               # 或使用创建的 Access Key
    secret-key: minioadmin               # 或使用创建的 Secret Key
    bucket: test-bucket                  # 创建的桶名称
    max-connections: 200
    region: us-east-1                    # MinIO 默认区域
```

#### 6. 重启后端服务

```bash
cd springboot-web-02
mvn spring-boot:run
```

---

### 方案 3：禁用 S3 功能（临时）

如果暂时不需要 S3 功能，可以临时禁用相关配置。

#### 1. 注释掉 S3Config

编辑 `src/main/java/com/itheima/config/S3Config.java`：

```java
//@Configuration  // 注释掉这一行
@RequiredArgsConstructor
public class S3Config {
    // ...
}
```

#### 2. 注释掉 S3Properties

编辑 `src/main/java/com/itheima/config/S3Properties.java`：

```java
@Data
//@Component  // 注释掉这一行
//@ConfigurationProperties(prefix = "aws.s3")  // 注释掉这一行
public class S3Properties {
    // ...
}
```

#### 3. 前端使用模拟上传

编辑 `frontend/src/components/dept/EmployeeFormModal.tsx`，在 `AvatarUploader` 组件中：

```typescript
const handleFile = async (e: React.ChangeEvent<HTMLInputElement>) => {
  const file = e.target.files?.[0]
  if (!file) return

  // 验证...

  try {
    setUploading(true)
    
    // 临时使用本地预览 URL
    const imageUrl = URL.createObjectURL(file)
    onChange(imageUrl)
    
    toast.success("头像上传成功（使用本地预览）")
  } catch (error) {
    toast.error("头像上传失败")
  } finally {
    setUploading(false)
  }
}
```

---

## 🎯 推荐使用 MinIO（方案 2）

对于学习和开发，我推荐使用 **MinIO**：

### 优点
- ✅ 完全兼容 S3 API
- ✅ 本地运行，无需注册账号
- ✅ 免费使用
- ✅ 图形化管理界面
- ✅ 快速启动（Docker 一条命令）

### MinIO 快速启动脚本

创建文件 `start-minio.sh`：

```bash
#!/bin/bash

echo "启动 MinIO 服务..."

docker run -d \
  -p 9000:9000 \
  -p 9001:9001 \
  --name minio-dev \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin123" \
  -v ~/minio-data:/data \
  minio/minio server /data --console-address ":9001"

echo "MinIO 已启动！"
echo "API 地址: http://localhost:9000"
echo "控制台: http://localhost:9001"
echo "用户名: minioadmin"
echo "密码: minioadmin123"
```

运行：
```bash
chmod +x start-minio.sh
./start-minio.sh
```

### 配置文件（使用 MinIO）

`application.yaml`：

```yaml
aws:
  s3:
    url: http://localhost:9000
    access-key: minioadmin
    secret-key: minioadmin123
    bucket: avatars
    max-connections: 200
    region: us-east-1
```

**记得在 MinIO 控制台创建名为 `avatars` 的 Bucket！**

---

## 📝 配置检查清单

在启动应用前，请确认：

- [ ] S3 服务已启动（真实 S3 或 MinIO）
- [ ] Access Key 和 Secret Key 正确
- [ ] Bucket 已创建
- [ ] Bucket 权限设置为公共读（或配置了正确的 ACL）
- [ ] 网络可以访问 S3 服务地址
- [ ] `application.yaml` 中的配置已更新

---

## 🐛 常见问题

### 1. InvalidAccessKeyId
**原因：** Access Key 错误或不存在  
**解决：** 检查 Access Key 是否正确

### 2. SignatureDoesNotMatch
**原因：** Secret Key 错误  
**解决：** 检查 Secret Key 是否正确

### 3. NoSuchBucket
**原因：** Bucket 不存在  
**解决：** 在 S3 控制台创建 Bucket

### 4. AccessDenied
**原因：** 权限不足  
**解决：** 检查 Bucket 权限和 IAM 策略

### 5. 连接超时
**原因：** 网络无法访问 S3 服务  
**解决：** 检查防火墙和网络配置

---

## 📚 相关文档

- [MinIO 官方文档](https://min.io/docs/)
- [阿里云 OSS](https://help.aliyun.com/product/31815.html)
- [腾讯云 COS](https://cloud.tencent.com/product/cos)
- [AWS S3](https://aws.amazon.com/s3/)

---

**创建时间：** 2026-07-19  
**问题：** InvalidAccessKeyId  
**解决方案：** 使用真实 S3 凭证或 MinIO
