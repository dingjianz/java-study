import { useEffect, useState } from "react"
import { empApi } from "@/api/emps"
import { Card, CardContent } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalTitle,
} from "@/components/ui/modal"
import { GENDER_OPTIONS, genderText, jobText } from "@/types/employee"
import type { Employee } from "@/types/employee"
import EmployeeFormModal from "@/components/dept/EmployeeFormModal"
import { cn } from "@/lib/utils"
import dayjs from "dayjs"

const selectClass =
  "flex h-9 w-full rounded-md border border-gray-300 bg-white px-3 text-sm text-gray-900 focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"

export default function EmployeeManagePage() {
  // 员工列表数据（后端 GET /emps 返回全部）
  const [employeeList, setEmployeeList] = useState<Employee[]>([])
  const [loading, setLoading] = useState(false)

  // 加载员工列表
  const loadEmployees = () => {
    setLoading(true)
    empApi
      .getAll()
      .then((res) => setEmployeeList(res.data ?? []))
      .catch(() => {
        /* 错误提示由 http 响应拦截器统一处理 */
      })
      .finally(() => setLoading(false))
  }

  useEffect(() => {
    loadEmployees()
  }, [])

  // 选中行
  const [selectedIds, setSelectedIds] = useState<Set<number>>(new Set())

  // 删除确认框
  const [isDeleteOpen, setIsDeleteOpen] = useState(false)

  // 新增/编辑表单 Modal
  const [isFormOpen, setIsFormOpen] = useState(false)
  const [formType, setFormType] = useState<"add" | "edit">("add")
  const [editingEmp, setEditingEmp] = useState<Employee | null>(null)

  const allChecked =
    employeeList.length > 0 && selectedIds.size === employeeList.length

  const toggleAll = () => {
    if (allChecked) {
      setSelectedIds(new Set())
    } else {
      setSelectedIds(new Set(employeeList.map((e) => e.id!).filter(Boolean)))
    }
  }

  const toggleOne = (id: number) => {
    setSelectedIds((prev) => {
      const next = new Set(prev)
      if (next.has(id)) {
        next.delete(id)
      } else {
        next.add(id)
      }
      return next
    })
  }

  const handleAdd = () => {
    setFormType("add")
    setEditingEmp(null)
    setIsFormOpen(true)
  }

  const handleEdit = (id: number) => {
    // 调用 getById 获取最新数据
    empApi
      .getById(id)
      .then((res) => {
        setFormType("edit")
        setEditingEmp(res.data)
        setIsFormOpen(true)
      })
      .catch(() => {
        /* 错误提示由 http 响应拦截器统一处理 */
      })
  }

  const handleDelete = (id: number) => {
    if (!confirm("确定要删除该员工吗？此操作不可撤销。")) return
    empApi
      .delete(id)
      .then(() => {
        loadEmployees()
      })
      .catch(() => {
        /* 错误提示由 http 响应拦截器统一处理 */
      })
  }

  const confirmBatchDelete = () => {
    const ids = Array.from(selectedIds)
    Promise.all(ids.map((id) => empApi.delete(id)))
      .then(() => {
        setSelectedIds(new Set())
        setIsDeleteOpen(false)
        loadEmployees()
      })
      .catch(() => {
        /* 错误提示由 http 响应拦截器统一处理 */
      })
  }

  const handleBatchDelete = () => {
    if (selectedIds.size === 0) return
    setIsDeleteOpen(true)
  }

  return (
    <div className="space-y-4">
      {/* 页面标题 */}
      <h1 className="border-l-4 border-blue-500 pl-3 text-lg font-semibold text-gray-800">
        员工管理
      </h1>

      {/* 筛选栏（静态 UI，逻辑待接口就绪后接入） */}
      <Card>
        <CardContent className="p-4 pt-4">
          <div className="flex flex-wrap items-end gap-x-6 gap-y-3">
            <div className="flex items-center gap-2">
              <label className="whitespace-nowrap text-sm text-gray-600">姓名</label>
              <Input className="w-48" placeholder="请输入员工姓名" />
            </div>

            <div className="flex items-center gap-2">
              <label className="whitespace-nowrap text-sm text-gray-600">性别</label>
              <select className={cn(selectClass, "w-40")} defaultValue="">
                <option value="">请选择</option>
                {GENDER_OPTIONS.map((o) => (
                  <option key={o.value} value={o.value}>
                    {o.label}
                  </option>
                ))}
              </select>
            </div>

            <div className="flex items-center gap-2">
              <label className="whitespace-nowrap text-sm text-gray-600">入职时间</label>
              <Input type="date" className="w-40" />
              <span className="text-sm text-gray-400">到</span>
              <Input type="date" className="w-40" />
            </div>

            <div className="flex items-center gap-2">
              <Button>查询</Button>
              <Button variant="outline">清空</Button>
            </div>
          </div>
        </CardContent>
      </Card>

      {/* 表格区 */}
      <Card>
        <CardContent className="p-4 pt-4">
          {/* 工具栏 */}
          <div className="mb-4 flex items-center gap-2">
            <Button onClick={handleAdd}>+ 新增员工</Button>
            <Button
              variant="destructive"
              onClick={handleBatchDelete}
              disabled={selectedIds.size === 0}
            >
              - 批量删除
            </Button>
          </div>

          <EmployeeTable
            list={employeeList}
            loading={loading}
            allChecked={allChecked}
            selectedIds={selectedIds}
            onToggleAll={toggleAll}
            onToggleOne={toggleOne}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />

        </CardContent>
      </Card>

      {/* 新增/编辑员工表单 */}
      <EmployeeFormModal
        open={isFormOpen}
        onOpenChange={setIsFormOpen}
        type={formType}
        employee={editingEmp}
        onSaved={loadEmployees}
      />

      {/* 批量删除确认框 */}
      <Modal open={isDeleteOpen} onOpenChange={setIsDeleteOpen}>
        <ModalContent>
          <ModalHeader>
            <ModalTitle>
              确定要删除选中的 {selectedIds.size} 名员工吗？此操作不可撤销。
            </ModalTitle>
          </ModalHeader>
          <ModalFooter>
            <Button variant="outline" onClick={() => setIsDeleteOpen(false)}>
              取消
            </Button>
            <Button
              variant="destructive"
              onClick={confirmBatchDelete}
            >
              删除
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  )
}

