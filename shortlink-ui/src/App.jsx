import React from "react";
import UrlShortenerForm from "./components/UrlShortenerForm";
import {
  Router,
  Routes,
  Route,
  Link
} from 'react-router-dom';
import ShortLinkStats from "./components/ShortLinkStats";

function App() {
  return (
     <div className="min-h-screen bg-gradient-to-br from-blue-100 via-purple-100 to-pink-100">
      <nav className="bg-white shadow-md p-4 flex justify-between">
        <div className="text-lg font-bold text-blue-600">ShortLink App</div>
        <div className="flex gap-4">
          <Link to="/" className="text-gray-700 hover:text-blue-500">Trang chủ</Link>
          <Link to="/stats" className="text-gray-700 hover:text-blue-500">Thống kê</Link>
        </div>
      </nav>
      <main className="flex justify-center py-10">
        <Routes>
          <Route path="/" element={<UrlShortenerForm />} />
          <Route path="/stats" element={<ShortLinkStats />} />
        </Routes>
      </main>
    </div>
  );
}

export default App
