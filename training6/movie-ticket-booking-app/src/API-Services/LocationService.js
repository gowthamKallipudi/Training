export const fetchLocations = async () => {
    const response = await fetch("http://localhost:8080/fetchRegion", {
        method: "GET",
        headers: {
            "content-type": "application/json",
        }
    })
    const data = response.json();
    return data;
}