export const signUpUser = async (signupdata) => {
    const response = await fetch("http://localhost:8080/user/addUser", {
        method: "POST",
        headers: {
            "content-type": "application/json",
        },
        body: JSON.stringify(signupdata)
    })
    const data = await response.text()
    return data;
}

export const editUser = async (editData) => {
    const response = await fetch("http://localhost:8080/user/editUser", {
        method: "PUT",
        headers: {
            "content-type": "application/json",
        },
        body: JSON.stringify(editData)
    })
    const data = await response.text()
    return data;
}