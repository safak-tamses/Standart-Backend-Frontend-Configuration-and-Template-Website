import React, { useState } from 'react';
import axios from 'axios';

const API_URL = "http://localhost:8080/save";

function Page2() {
  const [city, setCity] = useState('');
  const [district, setDistrict] = useState('');
  const [numberPlate, setnumberPlate] = useState('')
  const [responseMessage, setResponseMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleCityChange = (e) => {
    setCity(e.target.value);
  };

  const handleDistrictChange = (e) => {
    setDistrict(e.target.value);
  };
  
  const handlePlateNumberChange = (e) => {
    setnumberPlate(e.target.value);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      city: city,
      district: district,
      numberPlate : numberPlate
    };

    const config = {
      headers: {
        'Content-Type': 'application/json'
      }
    };

    axios.post(API_URL, JSON.stringify(data), config)
      .then(response => {
        setResponseMessage('Success: ' + JSON.stringify(response.data));
        setErrorMessage('');
        setCity('');
        setDistrict('');
        setnumberPlate('');
      })
      .catch(error => {
        setErrorMessage('Error: ' + error.message);
        setResponseMessage('');
      });
  };

  return (
    <div className="min-h-screen flex items-center justify-center" style={{backgroundColor:'#313951'}}>
      <div className="bg-white p-8 rounded shadow-lg w-full md:w-1/2 lg:w-1/3">
        <h1 className="text-4xl font-bold mb-6 text-center">City & District Form</h1>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="city" className="block font-bold mb-2">City:</label>
            <input
              type="text"
              id="city"
              className="w-full px-4 py-2 rounded border border-gray-400"
              placeholder="Enter your city"
              value={city}
              onChange={handleCityChange}
            />
          </div>
          <div className="mb-4">
            <label htmlFor="district" className="block font-bold mb-2">District:</label>
            <input
              type="text"
              id="district"
              className="w-full px-4 py-2 rounded border border-gray-400"
              placeholder="Enter your district"
              value={district}
              onChange={handleDistrictChange}
            />
          </div>
        
          <div className="mb-4">
            <label htmlFor="plateNumber" className="block font-bold mb-2">Plate Number:</label>
            <input
              type="text"
              id="numberPlate"
              className="w-full px-4 py-2 rounded border border-gray-400"
              placeholder="Enter your plate number"
              value={numberPlate}
              onChange={handlePlateNumberChange}
            />
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 px-4 rounded font-bold"
          >
            Save
          </button>
        </form>
        {responseMessage && <div className="mt-4 text-green-500">{responseMessage}</div>}
        {errorMessage && <div className="mt-4 text-red-500">{errorMessage}</div>}
      </div>
    </div>
  );
}

export default Page2;
