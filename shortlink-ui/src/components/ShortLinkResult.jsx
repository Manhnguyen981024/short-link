import React from "react";

export default function ShortLinkResult({ link }) {
  const copyToClipboard = () => {
    navigator.clipboard.writeText(link);
    alert("📋 Đã copy short link!");
  };

  return (
    <div className="mt-6 text-center">
      <p className="mb-2 text-green-600 font-semibold">🎉 Link rút gọn của bạn:</p>
      <a
        href={`http://localhost:8082/${link}`}
        target="_blank"
        rel="noopener noreferrer"
        className="text-blue-500 underline block mb-2"
      >
        {link}
      </a>
      <button
        onClick={copyToClipboard}
        className="bg-green-500 text-white px-4 py-1 rounded hover:bg-green-600"
      >
        Copy
      </button>
    </div>
  );
}