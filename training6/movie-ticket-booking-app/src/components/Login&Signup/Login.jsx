import React, { useState } from "react";
import "./Login.css"
import { loginUser } from "../../API-Services/LoginService";
import { useNavigate } from "react-router-dom";

const defaultValues = {
    "username": "",
    "password": ""
}

const Login = () => {
    const [loginCredentials, setLoginCredentials] = useState(defaultValues)
    const navigate = useNavigate();

    const loginCheck = async () => {
        console.log(loginCredentials)
        const data = await loginUser(loginCredentials)
        console.log(data);
        setLoginCredentials(defaultValues)
        if(data !== false) {
            navigate("/dashboard")
        }
    }

    return (
        <>
            <div className="login-container">
                Login
                <div>
                    <label>User Name : </label>
                    <input type="text"
                     name="username" 
                     placeholder="Enter your user name" 
                     value={loginCredentials.username} 
                     onChange={(e) => {setLoginCredentials({...loginCredentials, [e.target.name] : e.target.value})}} />
                </div>
                <div>
                    <label>Password : </label>
                    <input type="password"
                     name="password" 
                     placeholder="Enter your password" 
                     value={loginCredentials.password} 
                     onChange={(e) => {setLoginCredentials({...loginCredentials, [e.target.name]: e.target.value})}} />
                </div>
                <div>
                    <button type="submit" onClick={() => {loginCheck()}}>Login</button>
                </div>
            </div>
        </>
    );
}

export default Login;