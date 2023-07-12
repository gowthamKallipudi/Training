import React from "react";
import { useNavigate } from "react-router-dom";
import { auth } from "../../authentication";

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
        <>
            <button type="button" onClick={() => {navigate("/profile")}}>My Profile</button>
            <button type="button" onClick={() => {navigate("/bookings")}}>My Bookings</button>
            <button type="button" onClick={() => {logout(); callbackfunction()}}>Logout</button>
        </>
    );
}

export default ProfileBar;