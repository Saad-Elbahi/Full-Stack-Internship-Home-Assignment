import React from 'react';

const Layout = ({ children }) => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <header className="bg-blue-500 text-white py-4">
        <h1 className="text-3xl font-semibold">CSV Processing App</h1>
      </header>
      <main className="mt-8 p-6 bg-white shadow-md rounded-md">{children}</main>
    </div>
  );
};

export default Layout;
