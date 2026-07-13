#!/bin/bash
# Claude Lint Hook - 项目级测试脚本

echo "🧪 测试 Claude Lint Hook (项目级)"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

PROJECT_ROOT="/Users/lijiaqi/Documents/Java学习/my-project"
HOOK_SCRIPT="$PROJECT_ROOT/.claude/hooks/lint-on-stop.js"
SETTINGS_FILE="$PROJECT_ROOT/.claude/settings.json"

# 检查脚本是否存在
if [ ! -f "$HOOK_SCRIPT" ]; then
  echo "❌ 错误: 找不到 lint-on-stop.js"
  exit 1
fi

echo "✅ Hook 脚本存在"

# 检查是否有可执行权限
if [ -x "$HOOK_SCRIPT" ]; then
  echo "✅ Hook 有可执行权限"
else
  echo "⚠️  Hook 没有可执行权限，正在添加..."
  chmod +x "$HOOK_SCRIPT"
fi

# 检查 settings.json 配置
if grep -q "lint-on-stop.js" "$SETTINGS_FILE"; then
  echo "✅ Hook 已在 settings.json 中配置"
else
  echo "❌ 错误: Hook 未在 settings.json 中配置"
  exit 1
fi

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "🚀 运行测试..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# 切换到项目根目录运行
cd "$PROJECT_ROOT"
node "$HOOK_SCRIPT"

exit_code=$?

echo ""
if [ $exit_code -eq 0 ]; then
  echo "✅ 测试通过！Hook 可以正常工作"
else
  echo "⚠️  Hook 发现了一些问题（这是正常的行为）"
fi

exit $exit_code
