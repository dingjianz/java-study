import { useState, useEffect } from "react";
import { deptApi } from "@/api/dept";
import type { Dept } from "@/types/dept";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Modal, ModalContent, ModalHeader, ModalFooter, ModalTitle } from "@/components/ui/modal";
import { Input } from "@/components/ui/input";
import { toast } from "sonner";
import dayjs from "dayjs";

export default function DeptManagePage() {
  const [deptList, setDeptList] = useState<Dept[]>([]);
  const [loading, setLoading] = useState(false);

  // 编辑/新增模态框状态
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalType, setModalType] = useState<"add" | "edit">("add");
  const [currentDept, setCurrentDept] = useState<Dept | null>(null);
  const [deptName, setDeptName] = useState("");

  // 删除确认框状态
  const [isDeleteOpen, setIsDeleteOpen] = useState(false);
  const [deleteId, setDeleteId] = useState<number | null>(null);

  // 加载部门列表
  const loadDeptList = async () => {
    try {
      setLoading(true);
      const response = await deptApi.getAll();
      setDeptList(response.data);
    } catch (error) {
      console.error("加载部门列表失败：", error);
      console.log("加载部门列表失败");
    } finally {
      setLoading(false);
    }
  };

  // 页面加载时获取数据
  useEffect(() => {
    loadDeptList();
  }, []);

  // 新增部门
  const handleAdd = () => {
    setModalType("add");
    setCurrentDept(null);
    setDeptName("");
    setIsModalOpen(true);
  };

  // 编辑部门
  const handleEdit = async (dept: Dept) => {
    if (!dept.id) {
      console.error("部门 ID 不存在");
      return;
    }

    try {
      const response = await deptApi.getById(dept.id);
      setModalType("edit");
      setCurrentDept(response.data);
      setDeptName(response.data.name);
      setIsModalOpen(true);
    } catch (error) {
      console.error("获取部门详情失败：", error);
    }
  };

  // 删除部门：打开确认框
  const handleDelete = (id: number) => {
    setDeleteId(id);
    setIsDeleteOpen(true);
  };

  // 确认删除
  const handleConfirmDelete = async () => {
    if (deleteId === null) {
      return;
    }

    try {
      await deptApi.delete(deleteId);
      toast.success("删除成功");
      loadDeptList();
    } catch (error) {
      console.error("删除部门失败：", error);
    } finally {
      setIsDeleteOpen(false);
      setDeleteId(null);
    }
  };

  // 保存部门（新增或编辑）
  const handleSave = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!deptName.trim()) {
      toast.warning("请输入部门名称");
      return;
    }

    try {
      const deptData: Dept = {
        name: deptName.trim(),
      };

      if (modalType === "edit" && currentDept) {
        deptData.id = currentDept.id;
      }

      if (modalType === "add") {
        await deptApi.add(deptData);
        toast.success("新增成功");
      } else {
        await deptApi.update(deptData);
        toast.success("修改成功");
      }
      setIsModalOpen(false);
      loadDeptList();
    } catch (error) {
      console.error("保存部门失败：", error);
    }
  };

  return (
    <div className="container mx-auto p-6">
      <Card>
        <CardHeader className="flex flex-row items-center justify-between">
          <CardTitle className="text-2xl font-bold">部门管理</CardTitle>
          <Button onClick={handleAdd}>新增部门</Button>
        </CardHeader>
        <CardContent>
          {/* 表格 */}
          <div className="overflow-x-auto">
            <table className="w-full border-collapse">
              <thead>
                <tr className="border-b bg-gray-50">
                  <th className="p-3 text-left font-semibold">ID</th>
                  <th className="p-3 text-left font-semibold">部门名称</th>
                  <th className="p-3 text-left font-semibold">创建时间</th>
                  <th className="p-3 text-left font-semibold">更新时间</th>
                  <th className="p-3 text-center font-semibold">操作</th>
                </tr>
              </thead>
              <tbody>
                {loading ? (
                  <tr>
                    <td colSpan={5} className="p-8 text-center text-gray-500">
                      加载中...
                    </td>
                  </tr>
                ) : deptList.length === 0 ? (
                  <tr>
                    <td colSpan={5} className="p-8 text-center text-gray-500">
                      暂无数据
                    </td>
                  </tr>
                ) : (
                  deptList.map((dept) => (
                    <tr key={dept.id} className="border-b hover:bg-gray-50">
                      <td className="p-3">{dept.id}</td>
                      <td className="p-3">{dept.name}</td>
                      <td className="p-3">{dept.createTime ? dayjs(dept.createTime).format("YYYY-MM-DD HH:mm:ss") : "-"}</td>
                      <td className="p-3">{dept.updateTime ? dayjs(dept.updateTime).format("YYYY-MM-DD HH:mm:ss") : "-"}</td>
                      <td className="p-3">
                        <div className="flex justify-center gap-2">
                          <Button size="sm" variant="outline" onClick={() => handleEdit(dept)}>
                            编辑
                          </Button>
                          <Button size="sm" variant="destructive" onClick={() => handleDelete(dept.id!)}>
                            删除
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {/* 新增/编辑模态框 */}
      <Modal open={isModalOpen} onOpenChange={setIsModalOpen}>
        <ModalContent>
          <ModalHeader>
            <ModalTitle>{modalType === "add" ? "新增部门" : "编辑部门"}</ModalTitle>
          </ModalHeader>

          <form onSubmit={handleSave}>
            <div className="mb-4">
              <label className="mb-2 block text-sm font-medium text-gray-700">
                部门名称 <span className="text-red-500">*</span>
              </label>
              <Input
                type="text"
                value={deptName}
                onChange={(e) => setDeptName(e.target.value)}
                placeholder="请输入部门名称"
                autoFocus
              />
            </div>

            <ModalFooter>
              <Button type="button" variant="outline" onClick={() => setIsModalOpen(false)}>
                取消
              </Button>
              <Button type="submit">{modalType === "add" ? "新增" : "保存"}</Button>
            </ModalFooter>
          </form>
        </ModalContent>
      </Modal>

      {/* 删除确认框 */}
      <Modal open={isDeleteOpen} onOpenChange={setIsDeleteOpen}>
        <ModalContent>
          <ModalHeader>
            <ModalTitle>确定要删除该部门吗？此操作不可撤销。</ModalTitle>
          </ModalHeader>
          <ModalFooter>
            <Button variant="outline" onClick={() => setIsDeleteOpen(false)}>
              取消
            </Button>
            <Button variant="destructive" onClick={handleConfirmDelete}>
              删除
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}
