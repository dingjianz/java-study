import  { useState, useEffect } from 'react';
import { reportRegister } from '@/api/cakegrowth';
import { Card } from '@/components/ui/card';
import { Button } from '@/components/ui/button';

export default function CakeGrowthTest() {
  const [loading, setLoading] = useState(false);
  const [visitorId, setVisitorId] = useState<string>('');
  const [sdkReady, setSdkReady] = useState(false);
  const [result, setResult] = useState<any>(null);
  const [error, setError] = useState<string>('');
  const [needsUtmParam, setNeedsUtmParam] = useState(false);

  // 表单数据
  const [formData, setFormData] = useState({
    userId: '',
    username: '',
    email: '',
  });

  // 检查 SDK 状态
  useEffect(() => {
    // <script async> 可能尚未加载完成，轮询等待 window.CakeGrowth 就绪
    function waitForSdkLoaded(timeout = 10000): Promise<void> {
      return new Promise((resolve, reject) => {
        if (typeof window.CakeGrowth !== 'undefined') {
          resolve();
          return;
        }
        const start = Date.now();
        const timer = setInterval(() => {
          if (typeof window.CakeGrowth !== 'undefined') {
            clearInterval(timer);
            resolve();
          } else if (Date.now() - start > timeout) {
            clearInterval(timer);
            reject(new Error('CakeGrowth SDK 脚本加载超时，请检查 index.html 中的 SDK 脚本'));
          }
        }, 100);
      });
    }

    async function checkSDK() {
      try {
        // 1. 等待 SDK 脚本加载
        await waitForSdkLoaded();

        // 2. 等待 SDK 初始化完成
        await Promise.race([
          window.CakeGrowth('whenReady'),
          new Promise((_, reject) =>
            setTimeout(() => reject(new Error('SDK 初始化超时')), 10000)
          ),
        ]);

        // 3. 读取 visitorId —— 有值即就绪
        //    首次带 UTM 参数访问后 visitorId 会持久化到 cookie，后续访问无需 UTM 参数
        const vid = window.CakeGrowth.visitorId;

        console.log('🎯 CakeGrowth SDK 信息:');
        console.log('- API Key:', window.CakeGrowth.apiKey);
        console.log('- Visitor ID:', vid);
        console.log('- UTM:', window.CakeGrowth.utm);
        console.log('- URL:', window.CakeGrowth.url);

        if (vid) {
          setVisitorId(vid);
          setSdkReady(true);
          return;
        }

        // 4. whenReady 后仍无 visitorId：真正的首次访问且无 cookie，需带 UTM 参数初始化
        setSdkReady(false);
        setNeedsUtmParam(true);
        setError('SDK 已加载但未获取到 visitorId，请带 UTM 参数（?utm=cg&cgv=install）访问以初始化');
      } catch (err: any) {
        setSdkReady(false);
        setError(`SDK 初始化失败: ${err.message}`);
        console.error('CakeGrowth SDK 初始化错误:', err);
      }
    }

    checkSDK();
  }, []);

  // 生成测试数据
  const generateTestData = () => {
    const timestamp = Date.now();
    setFormData({
      userId: `test_user_${timestamp}`,
      username: `测试用户${timestamp.toString().slice(-4)}`,
      email: `test${timestamp}@example.com`,
    });
  };

  // 处理注册上报
  const handleRegister = async () => {
    if (!formData.userId || !formData.username) {
      setError('请填写用户 ID 和用户名');
      return;
    }

    if (!sdkReady || !visitorId) {
      setError('CakeGrowth SDK 未就绪或 visitorId 未获取');
      return;
    }

    setLoading(true);
    setError('');
    setResult(null);

    try {
      // 上报注册事件
      const response = await reportRegister({
        isvUserId: formData.userId,
        visitorId: visitorId,
        isvUserName: formData.username,
        registerAt: new Date().toISOString(),
        loginAt: new Date().toISOString(), // 假设注册后立即登录
      });

      setResult(response);

      // 刷新 visitorId
      if (typeof window.CakeGrowth !== 'undefined') {
        await window.CakeGrowth('pageview', { refreshVisitor: true });
        console.log('✅ visitorId 已刷新');
      }

    } catch (err: any) {
      setError(err.response?.data?.message || err.message || '上报失败');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-slate-50 p-8">
      <div className="max-w-4xl mx-auto space-y-6">
        {/* 页面标题 */}
        <div>
          <h1 className="text-3xl font-bold text-slate-900">CakeGrowth 注册上报测试</h1>
          <p className="text-slate-600 mt-2">测试用户注册事件上报功能</p>
        </div>

        {/* SDK 状态卡片 */}
        <Card className="p-6">
          <h2 className="text-xl font-semibold mb-4">SDK 状态</h2>
          <div className="space-y-2 text-sm">
            <div className="flex items-center gap-2">
              <span className="font-medium text-slate-700">状态:</span>
              <span className={`px-2 py-1 rounded ${sdkReady ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'}`}>
                {sdkReady ? '✅ 已就绪' : '❌ 未就绪'}
              </span>
            </div>
            <div className="flex items-center gap-2">
              <span className="font-medium text-slate-700">Visitor ID:</span>
              <code className="bg-slate-100 px-2 py-1 rounded text-xs">
                {visitorId || '未获取'}
              </code>
            </div>
            {typeof window.CakeGrowth !== 'undefined' && (
              <>
                <div className="flex items-center gap-2">
                  <span className="font-medium text-slate-700">API Key:</span>
                  <code className="bg-slate-100 px-2 py-1 rounded text-xs">
                    {window.CakeGrowth.apiKey || '未设置'}
                  </code>
                </div>
                <div className="flex items-center gap-2">
                  <span className="font-medium text-slate-700">UTM:</span>
                  <code className="bg-slate-100 px-2 py-1 rounded text-xs">
                    {window.CakeGrowth.utm || '未设置'}
                  </code>
                </div>
              </>
            )}
          </div>

          {/* UTM 参数提示 */}
          {needsUtmParam && (
            <div className="mt-4 p-3 bg-amber-50 border border-amber-200 rounded">
              <p className="text-sm text-amber-800 mb-2">
                <strong>🔗 需要添加 UTM 参数：</strong>
              </p>
              <a
                href="?utm=cg&cgv=install"
                className="inline-block px-4 py-2 bg-amber-600 text-white rounded hover:bg-amber-700 transition text-sm"
              >
                点击重新加载（带 UTM 参数）
              </a>
            </div>
          )}
        </Card>

        {/* 测试表单卡片 */}
        <Card className="p-6">
          <h2 className="text-xl font-semibold mb-4">模拟用户注册</h2>

          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-slate-700 mb-1">
                用户 ID *
              </label>
              <input
                type="text"
                value={formData.userId}
                onChange={(e) => setFormData({ ...formData, userId: e.target.value })}
                placeholder="test_user_123"
                className="w-full px-3 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-slate-700 mb-1">
                用户名 *
              </label>
              <input
                type="text"
                value={formData.username}
                onChange={(e) => setFormData({ ...formData, username: e.target.value })}
                placeholder="张三"
                className="w-full px-3 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-slate-700 mb-1">
                邮箱
              </label>
              <input
                type="email"
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                placeholder="test@example.com"
                className="w-full px-3 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div className="flex gap-3">
              <Button
                onClick={generateTestData}
                variant="outline"
                className="flex-1"
              >
                🎲 生成测试数据
              </Button>
              <Button
                onClick={handleRegister}
                disabled={loading || !sdkReady}
                className="flex-1"
              >
                {loading ? '上报中...' : '📤 上报注册'}
              </Button>
            </div>
          </div>
        </Card>

        {/* 错误提示 */}
        {error && (
          <Card className="p-4 bg-red-50 border-red-200">
            <div className="flex items-start gap-2">
              <span className="text-red-600 text-lg">❌</span>
              <div>
                <h3 className="font-semibold text-red-900">错误</h3>
                <p className="text-sm text-red-700 mt-1">{error}</p>
              </div>
            </div>
          </Card>
        )}

        {/* 成功结果 */}
        {result && (
          <Card className="p-6 bg-green-50 border-green-200">
            <div className="flex items-start gap-2 mb-3">
              <span className="text-green-600 text-lg">✅</span>
              <div>
                <h3 className="font-semibold text-green-900">上报成功</h3>
                <p className="text-sm text-green-700 mt-1">用户注册事件已成功上报到 CakeGrowth</p>
              </div>
            </div>
            <details className="mt-3">
              <summary className="cursor-pointer text-sm text-green-700 hover:text-green-800">
                查看响应数据
              </summary>
              <pre className="mt-2 bg-white p-3 rounded text-xs overflow-auto border border-green-200">
                {JSON.stringify(result, null, 2)}
              </pre>
            </details>
          </Card>
        )}

        {/* 说明文档 */}
        <Card className="p-6 bg-blue-50 border-blue-200">
          <h3 className="font-semibold text-blue-900 mb-2">📖 使用说明</h3>
          <ul className="text-sm text-blue-800 space-y-1 list-disc list-inside">
            <li>确保已在 <code>index.html</code> 中添加 CakeGrowth SDK 脚本</li>
            <li>
              <strong>首次访问必须带 UTM 参数：</strong>
              <code className="bg-blue-100 px-1 rounded">?utm=cg&cgv=install</code>
            </li>
            <li>SDK 初始化成功后会获取 visitorId（存储在 cookie 中）</li>
            <li>后续访问无需 UTM 参数（visitorId 已持久化）</li>
            <li>点击"生成测试数据"自动填充表单</li>
            <li>点击"上报注册"模拟用户注册事件</li>
            <li>查看浏览器控制台可以看到详细的请求日志</li>
          </ul>

          <div className="mt-4 p-3 bg-white rounded border border-blue-200">
            <p className="text-xs text-slate-600 mb-1"><strong>当前访问 URL：</strong></p>
            <code className="text-xs text-slate-800 break-all">
              {window.location.href}
            </code>
          </div>
        </Card>
      </div>
    </div>
  );
}
