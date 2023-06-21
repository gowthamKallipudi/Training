import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";

const Profile = () => {
  const [profileData, setProfileData] = useState(null);

  useEffect(() => {
    fetchProfile();
  }, []);

  const state = auth.getState();
  if (state.userName === "") {
    return <Navigate to="/login" />;
  }

  const fetchProfile = async () => {
    const response = await fetch(
      `http://localhost:8080/api/getUser/${state.userName}`,
      {
        method: "GET",
        headers: {
          "content-type": "application/json",
        },
      }
    );
    const data = await response.json();
    setProfileData(data);
  };

  return (
    <>
      <NavBar />
      <div>In Profile Page</div>
      {profileData == null ? (
        <div>No data found</div>
      ) : (
        <div>
          <label>User Name : </label>
          {profileData.userName}
          <br />
          <label>Email Id : </label>
          {profileData.emailId}
          <br />
          <label>Phone Number : </label>
          {profileData.phoneNumber}
        </div>
      )}
    </>
  );
};

export default Profile;
