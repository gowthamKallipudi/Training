import { auth } from "../authentication";

export const loginUser = async (loginData) => {
    const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "content-type": "application/json",
        },
        body: JSON.stringify(loginData)
    })
    const data = await response.json();
    console.log(data)
    if(data.username !== "User name Password doesn't matched !!!") {
        auth.dispatch({
            type: "Login",
            payload: data,
        });
    }
    return data;
}