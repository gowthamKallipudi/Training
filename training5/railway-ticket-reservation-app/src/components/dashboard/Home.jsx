import React from "react";
import NavBar from "../navbar/NavBar";
import { auth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";

const Home = () => {
  const state = auth.getState();
  if (state.userName === "") {
    return <Navigate to="/login" />;
  }
  return (
    <>
      <NavBar />
      <div>Welcome To Train Ticket Reservation Application</div>
    </>
  );
};

export default Home;
