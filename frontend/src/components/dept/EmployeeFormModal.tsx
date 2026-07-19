import { useEffect, useState } from "react"
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalTitle,
} from "@/components/ui/modal"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { GENDER_OPTIONS, JOB_OPTIONS } from "@/types/employee"
import type { Employee, EmployeeForm, EmpExpr } from "@/types/employee"
import type { Dept } from "@/types/dept"
import { deptApi } from "@/api/dept"
import { empApi } from "@/api/emps"
import { uploadApi } from "@/api/upload"
import { toast } from "sonner"
import { cn } from "@/lib/utils"

interface EmployeeFormModalProps {
  open: boolean
  onOpenChange: (open: boolean) => void
  /** add：新增，edit：编辑 */
  type: "add" | "edit"
  /** 编辑时的初始数据 */
  employee?: Employee | null
  /** 保存回调（逻辑待接口就绪后接入） */
  onSaved?: () => void
}

const labelClass = "w-20 shrink-0 text-right text-sm text-gray-600"
const requiredMark = <span className="text-red-500">* </span>
const selectClass =
  "flex h-9 w-full rounded-md border border-gray-300 bg-white px-3 text-sm text-gray-900 focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"

function emptyForm(): EmployeeForm {
  return {
    username: "",
    name: "",
    gender: undefined,
    phone: "",
    job: undefined,
    salary: undefined,
    deptId: undefined,
    entryDate: "",
    image: "",
    exprList: [],
  }
}

