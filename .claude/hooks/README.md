# Claude Lint Hook - 项目级配置

在每次 Claude 会话结束前自动检查修改的文件及其依赖。

## 📍 位置

**项目级配置** - 仅对当前项目生效
- Hook 脚本: `.claude/hooks/lint-on-stop.js`
- 配置文件: `.claude/hooks/lint-config.json`
- Settings: `.claude/settings.json`

## 功能

- ✅ 自动检测 git 中修改的文件
- ✅ 对 Java 文件执行 Maven 编译检查
- ✅ 对前端文件（TypeScript/JavaScript）执行 lint 检查
- ✅ 查找引用了修改文件的其他代码
- ✅ 显示清晰的检查结果和错误信息

## 使用方法

### 自动运行
每次 Claude 会话结束时自动执行（仅在本项目中）

### 手动测试
```bash
# 在项目根目录运行
node .claude/hooks/lint-on-stop.js

# 或创建测试脚本
bash .claude/hooks/test-hook.sh
```

## 配置

编辑 `.claude/hooks/lint-config.json`:

```json
{
  "enableJavaLint": true,           // 启用 Java 检查
  "enableFrontendLint": true,       // 启用前端检查
  "enableDependencyCheck": true,    // 启用依赖关系检查
  "maxFilesToCheck": 50,            // 最多检查的文件数
  "timeout": 30000,                 // 超时时间（毫秒）
  "skipPatterns": [                 // 跳过的文件模式
    "node_modules/",
    ".git/",
    "dist/",
    "build/",
    "target/"
  ]
}
```

## 工作原理

### 1. 检测修改的文件
使用 `git status --porcelain` 获取所有修改、新增的文件。

### 2. Java 文件检查
- 查找项目中的 `pom.xml`
- 运行 `mvn compile -q` 进行编译检查
- 报告编译错误
- 如果 Maven 不可用，自动跳过

### 3. 前端文件检查
检查顺序：
1. 如果 `package.json` 中有 `lint` 脚本，使用它
2. 如果安装了 `oxlint`，使用 oxlint
3. 否则使用 `tsc --noEmit` 进行 TypeScript 编译检查

### 4. 依赖关系检查
- 对于 Java 文件，搜索 `import` 语句
- 对于前端文件，搜索 `import`/`require` 语句
- 显示哪些文件引用了修改的文件

## 输出示例

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔧 Claude Hook: 会话结束前代码检查
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📁 项目目录: /path/to/my-project

📝 发现 4 个修改的文件:
   - frontend/src/App.tsx
   - frontend/src/utils/helper.ts
   - springboot-web-02/src/.../DeptService.java
   - springboot-web-02/src/.../DeptMapper.java

🔍 检查 2 个 Java 文件...
   使用 Maven 编译检查...
✅ Java 文件编译通过

🔍 检查 2 个前端文件...
   运行 lint 脚本...
✅ 前端文件 lint 通过

🔗 检查依赖关系...
   DeptMapper.java 被以下文件引用:
   - DeptServiceImpl.java

   建议检查这 1 个依赖文件

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ 检查完成，所有文件通过
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

## 启用/禁用

### 临时禁用
编辑 `.claude/hooks/lint-config.json`，将所有开关设为 `false`：
```json
{
  "enableJavaLint": false,
  "enableFrontendLint": false,
  "enableDependencyCheck": false
}
```

### 永久移除
编辑 `.claude/settings.json`，删除 `hooks.Stop` 配置。

## 故障排查

### Hook 没有运行
1. 检查 `.claude/settings.json` 中是否正确配置
2. 确保在项目根目录运行
3. 手动运行测试：`node .claude/hooks/lint-on-stop.js`

### Maven 相关问题
- Hook 会自动检测 Maven 是否可用
- 不可用时会友好地跳过，不影响其他检查

### 超时错误
增加 `.claude/settings.json` 中的 `timeout` 值（单位：秒）

## 自定义

### 添加 Python 支持
编辑 `.claude/hooks/lint-on-stop.js`：

```javascript
function lintPythonFiles(files, projectRoot) {
  const pyFiles = files.filter(f => f.endsWith('.py'));
  if (pyFiles.length === 0) return { success: true };
  
  const result = runCommand(`python -m pylint ${pyFiles.join(' ')}`, projectRoot);
  // 处理结果...
}
```

在 `main()` 函数中调用新的 lint 函数。

### 修改依赖查找逻辑
在 `findDependencies` 函数中添加自定义搜索模式。

## 技术细节

- **语言**: Node.js
- **依赖**: 无（仅使用 Node.js 内置模块）
- **执行时机**: Claude 会话结束时（Stop hook）
- **超时**: 30 秒（可配置）
- **退出码**: 0 = 成功，1 = 发现错误
- **作用域**: 仅当前项目

## 与全局 Hook 的区别

| 特性 | 项目级 | 全局级 |
|------|--------|--------|
| 位置 | `.claude/hooks/` | `~/.claude/hooks/` |
| 作用域 | 仅当前项目 | 所有项目 |
| 配置文件 | `.claude/settings.json` | `~/.claude/settings.json` |
| 可提交到 git | ✅ 是 | ❌ 否 |
| 团队共享 | ✅ 可以 | ❌ 不可以 |

## Git 版本控制

建议将以下文件添加到 git：
```bash
git add .claude/hooks/
git add .claude/settings.json
```

这样团队成员也能使用相同的 hook 配置。

## 更新日志

### v1.0.0 (2026-07-13)
- ✅ 初始版本
- ✅ 从全局配置迁移到项目级配置
- ✅ Java 和前端文件检查
- ✅ 依赖关系分析
- ✅ 可配置选项
