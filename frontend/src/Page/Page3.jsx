import React, { useState, useEffect } from "react";
import axios from "axios";
const API_URL = "http://localhost:8080/show";

function Page3() {
  const [data, setData] = useState([]);

  const fetchData = () => {
    axios
      .get(API_URL)
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="min-h-screen flex items-start justify-center" style={{ backgroundColor: "#313951" }}>
  <div className="bg-white p-8 rounded shadow-lg w-full md:w-2/3 lg:w-3/4 mt-4">
    <h1 className="text-4xl font-bold mb-6 text-center">
      City & District Table
    </h1>
    <div className="flex justify-end mb-4">
      <button
        className="bg-blue-500 text-white py-2 px-4 rounded font-bold"
        onClick={fetchData}
      >
        Refresh Page
      </button>
    </div>
    <div className="flex flex-col items-center">
      <table className="w-full border-collapse">
        <thead className="flex-1">
          <tr>
            <th className="border px-4 py-2 text-center">#</th>
            <th className="border px-4 py-2 text-center">City</th>
            <th className="border px-4 py-2 text-center">District</th>
          </tr>
        </thead>
        <tbody className="flex-1">
          {data.map((item) => (
            <tr key={item.id}>
              <td className="border px-4 py-2 text-center align-middle">
                {item.id}
              </td>
              <td className="border px-4 py-2 text-center align-middle">
                {item.city}
              </td>
              <td className="border px-4 py-2 text-center align-middle">
                {item.district}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  </div>
</div>

  );
}

export default Page3;
