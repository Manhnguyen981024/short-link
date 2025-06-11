import React, { useState } from "react";
import axios from "axios";
import ShortLinkResult from "./ShortLinkResult";

export default function UrlShortenerForm() {
  const [url, setUrl] = useState("");
  const [shortLink, setShortLink] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await axios.post("http://localhost:8082/shorten", {
        fullLinkUrl: url,
      });
      setShortLink(response.data.shortLink);
    } catch (err) {
      alert("Có lỗi xảy ra khi rút gọn URL." + err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="w-100 flex justify-center items-center h-full mt-90">
      <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-md">
        <form onSubmit={handleSubmit} className="flex flex-col">
          <label className="mb-2 font-medium text-gray-700">
            Nhập URL gốc:
          </label>

          <input
            type="text"
            value={url}
            onChange={(e) => setUrl(e.target.value)}
            className="border border-gray-300 rounded px-3 py-2 mb-4 focus:outline-none focus:ring-2 focus:ring-blue-400"
            placeholder="https://example.com"
            required
          />

          <button
            type="submit"
            className="bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
            disabled={loading}
          >
            {loading ? "Đang xử lý..." : "Rút gọn URL"}
          </button>
        </form>

        {shortLink && <ShortLinkResult link={shortLink} />}
      </div>
    </div>
  );
}
