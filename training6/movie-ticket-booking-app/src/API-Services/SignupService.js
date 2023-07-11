export const signUpUser = async (signupdata) => {
    const response = await fetch("http://localhost:8080/user/addUser", {
        method: "POST",
        headers: {
            "content-type": "application/json",
        },
        body: JSON.stringify(signupdata)
    })
    const data = await response.json()
    return data;
}