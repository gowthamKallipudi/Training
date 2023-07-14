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

export const addBooking = async (bookingData) => {
    const response = await fetch("http://localhost:8080/booking/addBooking", {
        method: "POST",
        headers: {
            "content-type": "application/json",
        },
        body: JSON.stringify(bookingData)
    })
    const data = await response.json()
    return data;
}

export const deleteBooking = async (id) => {
    const response = await fetch(`http://localhost:8080/booking/deleteBooking/${id}`, {
        method: "DELETE",
        headers: {
            "content-type": "application/json",
        },
    })
    const data = await response.json()
    return data;
}