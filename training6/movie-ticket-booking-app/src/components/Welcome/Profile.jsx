import React from "react";
import { auth } from "../../authentication";
import { useNavigate } from "react-router-dom";

const Profile = () => {
    const navigate = useNavigate();
    const state = auth.getState();

    return(
        <>
            <button type="button" onClick={() => {navigate("/dashboard")}}>Dashboard</button>
            <table>
                <thead>
                    <tr rowspawn={2}>
                        <th>My Profile</th>
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
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </>
    );
}

export default Profile;