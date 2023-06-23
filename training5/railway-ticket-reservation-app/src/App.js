import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/login/Login";
import SignUp from "./components/signup/SignUp";
import Home from "./components/dashboard/Home";
import Bookings from "./components/bookings/Bookings";
import BookTrain from "./components/book-train/BookTrain";
import Profile from "./components/profile/Profile";
import BookTrains from "./components/book-train/BookTrains";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/dashboard" element={<Home />} />
          <Route path="/bookings" element={<Bookings />} />
          <Route path="/book-train" element={<BookTrain />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/book" element={<BookTrains />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
