import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { auth } from "../../utilities/authentication";

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
    <>
      <NavLink to="/dashboard">Dashboard</NavLink>
      <NavLink to="/bookings">Bookings</NavLink>
      <NavLink to="/book-train">Book Train</NavLink>
      <NavLink to="/profile">Profile</NavLink>
      <button
        type="submit"
        onClick={() => {
          logout();
        }}
      >
        Logout
      </button>
    </>
  );
};

export default NavBar;
