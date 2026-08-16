export interface LoginRequest {
  username: string
  password: string
}

/** 后端 LoginInfo 结构（扁平，非嵌套） */
export interface LoginInfo {
  id: number
  username: string
  name: string
  token: string
}
