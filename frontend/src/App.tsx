import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { Toaster } from 'sonner'
import AboutPage from './pages/About'
import DeptManagePage from './pages/DeptManage'
import './index.css'

function App() {
  return (
    <BrowserRouter>
      <Toaster position="top-center" richColors />
      <Routes>
        <Route path="/" element={<DeptManagePage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/dept" element={<DeptManagePage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
