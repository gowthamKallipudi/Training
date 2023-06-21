import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./signup.css";

const initialData = {
  userName: "",
  emailId: "",
  phoneNumber: "",
  password: "",
};

const SignUp = () => {
  const [signUpData, setLoginData] = useState(initialData);
  const [prompt, setPrompt] = useState(false);
  const navigate = useNavigate();

  const sendData = async (e) => {
    e.preventDefault();
    const response = await fetch("http://localhost:8080/api/addUser", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(signUpData),
    });
    console.log(response);
    if (response.status === 201) {
      navigate("/login");
    } else {
      setPrompt(!prompt);
    }
  };

  return (
    <div className="signup-container">
      <form>
        <div className="input-fields">
          <label>User Name : </label>
          <input
            type="text"
            name="userName"
            value={signUpData.userName}
            placeholder="Enter your user name"
            onChange={(e) => {
              if (prompt) setPrompt(!prompt);
              setLoginData({ ...signUpData, [e.target.name]: e.target.value });
            }}
          />
          {prompt && (
            <>
              <div className="prompt">User name already exist ...</div>
            </>
          )}
        </div>
        <br />
        <div className="input-fields">
          <label>Email Id : </label>
          <input
            type="text"
            name="emailId"
            value={signUpData.emailId}
            placeholder="Enter your email id"
            onChange={(e) => {
              setLoginData({ ...signUpData, [e.target.name]: e.target.value });
            }}
          />
        </div>
        <br />
        <div className="input-fields">
          <label>Phone Number : </label>
          <input
            type="text"
            name="phoneNumber"
            value={signUpData.phoneNumber}
            placeholder="Enter your phone number"
            onChange={(e) => {
              setLoginData({ ...signUpData, [e.target.name]: e.target.value });
            }}
          />
        </div>
        <br />
        <div className="input-fields">
          <label>Password : </label>
          <input
            type="text"
            name="password"
            value={signUpData.password}
            placeholder="Enter your password"
            onChange={(e) => {
              setLoginData({ ...signUpData, [e.target.name]: e.target.value });
            }}
          />
        </div>
        <br />
        <div className="submit-button">
          <button
            type="submit"
            onClick={(e) => {
              sendData(e);
            }}
          >
            SignUp
          </button>
        </div>
        <br />
        Back to login? <Link to="/login">Login</Link>
      </form>
    </div>
  );
};

export default SignUp;
