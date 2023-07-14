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
    const [prompt, setPrompt] = useState({state: false, message: ""})
    const navigate = useNavigate();

    const loginCheck = async () => {
        console.log(loginCredentials)
        const data = await loginUser(loginCredentials)
        console.log(data);
        if(data.username !== "User name Password doesn't matched !!!") {
            navigate("/dashboard")
        } else {
            setPrompt({state: true, message: data.username})
        }
        setLoginCredentials(defaultValues)
    }

    return (
        <>
            <div className="login-container">
                <div className="title">Login</div>
                <div>
                    <label>User Name : </label>
                    <input type="text"
                     name="username" 
                     placeholder="Enter your user name" 
                     value={loginCredentials.username} 
                     onChange={(e) => {setLoginCredentials({...loginCredentials, [e.target.name] : e.target.value})}}
                     onClick={() => {setPrompt({state: false, message: ""})}} />
                </div>
                <div>
                    <label>Password : </label>
                    <input type="password"
                     name="password" 
                     placeholder="Enter your password" 
                     value={loginCredentials.password} 
                     onChange={(e) => {setLoginCredentials({...loginCredentials, [e.target.name]: e.target.value})}}
                     onClick={() => {setPrompt({state: false, message: ""})}} />
                </div>
                <div>
                    <button type="submit" onClick={() => {loginCheck()}}>Login</button>
                </div>
                {prompt.state && <div style={{color: "rgb(216, 38, 38)", fontSize: "large", margin: "5px"}}>
                    {prompt.message}
                 </div>}
            </div>
        </>
    );
}

export default Login;