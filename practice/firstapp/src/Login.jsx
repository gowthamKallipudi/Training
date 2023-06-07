import React from "react";
import { useState } from "react";

const initialData = {
  userName: "",
  password: "",
};

const Login = () => {
  const [data, setData] = useState(initialData);

  return (
    <>
      <h1>Showing Login Component</h1>
      <label>UserName </label>
      <input
        type="text"
        name="userName"
        value={data.userName}
        onChange={(e) => {
          setData({ ...data, [e.target.name]: e.target.value });
        }}
      />
      <br />
      <label>PassWord </label>
      <input
        type="password"
        name="password"
        value={data.password}
        onChange={(e) => {
          setData({ ...data, [e.target.name]: e.target.value });
        }}
      />
      <br />
      <button
        type="submit"
        onClick={() => {
          console.log(data);
          setData(initialData);
        }}
      >
        Submit
      </button>
    </>
  );
};

export default Login;
