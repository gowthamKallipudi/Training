import React, { useState } from "react";
import "./SignUp.css"
import { signUpUser } from "../../API-Services/SignupService";

const defaultValues = {
    "username": "",
    "firstname": "",
    "lastname": "",
    "email": "",
    "phone": "",
    "password": ""
}

const SignUp = () => {
    const [signupCredentials, setSignupCredentials] = useState(defaultValues)

    const signup = async () => {
        console.log(signupCredentials);
        const data = await signUpUser(signupCredentials)
        console.log(data);
        setSignupCredentials(defaultValues)
    }

    return (
        <>
            <div className="signup-container">
                SignUp
                <div>
                    <label>User Name : </label>
                    <input type="text"
                     name="username" 
                     placeholder="Enter your user name" 
                     value={signupCredentials.username} 
                     onChange={(e) => {setSignupCredentials({...signupCredentials, [e.target.name] : e.target.value})}} />
                </div>
                <div>
                    <label>First Name : </label>
                    <input type="firstname"
                     name="firstname" 
                     placeholder="Enter your first name" 
                     value={signupCredentials.firstname} 
                     onChange={(e) => {setSignupCredentials({...signupCredentials, [e.target.name]: e.target.value})}} />
                </div>
                <div>
                    <label>Last Name : </label>
                    <input type="text"
                     name="lastname" 
                     placeholder="Enter your last name" 
                     value={signupCredentials.lastname} 
                     onChange={(e) => {setSignupCredentials({...signupCredentials, [e.target.name] : e.target.value})}} />
                </div>
                <div>
                    <label>Email Id : </label>
                    <input type="text"
                     name="email" 
                     placeholder="Enter your email" 
                     value={signupCredentials.email} 
                     onChange={(e) => {setSignupCredentials({...signupCredentials, [e.target.name]: e.target.value})}} />
                </div>
                <div>
                    <label>Phone : </label>
                    <input type="text"
                     name="phone" 
                     placeholder="Enter your phone" 
                     value={signupCredentials.phone} 
                     onChange={(e) => {setSignupCredentials({...signupCredentials, [e.target.name]: e.target.value})}} />
                </div>
                <div>
                    <label>Password : </label>
                    <input type="password"
                     name="password" 
                     placeholder="Enter your password" 
                     value={signupCredentials.password} 
                     onChange={(e) => {setSignupCredentials({...signupCredentials, [e.target.name] : e.target.value})}} />
                </div>
                <div>
                    <button type="submit" onClick={() => {signup()}}>SignUp</button>
                </div>
            </div>
        </>
    );
}

export default SignUp;