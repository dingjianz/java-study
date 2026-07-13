import axios, { type AxiosInstance, type AxiosRequestConfig, type AxiosResponse, AxiosError } from 'axios'

// API 响应数据结构
export interface ApiResponse<T = any> {
  code: number
  message: string
  data: T
}

// 创建 axios 实例
const http: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
  timeout: 15000,
  headers: {
    'Content-Type': 'application/json',
  },
})

// 请求拦截器
http.interceptors.request.use(
  (config) => {
    // 可以在这里添加 token
    // const token = localStorage.getItem('token')
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`
    // }
    return config
  },
  (error: AxiosError) => {
    console.error('请求错误：', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
http.interceptors.response.use(
  (response: AxiosResponse<ApiResponse>) => {
    // 直接返回 response，保持类型一致
    const { data } = response

    // 根据后端返回的 code 判断请求是否成功
    if (data.code === 1) {
      return response
    } else {
      // 业务错误
      console.error('业务错误：', data.message)
      return Promise.reject(new Error(data.message || '请求失败'))
    }
  },
  (error: AxiosError<ApiResponse>) => {
    // HTTP 错误处理
    let message = '请求失败'

    if (error.response) {
      const { status, data } = error.response
      switch (status) {
        case 400:
          message = data?.message || '请求参数错误'
          break
        case 401:
          message = '未授权，请重新登录'
          break
        case 403:
          message = '拒绝访问'
          break
        case 404:
          message = '请求的资源不存在'
          break
        case 500:
          message = '服务器内部错误'
          break
        case 502:
          message = '网关错误'
          break
        case 503:
          message = '服务不可用'
          break
        case 504:
          message = '网关超时'
          break
        default:
          message = data?.message || `请求失败（${status}）`
      }
    } else if (error.request) {
      message = '网络错误，请检查网络连接'
    } else {
      message = error.message || '请求配置错误'
    }

    console.error('HTTP 错误：', message)
    return Promise.reject(new Error(message))
  }
)

// 封装常用请求方法
export const request = {
  async get<T = any>(url: string, config?: AxiosRequestConfig): Promise<ApiResponse<T>> {
    const response = await http.get<ApiResponse<T>>(url, config)
    return response.data
  },

  async post<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<ApiResponse<T>> {
    const response = await http.post<ApiResponse<T>>(url, data, config)
    return response.data
  },

  async put<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<ApiResponse<T>> {
    const response = await http.put<ApiResponse<T>>(url, data, config)
    return response.data
  },

  async delete<T = any>(url: string, config?: AxiosRequestConfig): Promise<ApiResponse<T>> {
    const response = await http.delete<ApiResponse<T>>(url, config)
    return response.data
  },

  async patch<T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<ApiResponse<T>> {
    const response = await http.patch<ApiResponse<T>>(url, data, config)
    return response.data
  },
}

export default http

