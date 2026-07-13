import { useState, useEffect } from "react";
import type { Dept } from "@/types/dept";
import { Button } from "@/components/ui/button";

interface DeptModalProps {
  open: boolean;
  type: "add" | "edit";
  dept: Dept | null;
  onClose: () => void;
  onSave: (dept: Dept) => void;
}

export default function DeptModal({ open, type, dept, onClose, onSave }: DeptModalProps) {
  const [name, setName] = useState("");

  useEffect(() => {
    if (open) {
      if (type === "edit" && dept) {
        setName(dept.name);
      } else {
        setName("");
      }
    }
  }, [open, type, dept]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!name.trim()) {
      console.log("请输入部门名称");
      return;
    }

    const deptData: Dept = {
      name: name.trim(),
    };

    if (type === "edit" && dept) {
      deptData.id = dept.id;
    }

    onSave(deptData);
  };

  if (!open) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50">
      <div className="w-full max-w-md rounded-lg bg-white p-6 shadow-xl">
        <h2 className="mb-4 text-xl font-bold">{type === "add" ? "新增部门" : "编辑部门"}</h2>

        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="mb-2 block text-sm font-medium text-gray-700">
              部门名称 <span className="text-red-500">*</span>
            </label>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} className="w-full rounded-md border border-gray-300 px-3 py-2 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500" placeholder="请输入部门名称" autoFocus />
          </div>

          <div className="flex justify-end gap-2">
            <Button type="button" variant="outline" onClick={onClose}>
              取消
            </Button>
            <Button type="submit">{type === "add" ? "新增" : "保存"}</Button>
          </div>
        </form>
      </div>
    </div>
  );
}
