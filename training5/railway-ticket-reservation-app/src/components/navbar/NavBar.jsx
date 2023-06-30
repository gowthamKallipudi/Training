import React from "react";
import { useNavigate } from "react-router-dom";
import { auth } from "../../utilities/authentication";
import "./navbar.css";

const NavBar = () => {
  const navigate = useNavigate();

  const logout = () => {
    updateProfile();
    auth.dispatch({
      type: "Logout",
      payload: {},
    });
    navigate("/login");
  };

  const updateProfile = async () => {
    const response = await fetch("http://localhost:8080/api/updateUser", {
      method: "PUT",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(auth.getState()),
    });
    console.log(response);
  };

  return (
    <div className="navbar-container">
      <button
        type="button"
        onClick={() => {
          auth.dispatch({
            type: "Login",
            payload: {...auth.getState(), latestPage: "dashboard"},
          });
          navigate("/dashboard");
        }}
      >
        Dashboard
      </button>
      <button
        type="button"
        onClick={() => {
          auth.dispatch({
            type: "Login",
            payload: {...auth.getState(), latestPage: "bookings"},
          });
          navigate("/bookings");
        }}
      >
        Bookings
      </button>
      <button
        type="button"
        onClick={() => {
          auth.dispatch({
            type: "Login",
            payload: {...auth.getState(), latestPage: "book-train"},
          });
          navigate("/book-train");
        }}
      >
        Book Train
      </button>
      <button
        type="button"
        onClick={() => {
          auth.dispatch({
            type: "Login",
            payload: {...auth.getState(), latestPage: "profile"},
          });
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
