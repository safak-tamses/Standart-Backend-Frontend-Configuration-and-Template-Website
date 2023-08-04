import "./App.css";

import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Page1 from "./Page/Page1";
import Page2 from "./Page/Page2";
import Page3 from "./Page/Page3";
import Navbar from "./Page/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Page1 />}></Route>
        <Route path="/page2" element={<Page2 />}></Route>
        <Route path="/page3" element={<Page3 />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
