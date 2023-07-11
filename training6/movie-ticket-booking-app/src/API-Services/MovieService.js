export const fetchMovies = async () => {
    const response = await fetch("http://localhost:8080/movie/fetchMovies", {
        method: "GET",
        headers: {
            "content-type": "application/json",
        }
    })
    const data = response.json();
    return data;
}

export const fetchMovieByLocation = async (location) => {
    const response = await fetch(`http://localhost:8080/movie/fetchMovie/${location}`, {
        method: "GET",
        headers: {
            "content-type": "application/json",
        }
    })
    const data = response.json();
    return data;
}