export default function EmployeeFormModal({
  open,
  onOpenChange,
  type,
  employee,
  onSaved,
}: EmployeeFormModalProps) {
  const [form, setForm] = useState<EmployeeForm>(emptyForm())
  const [deptList, setDeptList] = useState<Dept[]>([])

  // 打开时初始化表单数据
  useEffect(() => {
    if (!open) return
    if (type === "edit" && employee) {
      setForm({ ...emptyForm(), ...employee, exprList: employee.exprList ?? [] })
    } else {
      setForm(emptyForm())
    }
  }, [open, type, employee])

  // 加载部门下拉数据
  useEffect(() => {
    if (!open) return
    deptApi
      .getAll()
      .then((res) => setDeptList(res.data))
      .catch(() => {
        /* 拉取失败时下拉为空，联调时后端就绪即可 */
      })
  }, [open])

  const update = (patch: Partial<EmployeeForm>) =>
    setForm((prev) => ({ ...prev, ...patch }))

  // 工作经历增删改
  const addExpr = () =>
    update({ exprList: [...(form.exprList ?? []), {} as EmpExpr] })

  const removeExpr = (idx: number) =>
    update({ exprList: (form.exprList ?? []).filter((_, i) => i !== idx) })

  const updateExpr = (idx: number, patch: Partial<EmpExpr>) =>
    update({
      exprList: (form.exprList ?? []).map((e, i) =>
        i === idx ? { ...e, ...patch } : e
      ),
    })

  const handleSave = () => {
    if (!form.username?.trim()) return toast.warning("请输入用户名")
    if (!form.name?.trim()) return toast.warning("请输入姓名")
    if (!form.gender) return toast.warning("请选择性别")
    if (!form.phone?.trim()) return toast.warning("请输入手机号")

    // 过滤掉空的工作经历对象
    const filteredExprList = (form.exprList ?? []).filter((expr) => {
      // 只要有任一字段有值，就保留该条记录
      return (
        expr.beginDate ||
        expr.endDate ||
        expr.company?.trim() ||
        expr.job?.trim()
      )
    })

    const submitData = { ...form, exprList: filteredExprList }
    const apiCall = type === "add" ? empApi.add(submitData as Employee) : empApi.update(submitData as Employee)

    apiCall
      .then(() => {
        toast.success(type === "add" ? "新增成功" : "修改成功")
        onOpenChange(false)
        onSaved?.()
      })
      .catch(() => {
        /* 错误提示由 http 响应拦截器统一处理 */
      })
  }

  return (
    <Modal open={open} onOpenChange={onOpenChange}>
      <ModalContent className="max-w-3xl">
        <ModalHeader>
          <ModalTitle className="border-l-4 border-blue-500 pl-2 text-base text-blue-600">
            {type === "add" ? "新增员工" : "编辑员工"}
          </ModalTitle>
        </ModalHeader>

        <div className="max-h-[70vh] space-y-4 overflow-y-auto pr-1">
          {/* 用户名 / 姓名 */}
          <div className="grid grid-cols-2 gap-4">
            <div className="flex items-center gap-2">
              <label className={labelClass}>{requiredMark}用户名</label>
              <Input
                value={form.username ?? ""}
                onChange={(e) => update({ username: e.target.value })}
                placeholder="请输入员工用户名，2-20个字"
              />
            </div>
            <div className="flex items-center gap-2">
              <label className={labelClass}>{requiredMark}姓名</label>
              <Input
                value={form.name ?? ""}
                onChange={(e) => update({ name: e.target.value })}
                placeholder="请输入员工姓名，2-10个字"
              />
            </div>
          </div>

          {/* 性别 / 手机号 */}
          <div className="grid grid-cols-2 gap-4">
            <div className="flex items-center gap-2">
              <label className={labelClass}>{requiredMark}性别</label>
              <select
                className={selectClass}
                value={form.gender ?? ""}
                onChange={(e) =>
                  update({ gender: e.target.value ? Number(e.target.value) : undefined })
                }
              >
                <option value="">请选择</option>
                {GENDER_OPTIONS.map((o) => (
                  <option key={o.value} value={o.value}>
                    {o.label}
                  </option>
                ))}
              </select>
            </div>
            <div className="flex items-center gap-2">
              <label className={labelClass}>{requiredMark}手机号</label>
              <Input
                value={form.phone ?? ""}
                onChange={(e) => update({ phone: e.target.value })}
                placeholder="请输入员工手机号"
              />
            </div>
          </div>

          {/* 职位 / 薪资 */}
          <div className="grid grid-cols-2 gap-4">
            <div className="flex items-center gap-2">
              <label className={labelClass}>职位</label>
              <select
                className={selectClass}
                value={form.job ?? ""}
                onChange={(e) =>
                  update({ job: e.target.value ? Number(e.target.value) : undefined })
                }
              >
                <option value="">请选择</option>
                {JOB_OPTIONS.map((o) => (
                  <option key={o.value} value={o.value}>
                    {o.label}
                  </option>
                ))}
              </select>
            </div>
            <div className="flex items-center gap-2">
              <label className={labelClass}>薪资</label>
              <Input
                type="number"
                value={form.salary ?? ""}
                onChange={(e) =>
                  update({ salary: e.target.value ? Number(e.target.value) : undefined })
                }
                placeholder="请输入员工薪资"
              />
            </div>
          </div>

          {/* 所属部门 / 入职日期 */}
          <div className="grid grid-cols-2 gap-4">
            <div className="flex items-center gap-2">
              <label className={labelClass}>所属部门</label>
              <select
                className={selectClass}
                value={form.deptId ?? ""}
                onChange={(e) =>
                  update({ deptId: e.target.value ? Number(e.target.value) : undefined })
                }
              >
                <option value="">请选择</option>
                {deptList.map((d) => (
                  <option key={d.id} value={d.id}>
                    {d.name}
                  </option>
                ))}
              </select>
            </div>
            <div className="flex items-center gap-2">
              <label className={labelClass}>入职日期</label>
              <Input
                type="date"
                value={form.entryDate ?? ""}
                onChange={(e) => update({ entryDate: e.target.value })}
              />
            </div>
          </div>

          {/* 头像 */}
          <div className="flex items-start gap-2">
            <label className={cn(labelClass, "pt-1")}>头像</label>
            <div className="flex items-center gap-4">
              <AvatarUploader
                value={form.image}
                onChange={(url) => update({ image: url })}
              />
              <div className="text-xs leading-6 text-gray-500">
                <p>图片大小不超过 2M</p>
                <p>仅能上传 PNG、JPEG、JPG 等图片</p>
                <p>建议上传 200*200 或 300*300 尺寸的照片</p>
              </div>
            </div>
          </div>

          {/* 工作经历 */}
          <div className="flex items-start gap-2">
            <label className={cn(labelClass, "pt-2")}>工作经历</label>
            <div className="flex-1 space-y-2 rounded-md bg-gray-50 p-3">
              {(form.exprList ?? []).length === 0 && (
                <p className="text-xs text-gray-400">暂无工作经历，点击下方按钮添加</p>
              )}
              {(form.exprList ?? []).map((expr, idx) => (
                <div key={idx} className="flex flex-wrap items-center gap-2">
                  <span className="text-sm text-gray-600">时间</span>
                  <Input
                    type="date"
                    className="w-36"
                    value={expr.beginDate ?? ""}
                    onChange={(e) => updateExpr(idx, { beginDate: e.target.value })}
                  />
                  <span className="text-sm text-gray-400">到</span>
                  <Input
                    type="date"
                    className="w-36"
                    value={expr.endDate ?? ""}
                    onChange={(e) => updateExpr(idx, { endDate: e.target.value })}
                  />
                  <span className="text-sm text-gray-600">公司</span>
                  <Input
                    className="w-36"
                    placeholder="请输入公司的名字"
                    value={expr.company ?? ""}
                    onChange={(e) => updateExpr(idx, { company: e.target.value })}
                  />
                  <span className="text-sm text-gray-600">职位</span>
                  <Input
                    className="w-28"
                    placeholder="请输入职位"
                    value={expr.job ?? ""}
                    onChange={(e) => updateExpr(idx, { job: e.target.value })}
                  />
                  <button
                    type="button"
                    onClick={() => removeExpr(idx)}
                    className="text-sm text-red-500 hover:underline"
                  >
                    删除
                  </button>
                </div>
              ))}
              <div className="flex justify-end">
                <Button
                  type="button"
                  size="sm"
                  variant="outline"
                  className="border-blue-200 bg-blue-50 text-blue-600 hover:bg-blue-100 hover:text-blue-700"
                  onClick={addExpr}
                >
                  添加工作经历
                </Button>
              </div>
            </div>
          </div>
        </div>

        <ModalFooter>
          <Button onClick={handleSave}>保存</Button>
          <Button variant="outline" onClick={() => onOpenChange(false)}>
            取消
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  )
}

