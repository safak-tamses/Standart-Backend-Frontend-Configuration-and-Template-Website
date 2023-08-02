// Navbar.js dosyası
import React from "react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="bg-gray-800 p-4">
      <ul className="flex space-x-4">
        <li>
          <Link to="/" className="text-white text-4xl ">
          <img src="/images/mavidev.png" alt="logo" style={{width: "70%"}}/>
          </Link>
        </li>
        <li>
          <Link to="/page2" className="text-white text-xl ">
            Page 2
          </Link>
        </li>
        <li>
          <Link to="/page3" className="text-white text-xl ">
            Page 3
          </Link>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
