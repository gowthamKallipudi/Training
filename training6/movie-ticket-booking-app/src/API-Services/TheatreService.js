export const fetchTheatresByMovie = async (movie) => {
    const response = await fetch(`http://localhost:8080/theatre/getTheatre/${movie}`, {
        method: "GET",
        headers: {
            "content-type": "application/json",
        }
    })
    const data = response.json();
    return data;
}

export const fetchTheatreByLocation = async (movie, location) => {
    const response = await fetch(`http://localhost:8080/theatre/fetchTheatre/${location}?movie=${movie}`, {
        method: "GET",
        headers: {
            "content-type": "application/json",
        }
    })
    const data = response.json();
    return data;
}