import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./login.css";

const initialData = {
  userName: "",
  password: "",
};

const Login = () => {
  const [loginData, setLoginData] = useState(initialData);
  const [prompt, setPrompt] = useState(false);
  const navigate = useNavigate();

  const sendData = async (e) => {
    e.preventDefault();
    console.log(loginData);
    const response = await fetch("http://localhost:8080/api/checkUser", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(loginData),
    });
    console.log(response);
    if (response.status === 200) {
      navigate("/home");
    } else {
      setPrompt(true);
    }
  };

  return (
    <>
      <div className="login-container">
        <form>
          <div className="input-fields">
            <label>User Name : </label>
            <input
              type="text"
              name="userName"
              value={loginData.userName}
              placeholder="Enter your user name"
              onChange={(e) => {
                if (prompt) setPrompt(!prompt);
                setLoginData({ ...loginData, [e.target.name]: e.target.value });
              }}
            />
          </div>
          <br />
          <div className="input-fields">
            <label>Password : </label>
            <input
              type="password"
              name="password"
              value={loginData.password}
              placeholder="Enter your password"
              onChange={(e) => {
                if (prompt) setPrompt(!prompt);
                setLoginData({ ...loginData, [e.target.name]: e.target.value });
              }}
            />
          </div>
          <br />
          {prompt && (
            <>
              <div className="prompt">Bad Credentials ...</div> <br />
            </>
          )}
          <div className="submit-button">
            <button
              type="submit"
              onClick={(e) => {
                sendData(e);
              }}
            >
              Login
            </button>
          </div>
          <br />
          Don't have an account? <Link to="/signup">signup</Link>
        </form>
      </div>
    </>
  );
};

export default Login;
