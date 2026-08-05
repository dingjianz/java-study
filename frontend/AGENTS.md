# AGENTS.md

本文件为 Codex (Codex.ai/code) 、claude code提供在此仓库中工作的指导。

## 常用命令

```bash
# 开发
npm run dev          # 启动开发服务器（端口 8080）

# 构建
npm run build-dev    # 开发环境构建
npm run build-preview # 预览环境构建
npm run build-prod   # 生产环境构建（移除 source map）

# 代码质量
npm run lint         # ESLint 检查（仅在需要全量检查时运行）
npm run preview      # 本地预览生产构建
```

## ACTIVE_TODO.md 任务管理规则

### 会话开始时
1. **读取** `ACTIVE_TODO.md`，了解项目当前任务状态
2. **确认**用户本次要处理的任务是否已在文件中
3. 如果是新任务，询问用户任务的优先级和负责人

### 会话进行中
1. **更新任务状态**：开始任务时标记为 `[~]`，完成后标记为 `[x]`
2. **记录相关文件**：在任务下方添加修改的文件链接
3. **遇到阻塞时**：标记为 `[!]` 并在"阻塞项"区域说明原因

### 会话结束前
1. **更新 ACTIVE_TODO.md**：
   - 将本次完成的任务移至"已完成"区域
   - 更新进行中任务的进度说明
   - 添加发现的新问题到待办或技术债务区域
2. **更新时间戳**：修改文件底部的"最后更新"时间
3. **提交说明**：向用户总结本次任务进展

### 示例任务格式
```markdown
- [~] 修复登录页面表单验证 @jianding9 #P0 (2026-08-10)
  - [x] 添加邮箱格式校验
  - [ ] 添加密码强度提示
  - 相关文件: [src/pages/login/index.tsx](src/pages/login/index.tsx)
```

## 代码检查规则

每次会话结束前，**不要**默认运行 renderer build 或 `npm run build-*` 来检查错误。只有在用户明确要求、修改了构建配置/依赖/环境变量，或本次改动明显可能影响整体构建产物时，才运行对应构建命令。

每次会话结束前，**不要**运行 `npm run lint` 全量检查。只需对当前会话中修改过、且 ESLint 支持的文件执行增量 lint；文档、图片等不适用 ESLint 的文件无需加入命令：

```bash
npx eslint path/to/changed-file1.ts path/to/changed-file2.tsx
```

如果需要确认本次改动文件，可先用 `git diff --name-only` / `git diff --cached --name-only` 查看，再只挑选本次会话修改的 `.js`、`.jsx`、`.ts`、`.tsx` 等文件传给 `npx eslint`。

### 上下文管理

**保护上下文窗口，像保护内存一样。**

- 用 subagent 做大范围代码探索，结果以摘要回传
- 约束文件读取范围，禁止无目的全局搜索
- 上下文接近极限时主动压缩

## Git 规范

```text
feat: 新功能
fix: 修复 bug
refactor: 重构（不改变行为）
perf: 性能优化
test: 测试
docs: 文档
style: 格式调整（不影响逻辑）
chore: 构建/工具/依赖
```

- **所有 commit message 必须使用中文**，包括类型标注后的描述部分，严禁使用英文描述，除非特有关键词
- 推送到新分支，不直接推 main/master
- 不标注 Co-Authored-By
- **禁止在会话开始时自动执行 git commit**。只有在用户明确要求时才可以提交代码。

## 架构概览

### 技术栈
- **React 18 + TypeScript 5**，构建工具为 Vite 7
- **路由**：React Router v6，页面懒加载（`src/pages/index.tsx`）
- **状态管理**：大部分 store 使用 Zustand + Immer；`src/store/index.tsx` 中有 Context/reducer 模式
- **UI**：Ant Design 5 + Framer Motion + SCSS
- **编辑器**：TipTap 3（富文本）、Konva（画布/图片编辑）
- **HTTP**：Axios（`src/api/`），流式 AI 响应使用 `@microsoft/fetch-event-source`

### 入口文件
- `src/main.tsx` — React 根节点，Ant Design `ConfigProvider`，Sentry 初始化，polyfills
- `src/pages/App.tsx` — `BrowserRouter`，GrowthBook A/B 测试，鉴权处理，埋点（IFlyCollector、CBG Account SDK）
- `src/pages/index.tsx` — 所有路由定义（懒加载页面 + 弹窗路由）

### API 层（`src/api/`）
基于 Axios，按业务域拆分模块：`auth`、`creation`、`accountManager`、`agent`、`imageEditor`、`home`、`business`、`integral` 等。

开发代理（配置在 `vite.config.ts`）：
- `/api` → `https://fetdev.iflysec.com/api`
- `/publish-api` → `https://fetdev.iflysec.com/publish-api`
- `/security` → `https://oss-beijing-m8.openstorage.cn`

### 状态 Store（`src/store/`）
| Store | 用途 |
|---|---|
| `homepageStore` | 首页 UI 状态（弹窗、通知、奖励） |
| `editorStore` | 富文本编辑器状态 |
| `businessStore` | VIP 信息、积分 |
| `userInfoStore` | 用户追踪数据 |
| `networkStore` | IP、网络连通性 |
| `agentEditorStore` / `konvaStore` / `editorConfigStore` | 专项编辑器状态 |

### 路径别名（来自 `tsconfig.json`）
`src/*`、`store/*`、`api/*`、`pages/*`、`components/*`、`assets/*`、`utils/*`、`config/*`、`env/*`、`@helo/editor-verse`

### 构建分包
Vite 将输出拆分为命名 chunk：`react-vendor`、`antd-vendor`、`tiptap-vendor`、`utils-vendor`、`animation-vendor`、`editor-vendor`、`ui-vendor`、`state-vendor`、`file-vendor`、`crypto-vendor`、`other-vendor`。新增大型依赖时，需在 `vite.config.ts` 中归入合适的 chunk 或新增分包规则。

## SCSS 编写规范

写 SCSS 文件时，**必须使用嵌套语法**，能用 Sass 语法的地方都使用 Sass 语法。

- 选择器嵌套：使用 `&` 引用父选择器，避免重复书写完整选择器链。
- 伪类、伪元素、状态：通过 `&:hover`、`&:focus`、`&.is-active` 嵌套书写。
- 媒体查询：使用嵌套 `@media`、`@include` mixin，而非平铺。
- 变量、`@mixin`、`@include`、`@extend`、`@use`/`@forward`：优先使用 Sass 提供的能力，而不是手写重复代码。
- 颜色、间距、字号等可复用值：抽成变量或使用现有 design token。

示例：

```scss
.card {
  padding: 16px;

  &__title {
    font-size: 16px;
    font-weight: 600;

    &:hover {
      color: $primary-color;
    }
  }

  &.is-active {
    border-color: $primary-color;
  }

  @media (max-width: 768px) {
    padding: 12px;
  }
}
```
