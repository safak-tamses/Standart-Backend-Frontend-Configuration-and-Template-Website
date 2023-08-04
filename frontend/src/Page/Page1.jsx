import React from "react";

function Page1() {
  return (
    <div
      className="h-screen flex flex-col items-center justify-start"
      style={{ backgroundColor: "#313951" }}
    >
      <div className="text-center">
        <h1 className="text-4xl font-bold mb-4 text-white">
          Welcome to Page 1
        </h1>
        <h2 className="text-3xl font-bold mb-6 text-white">
          This project is a sample page of a project made for "Mavidev Software"
        </h2>
      </div>
    </div>
  );
}

export default Page1;
