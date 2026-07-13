import { BrowserRouter, Routes, Route } from 'react-router-dom'
import AboutPage from './pages/About'
import DashboardPage from './pages/Dashboard'
import DeptManagePage from './pages/DeptManage'
import './index.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<DeptManagePage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/dept" element={<DeptManagePage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
