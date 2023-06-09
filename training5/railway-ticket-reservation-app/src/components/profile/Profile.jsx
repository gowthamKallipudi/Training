import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth, checkAuth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";
import "./profile.css";

const Profile = () => {
  const [profileData, setProfileData] = useState(null);
  const [prompt, setPrompt] = useState(false);
  const [editState, setEditState] = useState(false);

  useEffect(() => {
    if (checkAuth()) setProfileData(auth.getState());
  }, [editState]);

  const state = auth.getState();
  if (state.userName === "") {
    return <Navigate to="/login" />;
  }

  const updateProfile = async () => {
    const response = await fetch("http://localhost:8080/api/updateUser", {
      method: "PUT",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(profileData),
    });
    console.log(response);
    if (response.status === 200) {
      auth.dispatch({
        type: "Login",
        payload: profileData,
      });
      console.log(auth.getState());
      setProfileData(auth.getState());
      setEditState(!editState);
    } else {
      if(!prompt) setPrompt(!prompt);
    }
  };

  return (
    <>
      <NavBar />
      {editState ? (
        <div>
          <table className="profile-table">
            <thead>
              <tr>
                <th colSpan={2}>Edit Profile</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>User Name</td>
                <td>
                  <input
                    type="text"
                    name="userName"
                    value={profileData.userName}
                    placeholder="Enter your User Name"
                    onChange={(e) => {
                      if(prompt) setPrompt(!prompt);
                      setProfileData({
                        ...profileData,
                        [e.target.name]: e.target.value,
                      });
                    }}
                  />
                  {prompt && (
                    <>
                      <div className="prompt">User name already exist ...</div>
                    </>
                  )}
                </td>
              </tr>
              <tr>
                <td>last Name</td>
                <td>
                  <input
                    type="text"
                    name="lastName"
                    value={profileData.lastName}
                    placeholder="Enter your Last Name"
                    onChange={(e) => {
                      setProfileData({
                        ...profileData,
                        [e.target.name]: e.target.value,
                      });
                    }}
                  />
                </td>
              </tr>
              <tr>
                <td>First Name</td>
                <td>
                  <input
                    type="text"
                    name="firstName"
                    value={profileData.firstName}
                    placeholder="Enter your First Name"
                    onChange={(e) => {
                      setProfileData({
                        ...profileData,
                        [e.target.name]: e.target.value,
                      });
                    }}
                  />
                </td>
              </tr>
              <tr>
                <td>Email Id</td>
                <td>
                  <input
                    type="text"
                    name="emailId"
                    value={profileData.emailId}
                    placeholder="Enter your Email ID"
                    onChange={(e) => {
                      setProfileData({
                        ...profileData,
                        [e.target.name]: e.target.value,
                      });
                    }}
                  />
                </td>
              </tr>
              <tr>
                <td>DOB</td>
                <td>
                  <input
                    type="text"
                    name="dob"
                    value={profileData.dob}
                    placeholder="Enter your Date of Birth"
                    onChange={(e) => {
                      setProfileData({
                        ...profileData,
                        [e.target.name]: e.target.value,
                      });
                    }}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <button
                    type="button"
                    onClick={() => {
                      updateProfile();
                    }}
                  >
                    Confirm Edit
                  </button>
                </td>
                <td>
                  <button
                    type="button"
                    onClick={() => {
                      setProfileData(auth.getState());
                      if(prompt) setPrompt(!prompt);
                      setEditState(!editState);
                    }}
                  >
                    Cancel
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      ) : (
        <div>
          {profileData == null ? (
            <div>No data found</div>
          ) : (
            <div>
              <table className="profile-table">
                <thead>
                  <tr>
                    <th colSpan={2}>My Profile</th>
                  </tr>
                </thead>
                <tbody>
                <tr>
                    <td>User Name </td>
                    <td>{profileData.userName}</td>
                  </tr>
                  <tr>
                    <td>Last Name </td>
                    <td>{profileData.lastName}</td>
                  </tr>
                  <tr>
                    <td>First Name </td>
                    <td>{profileData.firstName}</td>
                  </tr>
                  <tr>
                    <td>Email Id </td>
                    <td>{profileData.emailId}</td>
                  </tr>
                  <tr>
                    <td>DOB </td>
                    <td>{profileData.dob}</td>
                  </tr>
                  <tr>
                    <td></td>
                    <td>
                      <button
                        type="button"
                        onClick={() => {
                          setEditState(!editState);
                        }}
                      >
                        Edit Profile
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          )}
        </div>
      )}
    </>
  );
};

export default Profile;
