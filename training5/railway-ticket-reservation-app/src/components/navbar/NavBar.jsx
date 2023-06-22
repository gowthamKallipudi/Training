import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { auth } from "../../utilities/authentication";
import "./navbar.css";

const NavBar = () => {
  const navigate = useNavigate();
  const logout = () => {
    auth.dispatch({
      type: "Logout",
      payload: {},
    });
    navigate("/login");
  };
  return (
    <div className="navbar-container">
      <button
        type="button"
        onClick={() => {
          navigate("/dashboard");
        }}
      >
        Dashboard
      </button>
      <button
        type="button"
        onClick={() => {
          navigate("/bookings");
        }}
      >
        Bookings
      </button>
      <button
        type="button"
        onClick={() => {
          navigate("/book-train");
        }}
      >
        Book Train
      </button>
      <button
        type="button"
        onClick={() => {
          navigate("/profile");
        }}
      >
        Profile
      </button>
      <button
        type="submit"
        onClick={() => {
          logout();
        }}
      >
        Logout
      </button>
    </div>
  );
};

export default NavBar;
