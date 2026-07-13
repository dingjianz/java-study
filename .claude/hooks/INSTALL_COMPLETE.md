# ✅ Claude Lint Hook - 项目级配置完成

## 📦 安装位置

**项目级配置** - 仅对当前项目生效

```
my-project/
├── .claude/
│   ├── settings.json          # 项目级 Claude 配置（包含 hook 配置）
│   └── hooks/
│       ├── lint-on-stop.js    # 主 hook 脚本
│       ├── lint-config.json   # Hook 配置
│       ├── test-hook.sh       # 测试脚本
│       └── README.md          # 完整文档
```

## 🎯 功能特性

### ✅ 自动检查
- **Java 文件**: Maven 编译检查
- **TypeScript/JavaScript**: Lint 和编译检查
- **依赖关系**: 查找引用了修改文件的代码

### ✅ 智能跳过
- Maven 不可用时自动跳过
- 友好的错误提示
- 不影响其他检查的执行

### ✅ 项目级配置
- 仅在当前项目中生效
- 可以提交到 git 供团队使用
- 不影响其他项目

## 🚀 快速开始

### 自动运行
每次 Claude 会话结束时自动执行

### 手动测试
```bash
# 在项目根目录
bash .claude/hooks/test-hook.sh

# 或直接运行
node .claude/hooks/lint-on-stop.js
```

### 查看配置
```bash
cat .claude/hooks/lint-config.json
```

## ⚙️ 配置文件

### .claude/hooks/lint-config.json
```json
{
  "enableJavaLint": true,           // Java 编译检查
  "enableFrontendLint": true,       // 前端 lint 检查
  "enableDependencyCheck": true,    // 依赖关系分析
  "maxFilesToCheck": 50,            // 最大检查文件数
  "timeout": 30000,                 // 超时（毫秒）
  "skipPatterns": [                 // 跳过的文件
    "node_modules/",
    ".git/",
    "dist/",
    "build/",
    "target/"
  ]
}
```

### .claude/settings.json
```json
{
  "permissions": {
    "defaultMode": "bypassPermissions"
  },
  "hooks": {
    "Stop": [
      {
        "matcher": "*",
        "hooks": [
          {
            "type": "command",
            "command": "\"node\" \".claude/hooks/lint-on-stop.js\"",
            "timeout": 30
          }
        ]
      }
    ]
  }
}
```

## 📊 测试结果

✅ Hook 已成功测试
- 检测到 12 个修改的文件
- 正确识别了 5 个前端文件
- 依赖关系分析正常工作
- 智能跳过了不可用的检查

## 🔧 管理 Hook

### 临时禁用
编辑 `.claude/hooks/lint-config.json`:
```json
{
  "enableJavaLint": false,
  "enableFrontendLint": false,
  "enableDependencyCheck": false
}
```

### 永久删除
```bash
rm -rf .claude/hooks/
```
然后从 `.claude/settings.json` 中删除 `hooks` 配置。

### 更新 Hook
直接编辑 `.claude/hooks/lint-on-stop.js`

## 📝 输出示例

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔧 Claude Hook: 会话结束前代码检查
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📁 项目目录: /path/to/my-project

📝 发现 4 个修改的文件:
   - frontend/src/App.tsx
   - frontend/src/utils/http.ts
   - springboot-web-02/.../DeptService.java
   - springboot-web-02/.../DeptMapper.java

🔍 检查 2 个 Java 文件...
   使用 Maven 编译检查...
✅ Java 文件编译通过

🔍 检查 2 个前端文件...
   运行 lint 脚本...
✅ 前端文件 lint 通过

🔗 检查依赖关系...
   DeptMapper.java 被以下文件引用:
   - DeptServiceImpl.java

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ 检查完成，所有文件通过
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

## 🤝 团队协作

### 提交到 Git
```bash
git add .claude/hooks/
git add .claude/settings.json
git commit -m "feat: 添加 Claude lint hook"
```

### 团队成员使用
克隆仓库后，hook 自动生效，无需额外配置。

## 🛠️ 故障排查

### Hook 未运行
1. 确认在项目根目录
2. 检查 `.claude/settings.json` 配置
3. 运行 `bash .claude/hooks/test-hook.sh` 测试

### Maven 错误
Hook 会自动检测并跳过，不影响其他检查

### 前端 Lint 错误
确保 `frontend/package.json` 中有 `lint` 脚本或安装了 `oxlint`

## 📚 更多信息

- 完整文档: `.claude/hooks/README.md`
- 配置文件: `.claude/hooks/lint-config.json`
- 测试脚本: `.claude/hooks/test-hook.sh`

## ✨ 下一步

1. ✅ Hook 已在项目中配置并测试
2. ✅ 下次结束 Claude 会话时会自动运行
3. 💡 可以根据需要修改配置
4. 💡 提交到 git 让团队共享

---

**安装完成！** 🎉

Hook 将在每次 Claude 会话结束时自动检查你的代码。
