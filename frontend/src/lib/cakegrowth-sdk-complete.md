# CakeGrowth SDK 使用文档

> 本文档整理自飞书官方对接文档，提供完整的 JS SDK 和开放平台 API 接入指南。

---

## 📚 目录

- [一、JS SDK 对接](#一js-sdk-对接)
  - [1. 获取 API Key](#1-获取-api-key)
  - [2. 安装与验证](#2-安装与验证)
  - [3. 可用属性](#3-可用属性)
  - [4. 注意事项](#4-注意事项)
- [二、开放平台 API 对接](#二开放平台-api-对接)
  - [1. 前置条件](#1-前置条件)
  - [2. 通信规范](#2-通信规范)
  - [3. 认证机制](#3-认证机制)
  - [4. 接口列表](#4-接口列表)
  - [5. 常见问题](#5-常见问题)

---

## 一、JS SDK 对接

### 1. 获取 API Key

联系 CakeGrowth 技术支持，创建广告主账号，获取专属 API Key。

### 2. 安装与验证

#### 2.1 安装

SDK 只需要全局引入一次，自动初始化。

**中国站（国内推广）**

生产环境地址：`https://cakegrowth.cn/static/cg.min.js`

**React 项目安装**

在 `public/index.html` 中添加：

```html
<script
  async
  src="https://www.cakegrowth.cn/static/cg.min.js"
  data-cakegrowth="YOUR-API-KEY"
  data-env="prod"
></script>
```

**参数说明**

| 属性 | 必填 | 说明 |
|------|------|------|
| `data-cakegrowth` | 是 | 分配给您的 API Key |
| `data-env` | 否 | 环境标识，test 或 prod，不传默认 prod |
| `data-cookie-domain` | 否 | 自定义 Cookie 域名，跨子域时建议填写 |

#### 2.2 验证安装

**前提：** 联系 CakeGrowth 技术支持初始化项目，并添加测试域名（如 `http://localhost:8082`）

浏览器访问：

```
https://your-domain/?utm=cg&cgv=install
```

响应：

```json
{
  "installed": true
}
```

即为正确安装。

### 3. 可用属性

安装完成后，可通过 `window.CakeGrowth` 读取以下属性：

| 属性 | 说明 |
|------|------|
| `CakeGrowth.apiKey` | 当前加载的 API Key |
| `CakeGrowth.cgv` | 当前链接参数中的 cgv 标识 |
| `CakeGrowth.utm` | 当前渠道标识（固定为 `cg`） |
| `CakeGrowth.lastPage` | 最近一次 pageview 记录的页面信息 |
| `CakeGrowth.url` | 当前访问的分享链接 |
| **`CakeGrowth.visitorId`** | **当前访客 ID（注册时必须获取）** |
| `CakeGrowth.cookieInfo` | 当前访客的 Cookie 信息 |
| `CakeGrowth.linkInfo` | 当前访客对应的链接信息 |
| `CakeGrowth.projectInfo` | 当前访客对应的项目信息 |

**重点属性：**

- **`CakeGrowth.utm`**：渠道标识，固定为 `cg`，用于区分推广数据来自 CakeGrowth
- **`CakeGrowth.visitorId`**：当前访客ID，**注册时必须获取该属性**，通过 S2S 模式上报给 CakeGrowth

### 4. 注意事项

#### 4.1 域名限制

- 广告主在平台设置推广项目的基础链接（如 `https://your-project-url-1/`）
- JS 上报时，会限制请求来源的域名必须在基础链接列表中
- **本地调试**：需在平台添加本地域名（如 `http://localhost:3000/`）

#### 4.2 频率限制

每个 IP 最大每分钟 **60 次**请求。

#### 4.3 什么时候需要 `whenReady`

正常情况下，**不需要**每次调用前都写 `await window.CakeGrowth('whenReady')`。

**只有以下场景才需要显式等待：**

1. 页面刚加载完成，就要立刻读取 `window.CakeGrowth.visitorId`
2. SDK 初始化完成后，马上要执行第一条依赖访客状态的逻辑

**React / Next.js 示例：**

```typescript
useEffect(() => {
  async function loadSdkState() {
    await window.CakeGrowth('whenReady');
    console.log(window.CakeGrowth.visitorId);
    console.log(window.CakeGrowth.apiKey);
    console.log(window.CakeGrowth.utm);
  }

  loadSdkState().catch(console.error);
}, []);
```

> **说明：** Vue / Nuxt 对应的是 `onMounted`

#### 4.4 销毁 SDK

适用于微前端或特殊卸载场景：

```typescript
await window.CakeGrowth('destroy', {
  clearQueue: true,
});
```

#### 4.5 其它说明

- 所有时间请使用 **UTC ISO 格式**，例如 `2026-03-13T08:30:00.000Z`
- `userId` 必须在您系统内唯一且稳定
- 订单金额与退款金额单位均为**分**
- SDK 只需要全局引入一次

---

## 二、开放平台 API 对接

### 1. 前置条件

#### API 接口地址

**中国站（国内推广）**

```
https://api.cakegrowth.cn/v1/open
```

**国际站（国际推广）**

```
https://api.cakegrowth.com/v1/open
```

#### 广告主管理后台

**中国站（国内推广）**

```
https://dash.cakegrowth.cn/
```

**国际站（国际推广）**

```
https://dash.cakegrowth.com/
```

#### 必需信息

联系 CakeGrowth 技术支持，获取以下信息：

| 项目 | 必需 | 说明 |
|------|------|------|
| Access Key | 是 | 广告主标识，用于请求鉴权 |
| Access Secret | 是 | 广告主密钥，用于生成签名 |
| 出口 IP 白名单 | 否 | 如果设置白名单，仅允许从白名单发起请求 |

### 2. 通信规范

#### 2.1 请求规范

- **请求方法**：`POST`
- **Content-Type**：`application/json`
- **字符编码**：`UTF-8`

#### 2.2 响应规范

所有接口均返回统一结构：

```json
{
  "code": 0,
  "message": "ok",
  "data": {}
}
```

**字段说明：**

| 字段 | 类型 | 说明 |
|------|------|------|
| `code` | int | 业务状态码，0 表示成功 |
| `message` | string | 响应说明 |
| `data` | object | 接口返回数据 |

#### 2.3 调用结果判断

| 条件 | 说明 |
|------|------|
| HTTP 200 且 `code = 0` | 调用成功 |
| HTTP 403 | 鉴权失败、签名失败、时间戳无效、Nonce 重复或 IP 不在白名单 |
| HTTP 200 且 `code != 0` | 参数校验失败或业务校验失败 |

### 3. 认证机制

所有 API 请求均需携带以下请求头：

| Header | 必填 | 说明 |
|--------|------|------|
| `X-ACCESS-KEY` | 是 | 平台分配的访问标识 |
| `X-Timestamp` | 是 | 秒级 Unix 时间戳 |
| `X-Nonce` | 是 | 每次请求唯一的随机字符串 |
| `X-Signature` | 是 | 按签名规则生成的摘要 |

#### 3.1 签名规则

- **算法**：HMAC-SHA256
- **密钥**：Access Secret
- **输出格式**：十六进制小写字符串

#### 3.2 签名步骤

1. 合并查询参数和认证参数（`accessKey`、`timestamp`、`nonce`）
2. 按参数名字典序排序
3. 对参数值进行 URL 编码
4. 拼接为 `key1=value1&key2=value2` 格式
5. 计算请求体的 SHA256（十六进制小写）
6. 构造待签名字符串：`METHOD\nPATH\nQUERY_STRING\nBODY_HASH`
7. 使用 HMAC-SHA256 签名

**TypeScript 示例（已实现）：**

参见项目中的 `src/utils/cakegrowth.ts` 和 `src/api/cakegrowth.ts`

#### 3.3 频率限制

单个 IP 最大每分钟 **120 次**请求。

### 4. 接口列表

| 接口 | 路径 | 说明 |
|------|------|------|
| 访客追踪 | `POST /v1/open/visitor/track` | 用户访问推广链接时调用（纯 S2S 模式） |
| **访客注册** | `POST /v1/open/visitor/register` | **用户注册完成时调用（必须上报）** |
| 访客登录 | `POST /v1/open/visitor/login` | 用户登录时调用 |
| 访客下单 | `POST /v1/open/visitor/order` | 用户下单或支付完成时调用 |
| 访客退款 | `POST /v1/open/visitor/refund` | 订单退款时调用 |
| 产品使用 | `POST /v1/open/visitor/use` | 用户发生关键使用行为时调用 |
| 老用户召回 | `POST /v1/open/visitor/win-back` | 老用户回流或召回时调用 |
| 查询项目列表 | `POST /v1/open/project/list` | 查询当前广告主的所有项目 |
| 查询项目统计 | `POST /v1/open/project/statistic` | 根据项目 ID 查询每日统计 |


#### 4.1 访客追踪（纯 S2S 模式）

**请求路径：** `POST /v1/open/visitor/track`

**业务说明：**

仅纯 S2S 模式使用，广告主自行维护临时访客信息。其他模式不必接入。

当用户首次访问广告主推广链接（如 `https://yourdomain.com?utm=cg&cgv=link_token_field_value`），广告主自行获取 `cgv` 参数值，调用本接口上报平台。平台成功处理后会返回 `visitorId`。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `linkToken` | string | 是 | 推广链接 token（从 URL 的 cgv 参数获取） |
| `visitorId` | string | 否 | 平台访客 ID，首次访问不传 |
| `pushedAt` | string | 是 | 推送时间（UTC 时间） |

**请求示例：**

```json
{
  "linkToken": "abc123",
  "pushedAt": "2025-11-12T01:37:21.949Z"
}
```

**返回示例：**

```json
{
  "code": 0,
  "message": "ok",
  "data": {
    "visitorId": "v_xxxxx",
    "url": "https://example.com/landing",
    "becameLeadAt": "",
    "companyInfo": {},
    "projectInfo": {},
    "linkInfo": {},
    "cookieInfo": {
      "expiredAt": "2025-11-12T01:37:21.949Z",
      "domain": "xx.com",
      "confirmVisitorType": 1
    },
    "createdAt": "2025-11-12T01:37:21.949Z"
  }
}
```

**关键返回字段：**

| 字段 | 说明 |
|------|------|
| `visitorId` | 平台访客 ID，后续接口建议保存并传回 |
| `url` | 访问落地页地址 |
| `createdAt` | 访客创建时间 |

---

#### 4.2 用户注册（必须上报）

**请求路径：** `POST /v1/open/visitor/register`

**业务说明：**

当用户在广告主系统完成注册时调用，用于将广告主用户与平台临时访客绑定。

**这是除 track 外第一个必须上报的接口。**

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `isvUserId` | string | 是 | 广告主系统用户唯一标识（如邮箱、账号、用户ID等） |
| `pushedAt` | string | 是 | 推送时间（UTC 时间） |
| `visitorId` | string | 是 | 平台访客ID（从 JS SDK 获取，或从 track 获取） |
| `isvUserName` | string | 是 | 用户名称 |
| `registerAt` | string | 是 | 注册时间（UTC 时间） |
| `loginAt` | string | 否 | 登录时间（存在表示注册后立即登录） |

**请求示例：**

```json
{
  "isvUserId": "user_10001",
  "pushedAt": "2025-11-12T01:37:21.949Z",
  "visitorId": "v_xxxxx",
  "isvUserName": "tom",
  "registerAt": "2025-11-12T01:30:00.000Z",
  "loginAt": "2025-11-12T01:37:21.949Z"
}
```

**⚠️ 重要：注册成功后，如果使用 JS + S2S 模式对接，需要前端调用：**

```typescript
await window.CakeGrowth('pageview', { refreshVisitor: true });
```

---

#### 4.3 用户登录

**请求路径：** `POST /v1/open/visitor/login`

**业务说明：**

当用户在广告主系统登录时调用，用于同步登录行为。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `isvUserId` | string | 是 | 广告主系统用户唯一标识 |
| `pushedAt` | string | 是 | 推送时间（UTC 时间） |
| `isvUserName` | string | 是 | 用户名称 |
| `visitorId` | string | 否 | 平台访客 ID（如果广告主下有多个产品，产品的用户体系不互通，必传） |
| `registerAt` | string | 是 | 注册时间（UTC 时间） |
| `loginAt` | string | 是 | 本次登录时间（UTC 时间） |
| `lastLoginAt` | string | 是 | 上次登录时间（首次登录时，与 loginAt 相同） |

**请求示例：**

```json
{
  "isvUserId": "user_10001",
  "pushedAt": "2025-11-12T01:37:21.949Z",
  "isvUserName": "tom",
  "visitorId": "v_xxxxx",
  "registerAt": "2025-11-01T08:00:00.000Z",
  "loginAt": "2025-11-12T01:37:21.949Z",
  "lastLoginAt": "2025-11-10T03:20:00.000Z"
}
```

---

#### 4.4 用户下单

**请求路径：** `POST /v1/open/visitor/order`

**业务说明：**

当用户完成下单或支付状态发生变化时调用。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `isvUserId` | string | 是 | 广告主系统用户唯一标识 |
| `pushedAt` | string | 是 | 推送时间（UTC 时间） |
| `orderNo` | string | 是 | 订单号 |
| `description` | string | 是 | 订单描述 |
| `chargeAmount` | uint | 是 | 成交金额，**单位分** |
| `currency` | string | 是 | 币种（人民币：CNY；美元：USD） |
| `status` | uint | 是 | 订单状态：0=待支付、1=支付中、2=支付成功、3=支付失败或过期 |
| `provider` | string | 否 | 支付渠道（如 stripe、alipay、paypal、wechat） |
| `chargedAt` | string | 是 | 成交时间（UTC 时间） |
| `period` | string | 否 | 续费周期（monthly 或 yearly） |
| `visitorId` | string | 否 | 平台访客 ID（多产品体系不互通时必传） |

**请求示例：**

```json
{
  "isvUserId": "user_10001",
  "pushedAt": "2025-11-12T01:37:21.949Z",
  "orderNo": "order_202511120001",
  "description": "Pro Monthly",
  "chargeAmount": 9900,
  "currency": "USD",
  "status": 2,
  "provider": "stripe",
  "chargedAt": "2025-11-12T01:37:21.949Z",
  "period": "monthly"
}
```

---

#### 4.5 用户退款

**请求路径：** `POST /v1/open/visitor/refund`

**业务说明：**

当订单发生退款时调用。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `isvUserId` | string | 是 | 广告主系统用户唯一标识 |
| `pushedAt` | string | 是 | 推送时间（UTC 时间） |
| `orderNo` | string | 是 | 订单号 |
| `description` | string | 是 | 订单描述 |
| `status` | uint | 是 | 退款状态：4=退款中、5=退款成功、6=退款失败 |
| `refundAmount` | uint | 是 | 累计退款金额，**单位分** |
| `refundAt` | string | 是 | 本次退款时间（UTC 时间） |
| `visitorId` | string | 否 | 平台访客 ID（多产品体系不互通时必传） |

**请求示例：**

```json
{
  "isvUserId": "user_10001",
  "pushedAt": "2025-11-15T02:10:11.000Z",
  "orderNo": "order_202511120001",
  "description": "Pro Monthly",
  "status": 5,
  "refundAmount": 9900,
  "refundAt": "2025-11-15T02:09:59.000Z"
}
```

---

#### 4.6 产品使用

**请求路径：** `POST /v1/open/visitor/use`

**业务说明：**

当用户发生关键产品使用行为时调用，例如完成关键功能使用、消耗产品资源或触发核心转化动作。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `isvUserId` | string | 是 | 广告主系统用户唯一标识 |
| `pushedAt` | string | 是 | 推送时间（UTC 时间） |
| `productId` | string | 是 | 产品 ID |
| `description` | string | 是 | 使用描述 |
| `usedAt` | string | 是 | 使用时间（UTC 时间） |
| `visitorId` | string | 否 | 平台访客 ID（多产品体系不互通时必传） |

**请求示例：**

```json
{
  "isvUserId": "user_10001",
  "pushedAt": "2025-11-12T01:37:21.949Z",
  "productId": "product_pro",
  "description": "User used report export",
  "usedAt": "2025-11-12T01:37:21.949Z"
}
```

---

#### 4.7 老用户召回

**请求路径：** `POST /v1/open/visitor/win-back`

**业务说明：**

当已流失用户重新活跃或通过召回活动返回时调用。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `isvUserId` | string | 是 | 广告主系统用户唯一标识 |
| `pushedAt` | string | 是 | 推送时间（UTC 时间） |
| `visitorId` | string | 是 | 平台访客 ID |
| `isvUserName` | string | 是 | 用户名称 |
| `registerAt` | string | 是 | 注册时间（UTC 时间） |
| `lastLoginAt` | string | 是 | 上次登录时间（UTC 时间） |
| `backAt` | string | 是 | 召回时间（UTC 时间） |

**请求示例：**

```json
{
  "isvUserId": "user_10001",
  "pushedAt": "2025-11-20T09:00:00.000Z",
  "visitorId": "v_xxxxx",
  "isvUserName": "tom",
  "registerAt": "2025-10-01T08:00:00.000Z",
  "lastLoginAt": "2025-10-15T08:00:00.000Z",
  "backAt": "2025-11-20T09:00:00.000Z"
}
```

---

#### 4.8 查询项目列表

**请求路径：** `POST /v1/open/project/list?lang=zh-CN`

**业务说明：**

查询当前广告主的所有项目。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `page` | uint | 否 | 当前页，默认 1 |
| `size` | uint | 否 | 每页大小，默认 20 |

**请求示例：**

```json
{
  "page": 1,
  "size": 20
}
```

**返回示例：**

```json
{
  "code": 0,
  "message": "ok",
  "data": {
    "total": 1,
    "page": 1,
    "size": 20,
    "list": [
      {
        "id": "1825ky3q7m",
        "productId": "1825ky3q7m",
        "name": "项目名称",
        "description": "项目描述",
        "currency": "CNY",
        "status": 1,
        "ruleScope": 2
      }
    ]
  }
}
```

**关键返回字段：**

| 字段 | 说明 |
|------|------|
| `id` | 项目 ID |
| `productId` | 产品 ID |
| `name` | 项目名称 |
| `description` | 项目描述 |
| `currency` | 结算币种（CNY=人民币；USD=美元） |
| `status` | 项目状态：0=待发布、1=已发布、2=私密、3=已到期、4=已作废 |
| `ruleScope` | 奖励范围（二进制表示，00 对应 CPA、CPS） |

---

#### 4.9 查询项目统计

**请求路径：** `POST /v1/open/project/statistic?lang=zh-CN`

**业务说明：**

根据项目 ID 查询项目的每日统计情况。

**请求参数：**

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `projectId` | string | 是 | 项目 ID |
| `dateFrom` | string | 是 | 统计开始时间（UTC 时间） |
| `dateTo` | string | 是 | 统计截止时间（UTC 时间） |
| `page` | uint | 否 | 当前页，默认 1 |
| `size` | uint | 否 | 每页大小，默认 20 |

**请求示例：**

```json
{
  "projectId": "1825ky3q7m",
  "dateFrom": "2025-11-12T00:00:00.000Z",
  "dateTo": "2025-11-12T00:00:00.000Z",
  "page": 1,
  "size": 20
}
```

**返回示例：**

```json
{
  "code": 0,
  "message": "ok",
  "data": {
    "total": 0,
    "page": 0,
    "size": 0,
    "project": {
      "id": "string",
      "productId": "string",
      "name": "string",
      "description": "string",
      "currency": "string",
      "status": 0,
      "ruleScope": 0
    },
    "list": [
      {
        "date": "string",
        "visitors": 0,
        "leads": 0,
        "effects": 0,
        "conversions": 0
      }
    ]
  }
}
```

**关键返回字段：**

| 字段 | 说明 |
|------|------|
| `date` | 统计周期，按天（如 20260526） |
| `visitors` | 临时访客数 |
| `leads` | 注册数 |
| `effects` | 有效用户数（CPA 项目才有，符合奖励规则的用户数） |
| `conversions` | 转化数（CPS 项目才有，符合奖励规则的用户数） |

---

### 5. 常见问题

#### 5.1 返回 HTTP 403

查看请求的 response 提示，优先检查：

- ✅ Access Key 是否正确
- ✅ 时间戳是否过期
- ✅ Nonce 是否重复使用
- ✅ 签名是否匹配
- ✅ 出口 IP 是否在白名单中

#### 5.2 签名不通过

重点检查：

- ✅ 参与签名的 JSON 与实际发送 JSON 是否完全一致
- ✅ query 参数是否按参数名升序排序
- ✅ 参数值是否完成 URL 编码
- ✅ 签名密钥是否使用 Access Secret
- ✅ 签名结果是否为十六进制小写

#### 5.3 时间字段校验失败

请确认：

- ✅ 时间是否已转为 UTC
- ✅ 是否使用 ISO8601 格式
- ✅ 是否包含 `Z` 后缀

#### 5.4 金额异常

请确认金额单位是否为**"分"**，且传值为整数。

#### 5.5 visitorId 无效

请确认该值是否来自 **JS SDK 生成** 或者 **track 接口返回**，而非广告主系统内部主键。

---

### 6. 日志与排查建议

为便于联调与生产排障，建议广告主记录以下信息：

- ✅ 请求路径
- ✅ `X-Timestamp`
- ✅ `X-Nonce`
- ✅ 原始 JSON 请求体
- ✅ body SHA256
- ✅ stringToSign
- ✅ `X-Signature`
- ✅ HTTP 状态码
- ✅ 响应 `code`
- ✅ 响应 `message`

---

## 📦 项目中的实现

本项目已完整实现 CakeGrowth SDK 对接功能，相关文件：

- **`src/utils/cakegrowth.ts`** - 签名工具函数（HMAC-SHA256）
- **`src/api/cakegrowth.ts`** - API 接口封装（reportRegister 等）
- **`src/pages/CakeGrowthTest.tsx`** - 测试页面
- **`src/types/cakegrowth.d.ts`** - TypeScript 类型声明
- **`CAKEGROWTH_SDK.md`** - 安装说明文档

**测试页面访问：** `http://localhost:8082/cakegrowth-test`

---

**文档来源：** CakeGrowth 官方飞书文档  
**最后更新：** 2026-07-15
