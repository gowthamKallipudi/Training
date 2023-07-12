export const fetchBookingsById = async (id) => {
    const response = await fetch(`http://localhost:8080/booking/fetchBookings/${id}`, {
        method: "GET",
        headers: {
            "content-type": "application/json",
        }
    })
    const data = response.json();
    return data;
}


export const fetchCapacityBookings = async (theatre, show, date) => {
    const response = await fetch(`http://localhost:8080/booking/fetchSeats?theatre=${theatre}&show=${show}&date=${date}`, {
        method: "GET",
        headers: {
            "content-type": "application/json",
        }
    })
    const data = response.json();
    return data;
}