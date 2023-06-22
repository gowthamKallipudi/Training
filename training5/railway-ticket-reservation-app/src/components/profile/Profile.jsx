import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth, checkAuth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";
import "./profile.css";

const Profile = () => {
  const [profileData, setProfileData] = useState(null);

  useEffect(() => {
    if (checkAuth()) fetchProfile();
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
      <div className="profile-heading">My Profile</div>
      {profileData == null ? (
        <div>No data found</div>
      ) : (
        <div>
          <table className="profile-table">
            <thead>
              <th colSpan={2}>Profile</th>
            </thead>
            <tr>
              <td>User Name </td>
              <td>{profileData.userName}</td>
            </tr>
            <tr>
              <td>Email Id </td>
              <td>{profileData.emailId}</td>
            </tr>
            <tr>
              <td>Phone Number </td>
              <td>{profileData.phoneNumber}</td>
            </tr>
          </table>
        </div>
      )}
    </>
  );
};

export default Profile;
