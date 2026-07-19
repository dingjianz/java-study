import { request } from '@/utils/http'

export interface UploadResponse {
  key: string
  fileName: string
  fileSize: number
  contentType?: string
  publicUrl: string
  presignedUrl: string
}

/**
 * 上传 API
 */
export const uploadApi = {
  /**
   * 上传文件到 S3
   * @param file 文件对象
   * @returns 上传结果
   */
  uploadFile(file: File): Promise<{ data: UploadResponse }> {
    const formData = new FormData()
    formData.append('file', file)

    return request.post('/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
  },

  /**
   * 上传图片到 S3
   * @param file 图片文件
   * @param prefix 路径前缀（可选，默认 "images"）
   * @returns 上传结果
   */
  uploadImage(file: File, prefix = 'images'): Promise<{ data: UploadResponse }> {
    const formData = new FormData()
    formData.append('file', file)

    return request.post('/upload/image', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
      params: {
        prefix,
      },
    })
  },

  /**
   * 删除文件
   * @param key S3 文件 key
   */
  deleteFile(key: string): Promise<{ data: null }> {
    return request.delete('/upload', {
      params: { key },
    })
  },

  /**
   * 获取文件访问 URL
   * @param key S3 文件 key
   * @param expireTime 过期时间（分钟）
   */
  getFileUrl(key: string, expireTime = 10080): Promise<{ data: {
    key: string
    publicUrl: string
    presignedUrl: string
    expireMinutes: string
  } }> {
    return request.get('/upload/url', {
      params: { key, expireTime },
    })
  },
}
