import React from "react";
import Login from "./Login";
import SignUp from "./SignUp";
import "./LoginSignup.css"

const LoginSignup = () => {
    return (
        <div className="login-signup-container">
            <Login />
            <SignUp />
        </div>
    );
}

export default LoginSignup;