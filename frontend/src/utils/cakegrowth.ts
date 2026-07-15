// CakeGrowth SDK 工具函数
import CryptoJS from 'crypto-js';

const ACCESS_KEY = '9povk4nj8y';
const ACCESS_SECRET = 's08XOnfNWYNZFnbhOJ57iB1ibPfh64d9M74fomga7pc7Pc0M';
const API_BASE_URL = 'https://api.cakegrowth.cn';

interface SignatureParams {
  method: string;
  path: string;
  queryParams?: Record<string, string>;
  body: any;
  timestamp: string;
  nonce: string;
}

// 生成签名
function generateSignature({
  method,
  path,
  queryParams = {},
  body,
  timestamp,
  nonce,
}: SignatureParams): string {
  // 1. 合并认证参数
  const allParams: Record<string, string> = {
    ...queryParams,
    accessKey: ACCESS_KEY,
    timestamp,
    nonce,
  };

  // 2. 按 key 字典序排序并拼接
  const sortedKeys = Object.keys(allParams).sort();
  const queryString = sortedKeys
    .map(key => `${key}=${encodeURIComponent(allParams[key])}`)
    .join('&');

  // 3. 计算 body 的 SHA256
  const bodyStr = JSON.stringify(body);
  const bodyHash = CryptoJS.SHA256(bodyStr).toString(CryptoJS.enc.Hex);

  // 4. 构造待签名字符串
  const stringToSign = `${method}\n${path}\n${queryString}\n${bodyHash}`;

  // 5. HMAC-SHA256 签名
  const signature = CryptoJS.HmacSHA256(stringToSign, ACCESS_SECRET).toString(
    CryptoJS.enc.Hex
  );

  return signature;
}

// 生成随机 Nonce
function generateNonce(): string {
  return Math.random().toString(36).substring(2, 15) +
         Math.random().toString(36).substring(2, 15);
}

// 获取 UTC 时间戳（秒）
function getTimestamp(): string {
  return Math.floor(Date.now() / 1000).toString();
}

export { generateSignature, generateNonce, getTimestamp, ACCESS_KEY, API_BASE_URL };
