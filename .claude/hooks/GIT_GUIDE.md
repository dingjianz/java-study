# Claude Hooks - Git 配置说明

## 建议的 .gitignore 配置

如果你想将 Claude hooks 提交到 git 供团队使用，**不需要**添加任何 .gitignore 规则。

当前配置：
- `.claude/hooks/` - 建议提交（团队共享）
- `.claude/settings.json` - 建议提交（项目配置）
- `.claude/settings.local.json` - 已在 gitignore（个人配置）

## 如果不想提交 hooks

在 `.gitignore` 中添加：

```gitignore
# Claude hooks (如果不想团队共享)
.claude/hooks/
```

但这样团队成员就需要手动配置。

## 推荐做法

✅ **提交 hooks** - 让整个团队受益
- 统一的代码检查标准
- 自动化的质量保障
- 减少代码审查时间

❌ **不提交 hooks** - 仅个人使用
- 每个人需要单独配置
- 检查标准不统一
- 失去自动化优势

## 当前状态

已创建的文件：
```
.claude/
├── settings.json          ✅ 建议提交
├── settings.local.json    ❌ 已在 gitignore
└── hooks/
    ├── lint-on-stop.js    ✅ 建议提交
    ├── lint-config.json   ✅ 建议提交
    ├── test-hook.sh       ✅ 建议提交
    ├── README.md          ✅ 建议提交
    └── INSTALL_COMPLETE.md ✅ 建议提交
```

## 提交命令

```bash
git add .claude/hooks/
git add .claude/settings.json
git commit -m "feat: 添加 Claude lint hook 用于自动代码检查"
```
