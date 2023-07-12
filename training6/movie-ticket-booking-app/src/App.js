import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom"
import LoginSignup from "./components/Login&Signup/LoginSignup";
import LandingPage from "./components/Welcome/LandingPage";
import Profile from "./components/Welcome/Profile";
import Bookings from "./components/Welcome/Bookings";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes> 
          <Route path="/" element={<LandingPage />} />
          <Route path="/login-signup" element={<LoginSignup />} />
          <Route path="/dashboard" element={<LandingPage />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/bookings" element={<Bookings />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
