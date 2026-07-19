# S3 配置完成说明

## ✅ 配置已完成

已成功将 `digit-cloud-gc` 项目的 S3 凭证配置到 `springboot-web-02` 项目中。

## 📋 配置详情

### S3 服务信息

```yaml
aws:
  s3:
    url: oss-beijing-m8.openstorage.cn
    access-key: GL08C7Y3RZWAEWFDYT3V
    secret-key: 2fksKTc3NZeWEE1N6Nd3V1MW5uJVRGZVQVab55Ri
    bucket: cloud-gc
    max-connections: 200
    region: cn-north-1
```

- **S3 服务商：** 讯飞云对象存储
- **服务地址：** oss-beijing-m8.openstorage.cn
- **存储桶：** cloud-gc（与 digit-cloud-gc 项目共享）
- **区域：** cn-north-1

## 🚀 现在可以使用

### 1. 重启后端服务

```bash
cd springboot-web-02
mvn spring-boot:run
```

### 2. 测试上传功能

1. 访问前端：`http://localhost:8082/employees`
2. 点击「新增员工」或「编辑」
3. 上传头像图片
4. 应该能够成功上传到 S3

## 📂 文件存储路径

上传的头像会存储在：

```
cloud-gc/avatars/2026/07/19/uuid.jpg
  │       │       └─日期─┘  └─UUID─┘
  │       └─前缀（avatars）
  └─桶名称（与 digit-cloud-gc 共享）
```

**完整 URL 示例：**
```
https://oss-beijing-m8.openstorage.cn/cloud-gc/avatars/2026/07/19/abc123def456789.jpg
```

## ⚠️ 注意事项

### 1. 共享存储桶

- 该配置使用的是 `digit-cloud-gc` 项目的 S3 凭证
- 存储桶 `cloud-gc` 是共享的
- 为避免文件冲突，头像使用独立前缀 `avatars/`

### 2. 文件命名策略

- **digit-cloud-gc 项目：** 使用各种前缀（如 `wx/official-account/image`）
- **springboot-web-02 项目：** 使用 `avatars/` 前缀
- **日期分层：** `yyyy/MM/dd/` 目录结构
- **UUID 文件名：** 避免文件名冲突

### 3. 权限说明

- **ACL：** PublicReadWrite（公共读写）
- **访问方式：** 
  - 公共 URL（无需签名，永久有效）
  - 预签名 URL（带过期时间，默认 7 天）

## 🧪 测试建议

### 功能测试

```bash
# 1. 使用 curl 测试上传
curl -X POST http://localhost:8080/upload/image?prefix=avatars \
  -F "file=@test-avatar.jpg"

# 预期响应：
# {
#   "code": 1,
#   "msg": "success",
#   "data": {
#     "key": "avatars/2026/07/19/xxx.jpg",
#     "publicUrl": "https://oss-beijing-m8.openstorage.cn/cloud-gc/avatars/2026/07/19/xxx.jpg",
#     ...
#   }
# }
```

### 验证上传成功

1. 复制返回的 `publicUrl`
2. 在浏览器中打开该 URL
3. 应该能看到上传的图片

## 🔧 故障排查

### 如果还是出现 InvalidAccessKeyId

**可能原因：**
1. 配置文件没有保存
2. 后端服务没有重启
3. 网络无法访问 S3 服务

**解决步骤：**
1. 确认 `application.yaml` 已保存
2. 停止后端服务（Ctrl+C）
3. 重新启动：`mvn spring-boot:run`
4. 查看启动日志，确认没有配置错误

### 如果上传成功但图片无法访问

**可能原因：**
1. 桶的访问权限问题
2. URL 格式错误
3. 网络防火墙阻止

**解决步骤：**
1. 检查返回的 `publicUrl` 是否正确
2. 尝试使用 `presignedUrl`（带签名）
3. 检查浏览器控制台是否有 CORS 错误

## 📊 存储使用情况

由于与 `digit-cloud-gc` 项目共享存储桶，建议：

### 定期清理测试文件

```bash
# 可以通过 API 删除不需要的测试文件
curl -X DELETE "http://localhost:8080/upload?key=avatars/2026/07/19/test.jpg"
```

### 监控存储空间

- 登录讯飞云对象存储控制台
- 查看 `cloud-gc` 桶的使用情况
- 必要时清理旧文件

## 🎯 后续建议

### 生产环境部署

如果要部署到生产环境，建议：

1. **独立存储桶**
   - 创建专用的存储桶（如 `springboot-web-02-prod`）
   - 不与其他项目共享

2. **环境变量配置**
   ```bash
   export AWS_ACCESS_KEY=xxx
   export AWS_SECRET_KEY=xxx
   export AWS_BUCKET=xxx
   ```

3. **凭证安全**
   - 不要将真实凭证提交到 Git
   - 使用密钥管理服务
   - 定期轮换密钥

4. **监控和告警**
   - 监控上传失败率
   - 设置存储空间告警
   - 记录访问日志

## ✅ 配置验证清单

使用前请确认：

- [x] S3 凭证已配置到 `application.yaml`
- [ ] 后端服务已重启
- [ ] 前端服务已启动
- [ ] 能够访问 `http://localhost:8080`
- [ ] 能够访问 `http://localhost:8082`
- [ ] 测试上传功能正常

---

**配置时间：** 2026-07-19  
**配置来源：** digit-cloud-gc 项目  
**状态：** ✅ 已配置，待测试
