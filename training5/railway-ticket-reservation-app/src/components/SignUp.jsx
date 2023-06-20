import React, { useState } from "react";
import { Link } from "react-router-dom";

const initialData = {
  userName: "",
  emailId: "",
  phoneNumber: "",
  password: "",
};

const SignUp = () => {
  const [signUpData, setLoginData] = useState(initialData);

  const sendData = async (e) => {
    e.preventDefault();
    console.log(signUpData);
    const response = await fetch("http://localhost:8080/api/addUser", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(signUpData),
    });
    console.log(response);
  };

  return (
    <div>
      <form>
        <label>User Name : </label>
        <input
          type="text"
          name="userName"
          value={signUpData.userName}
          placeholder="Enter your user name"
          onChange={(e) => {
            setLoginData({ ...signUpData, [e.target.name]: e.target.value });
          }}
        />
        <br />
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
        <br />
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
        <br />
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
        <br />
        <button
          type="submit"
          onClick={(e) => {
            sendData(e);
          }}
        >
          SignUp
        </button>
        Back to login? <Link to="/login">Login</Link>
      </form>
    </div>
  );
};

export default SignUp;
