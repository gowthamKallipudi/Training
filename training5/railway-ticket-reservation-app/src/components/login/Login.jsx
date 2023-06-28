import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./login.css";
import { auth } from "../../utilities/authentication";

const initialData = {
  userName: "",
  password: "",
};

const Login = () => {
  const [loginData, setLoginData] = useState(initialData);
  const [prompt, setPrompt] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    console.log(auth.getState());
  }, []);

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
    if (response.status === 200) {
      const data = await response.json();
      auth.dispatch({
        type: "Login",
        payload: data,
      });
      console.log(auth.getState());
      if(auth.getState().latestPage === ""){
        navigate("/dashboard");
      }
      else {
        navigate("/" + auth.getState().latestPage);
      }
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
