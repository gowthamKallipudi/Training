import React from "react";
import { useNavigate } from "react-router-dom";
import { auth } from "../../authentication";
import "./ProfileBar.css";

const ProfileBar = ({callbackfunction}) => {
    const navigate = useNavigate();

    const logout = () => {
        auth.dispatch({
          type: "Logout",
          payload: {},
        });
        navigate("/dashboard");
      };

    return (
        <div className="profile-bar">
            <button type="button" onClick={() => {navigate("/profile")}}>My Profile</button>
            <button type="button" onClick={() => {navigate("/bookings")}}>My Bookings</button>
            <button type="button" onClick={() => {logout(); callbackfunction()}}>Logout</button>
        </div>
    );
}

export default ProfileBar;