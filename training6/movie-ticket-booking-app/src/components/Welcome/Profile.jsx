import React, { useState } from "react";
import { auth } from "../../authentication";
import { Navigate, useNavigate } from "react-router-dom";
import { editUser } from "../../API-Services/SignupService";
import "./Profile.css"

const Profile = () => {
    const navigate = useNavigate();
    const [editState, setEditState] = useState(false)
    const [state, setState] = useState(auth.getState())

    if (state.userName === "") {
    return <Navigate to="/dashboard" />;
    }

    const editProfile = async () => {
        const data = await editUser(state)
        if(data === "user data updated successfully") {
            auth.dispatch({
                type: "Login",
                payload: state,
              });
        } else {
            setState(auth.getState())
        }
        setEditState(false)
    }

    return(
        <div className="profile-container">
            <button type="button" onClick={() => {setEditState(false); navigate("/dashboard")}}>Dashboard</button>
            {!editState ? 
            <table className="profile-table">
                <thead>
                    <tr>
                        <th colSpan={2}>My Profile</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>User Name</td>
                        <td>{state.username}</td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td>{state.firstname}</td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td>{state.lastname}</td>
                    </tr>
                    <tr>
                        <td>Email Id</td>
                        <td>{state.email}</td>
                    </tr>
                    <tr>
                        <td>Phone Number</td>
                        <td>{state.phone}</td>
                    </tr>
                    <tr>
                        <td colSpan={2}><button type="button" onClick={() => {setEditState(!editState)}}>Edit Profile</button></td>
                    </tr>
                </tbody>
            </table> 
            : 
            <table className="profile-table">
                <thead>
                    <tr>
                        <th colSpan={2}>Edit Profile</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="username" value={state.username} onChange={(e) => {setState({...state, [e.target.name]: e.target.value})}} /></td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="firstname" value={state.firstname} onChange={(e) => {setState({...state, [e.target.name]: e.target.value})}} /></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="lastname" value={state.lastname} onChange={(e) => {setState({...state, [e.target.name]: e.target.value})}} /></td>
                    </tr>
                    <tr>
                        <td>Email Id</td>
                        <td><input type="text" name="email" value={state.email} onChange={(e) => {setState({...state, [e.target.name]: e.target.value})}} /></td>
                    </tr>
                    <tr>
                        <td>Phone Number</td>
                        <td><input type="text" name="phone" value={state.phone} onChange={(e) => {setState({...state, [e.target.name]: e.target.value})}} /></td>
                    </tr>
                    <tr>
                        <td><button type="button" onClick={() => {editProfile()}}>Edit Profile</button></td>
                        <td><button type="button" onClick={() => {setState(auth.getState()); setEditState(!editState)}}>Cancel</button></td>
                    </tr>
                </tbody>
            </table>
            }
        </div>
    );
}

export default Profile;