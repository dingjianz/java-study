import { useState, useEffect } from "react";
import { deptApi } from "@/api/dept";
import type { Dept } from "@/types/dept";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import DeptModal from "@/components/dept/DeptModal";
import dayjs from "dayjs";

export default function DeptManagePage() {
  const [deptList, setDeptList] = useState<Dept[]>([]);
  const [loading, setLoading] = useState(false);

  // 模态框状态
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalType, setModalType] = useState<"add" | "edit">("add");
  const [currentDept, setCurrentDept] = useState<Dept | null>(null);

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
    setIsModalOpen(true);
  };

  // 编辑部门
  const handleEdit = (dept: Dept) => {
    setModalType("edit");
    setCurrentDept(dept);
    setIsModalOpen(true);
  };

  // 删除部门
  const handleDelete = async (id: number) => {
    if (!confirm("确定要删除该部门吗？")) {
      return;
    }

    try {
      await deptApi.delete(id);
      console.log("删除成功");
      loadDeptList();
    } catch (error) {
      console.error("删除部门失败：", error);
      console.log("删除失败");
    }
  };

  // 保存部门（新增或编辑）
  const handleSave = async (dept: Dept) => {
    try {
      if (modalType === "add") {
        await deptApi.add(dept);
        console.log("新增成功");
      } else {
        await deptApi.update(dept);
        console.log("修改成功");
      }
      setIsModalOpen(false);
      loadDeptList();
    } catch (error) {
      console.error("保存部门失败：", error);
      console.log("保存失败");
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
      <DeptModal open={isModalOpen} type={modalType} dept={currentDept} onClose={() => setIsModalOpen(false)} onSave={handleSave} />
    </div>
  );
}
