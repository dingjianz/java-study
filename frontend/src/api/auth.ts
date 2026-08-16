import { request } from '@/utils/http'
import type { LoginInfo, LoginRequest } from '@/types/auth'

export const authApi = {
  login: (data: LoginRequest) => request.post<LoginInfo>('/login', data),
}
