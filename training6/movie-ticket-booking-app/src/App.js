import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom"
import LoginSignup from "./components/Login&Signup/LoginSignup";
import LandingPage from "./components/Welcome/LandingPage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes> 
          <Route path="/" element={<LandingPage />} />
          <Route path="/login-signup" element={<LoginSignup />} />
          <Route path="/dashboard" element={<LandingPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
