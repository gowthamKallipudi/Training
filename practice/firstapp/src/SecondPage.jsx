import React, { useState } from "react";

const initialData = {
  name: "",
  mobile: "",
  email: "",
  password: "",
  confirmPassword: "",
};

const fieldCheck = {
  name: false,
  mobile: false,
  email: false,
  password: false,
  confirmPassword: false,
};

const SecondPage = () => {
  const [formData, setFormData] = useState(initialData);
  const [fieldStatus, setFieldStatus] = useState(fieldCheck);

  return (
    <>
      <h1>Hello Java</h1>
      <h4>This component is to build to display about the required fields</h4>
      <form>
        <label>Name : </label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={(e) => {
            setFormData({ ...formData, [e.target.name]: e.target.value });
          }}
        />
        <br />
        <label>Mobile : </label>
        <input
          type="text"
          name="mobile"
          value={formData.mobile}
          onChange={(e) => {
            setFormData({ ...formData, [e.target.name]: e.target.value });
          }}
        />
        <br />
        <label>Email : </label>
        <input
          type="text"
          name="email"
          value={formData.email}
          onChange={(e) => {
            setFormData({ ...formData, [e.target.name]: e.target.value });
          }}
        />
        <br />
        <label>Password : </label>
        <input
          type="text"
          name="password"
          value={formData.password}
          onChange={(e) => {
            setFormData({ ...formData, [e.target.name]: e.target.value });
          }}
        />
        <br />
        <label>Confirm Password : </label>
        <input
          type="text"
          name="confirmPassword"
          value={formData.confirmPassword}
          onChange={(e) => {
            setFormData({
              ...formData,
              [e.target.name]: e.target.value,
            });
          }}
        />
        <br />
        <button
          type="submit"
          onClick={() => {
            console.log(formData);
            setFormData(initialData);
          }}
        >
          Submit
        </button>
      </form>
    </>
  );
};

export default SecondPage;