interface EmployeeTableProps {
  list: Employee[]
  loading: boolean
  allChecked: boolean
  selectedIds: Set<number>
  onToggleAll: () => void
  onToggleOne: (id: number) => void
  onEdit: (id: number) => void
  onDelete: (id: number) => void
}

function EmployeeTable({
  list,
  loading,
  allChecked,
  selectedIds,
  onToggleAll,
  onToggleOne,
  onEdit,
  onDelete,
}: EmployeeTableProps) {
  const thClass = "p-3 text-left text-sm font-semibold text-gray-700"

  return (
    <div className="overflow-x-auto rounded-md border border-gray-200">
      <table className="w-full border-collapse">
        <thead>
          <tr className="border-b border-gray-200 bg-gray-50">
            <th className="w-12 p-3 text-center">
              <input
                type="checkbox"
                checked={allChecked}
                onChange={onToggleAll}
                className="size-4 cursor-pointer align-middle accent-blue-600"
              />
            </th>
            <th className={thClass}>姓名</th>
            <th className={thClass}>性别</th>
            <th className={thClass}>头像</th>
            <th className={thClass}>所属部门</th>
            <th className={thClass}>职位</th>
            <th className={thClass}>入职日期</th>
            <th className={thClass}>最后操作时间</th>
            <th className="p-3 text-center text-sm font-semibold text-gray-700">操作</th>
          </tr>
        </thead>
        <tbody>
          {loading ? (
            <tr>
              <td colSpan={9} className="p-8 text-center text-gray-500">
                加载中...
              </td>
            </tr>
          ) : list.length === 0 ? (
            <tr>
              <td colSpan={9} className="p-8 text-center text-gray-500">
                暂无数据
              </td>
            </tr>
          ) : (
            list.map((emp) => (
              <tr
                key={emp.id}
                className="border-b border-gray-100 last:border-0 hover:bg-gray-50"
              >
                <td className="p-3 text-center">
                  <input
                    type="checkbox"
                    checked={selectedIds.has(emp.id!)}
                    onChange={() => onToggleOne(emp.id!)}
                    className="size-4 cursor-pointer align-middle accent-blue-600"
                  />
                </td>
                <td className="p-3 text-sm text-gray-800">{emp.name}</td>
                <td className="p-3 text-sm text-gray-800">{genderText(emp.gender)}</td>
                <td className="p-3">
                  {emp.image ? (
                    <img
                      src={emp.image}
                      alt={emp.name}
                      className="size-9 rounded object-cover"
                    />
                  ) : (
                    <div className="flex size-9 items-center justify-center rounded bg-gray-100 text-xs text-gray-400">
                      无
                    </div>
                  )}
                </td>
                <td className="p-3 text-sm text-gray-800">{emp.deptId ?? "-"}</td>
                <td className="p-3 text-sm text-gray-800">{jobText(emp.job)}</td>
                <td className="p-3 text-sm text-gray-800">
                  {emp.entryDate
                    ? dayjs(emp.entryDate).format("YYYY-MM-DD")
                    : "-"}
                </td>
                <td className="p-3 text-sm text-gray-800">
                  {emp.updateTime
                    ? dayjs(emp.updateTime).format("YYYY-MM-DD HH:mm:ss")
                    : "-"}
                </td>
                <td className="p-3">
                  <div className="flex justify-center gap-3 text-sm">
                    <button
                      type="button"
                      onClick={() => onEdit(emp.id!)}
                      className="text-blue-600 hover:underline"
                    >
                      编辑
                    </button>
                    <button
                      type="button"
                      onClick={() => onDelete(emp.id!)}
                      className="text-red-500 hover:underline"
                    >
                      删除
                    </button>
                  </div>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  )
}
