import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Navigate } from "react-router-dom";

const initialData = {
  userName: "",
  password: "",
};

const Login = () => {
  const [loginData, setLoginData] = useState(initialData);
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
    console.log(response.status);
    if(response.status === 200) {
      navigate("/home");
    }
  };

  return (
    <div>
      <form>
        <label>User Name : </label>
        <input
          type="text"
          name="userName"
          value={loginData.userName}
          placeholder="Enter your user name"
          onChange={(e) => {
            setLoginData({ ...loginData, [e.target.name]: e.target.value });
          }}
        />
        <br />
        <label>Password : </label>
        <input
          type="password"
          name="password"
          value={loginData.password}
          placeholder="Enter your password"
          onChange={(e) => {
            setLoginData({ ...loginData, [e.target.name]: e.target.value });
          }}
        />
        <br />
        <button
          type="submit"
          onClick={(e) => {
            sendData(e);
          }}
        >
          Login
        </button>
        <br />
        Don't have an account? <Link to="/signup">signup</Link>
      </form>
    </div>
  );
};

export default Login;