interface AvatarUploaderProps {
  value?: string
  onChange: (url: string) => void
}

/** 头像上传：上传到 S3 */
function AvatarUploader({ value, onChange }: AvatarUploaderProps) {
  const [uploading, setUploading] = useState(false)

  const handleFile = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0]
    if (!file) return

    // 验证文件大小
    if (file.size > 2 * 1024 * 1024) {
      toast.warning("图片大小不能超过 2M")
      return
    }

    // 验证文件类型
    const validTypes = ['image/png', 'image/jpeg', 'image/jpg']
    if (!validTypes.includes(file.type)) {
      toast.warning("仅支持 PNG、JPEG、JPG 格式的图片")
      return
    }

    try {
      setUploading(true)
      // 上传到 S3，使用 "avatars" 前缀
      const response = await uploadApi.uploadImage(file, 'avatars')

      // 使用公共 URL 作为头像地址
      const imageUrl = response.data.publicUrl
      onChange(imageUrl)

      toast.success("头像上传成功")
    } catch (error) {
      console.error('头像上传失败:', error)
      toast.error("头像上传失败，请重试")
    } finally {
      setUploading(false)
      // 清空 input，允许重新选择相同文件
      e.target.value = ''
    }
  }

  return (
    <label className={cn(
      "flex size-24 cursor-pointer items-center justify-center overflow-hidden rounded border border-gray-200 bg-gray-50 hover:border-blue-400",
      uploading && "cursor-not-allowed opacity-60"
    )}>
      {uploading ? (
        <div className="flex flex-col items-center justify-center gap-1">
          <svg className="size-8 animate-spin text-blue-500" viewBox="0 0 24 24" fill="none">
            <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4" />
            <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z" />
          </svg>
          <span className="text-xs text-gray-500">上传中...</span>
        </div>
      ) : value ? (
        <img src={value} alt="头像" className="size-full object-cover" />
      ) : (
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" className="size-10 text-gray-300">
          <rect x="3" y="3" width="18" height="18" rx="2" />
          <circle cx="8.5" cy="8.5" r="1.5" />
          <path d="m21 15-5-5L5 21" />
        </svg>
      )}
      <input
        type="file"
        accept="image/png,image/jpeg,image/jpg"
        className="hidden"
        onChange={handleFile}
        disabled={uploading}
      />
    </label>
  )
}
