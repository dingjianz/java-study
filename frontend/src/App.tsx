import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { Toaster } from 'sonner'
import AdminLayout from './components/layout/AdminLayout'
import DeptManagePage from './pages/DeptManage'
import EmployeeManagePage from './pages/EmployeeManage'
import PlaceholderPage from './pages/Placeholder'
import AboutPage from './pages/About'
import CakeGrowthTest from './pages/CakeGrowthTest'
import './index.css'

function App() {
  return (
    <BrowserRouter>
      <Toaster position="top-center" richColors />
      <Routes>
        {/* 默认进入后台，落到部门管理 */}
        <Route path="/" element={<Navigate to="/admin/dept" replace />} />

        {/* 后台管理布局 */}
        <Route path="/admin" element={<AdminLayout />}>
          <Route index element={<Navigate to="/admin/dept" replace />} />
          <Route path="dept" element={<DeptManagePage />} />
          <Route path="employee" element={<EmployeeManagePage />} />
          {/* 未实现模块统一占位 */}
          <Route path="home" element={<PlaceholderPage />} />
          <Route path="class" element={<PlaceholderPage />} />
          <Route path="student" element={<PlaceholderPage />} />
          <Route path="stat/employee" element={<PlaceholderPage />} />
          <Route path="stat/student" element={<PlaceholderPage />} />
        </Route>

        {/* 保留原有页面 */}
        <Route path="/about" element={<AboutPage />} />
        <Route path="/cakegrowth" element={<CakeGrowthTest />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
