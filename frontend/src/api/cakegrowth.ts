// CakeGrowth API 接口
import axios from 'axios';
import {
  generateSignature,
  generateNonce,
  getTimestamp,
  ACCESS_KEY,
  API_BASE_URL,
} from '@/utils/cakegrowth';

interface RegisterParams {
  isvUserId: string;      // 广告主系统用户唯一标识（如用户 ID、邮箱）
  visitorId: string;      // 从 window.CakeGrowth.visitorId 获取
  isvUserName: string;    // 用户名称
  registerAt: string;     // 注册时间（UTC ISO 格式）
  loginAt?: string;       // 登录时间（可选，注册后立即登录时传）
}

// 上报用户注册
export async function reportRegister(params: RegisterParams) {
  const path = '/v1/open/visitor/register';
  const method = 'POST';
  const timestamp = getTimestamp();
  const nonce = generateNonce();

  // 构造请求体
  const body = {
    isvUserId: params.isvUserId,
    pushedAt: new Date().toISOString(), // 当前时间 UTC
    visitorId: params.visitorId,
    isvUserName: params.isvUserName,
    registerAt: params.registerAt,
    ...(params.loginAt && { loginAt: params.loginAt }),
  };

  // 生成签名
  const signature = generateSignature({
    method,
    path,
    body,
    timestamp,
    nonce,
  });

  console.log('📤 CakeGrowth 注册上报请求:');
  console.log('- Path:', path);
  console.log('- Timestamp:', timestamp);
  console.log('- Nonce:', nonce);
  console.log('- Body:', body);
  console.log('- Signature:', signature);

  // 发送请求
  try {
    const response = await axios.post(`${API_BASE_URL}${path}`, body, {
      headers: {
        'Content-Type': 'application/json',
        'X-ACCESS-KEY': ACCESS_KEY,
        'X-Timestamp': timestamp,
        'X-Nonce': nonce,
        'X-Signature': signature,
      },
    });

    console.log('✅ CakeGrowth 注册上报成功:', response.data);
    return response.data;
  } catch (error: any) {
    console.error('❌ CakeGrowth 注册上报失败:', error.response?.data || error.message);
    throw error;
  }
}
